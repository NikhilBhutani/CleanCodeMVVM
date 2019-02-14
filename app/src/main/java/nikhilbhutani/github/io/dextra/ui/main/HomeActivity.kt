package nikhilbhutani.github.io.dextra.ui.main

import android.app.PendingIntent
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import nikhilbhutani.github.io.dextra.BR
import nikhilbhutani.github.io.dextra.R
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.databinding.ActivityHomeBinding
import nikhilbhutani.github.io.dextra.factory.AppViewModelFactory
import nikhilbhutani.github.io.dextra.ui.base.BaseActivity
import nikhilbhutani.github.io.dextra.ui.dexes.DexActivity
import nikhilbhutani.github.io.dextra.ui.listeners.RecyclerViewItemClickListener
import nikhilbhutani.github.io.dextra.utils.CustomTabHelper
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator, RecyclerViewItemClickListener {

    @Inject
    lateinit var viewModeFactory: AppViewModelFactory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var homeParentRecyclerviewAdapter: HomeParentRecyclerviewAdapter

    private var exploreList: List<ExploreResponse.Explore> = ArrayList()


    override fun getBindingVariable(): Int {
        return BR.homeviewmodel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        homeViewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)
        return homeViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.setNavigator(this)

        recyclerview_parent.layoutManager = LinearLayoutManager(this)
        homeParentRecyclerviewAdapter = HomeParentRecyclerviewAdapter()
        recyclerview_parent.adapter = homeParentRecyclerviewAdapter

        homeViewModel.fetchData()

    }

    override fun setExploreResponseList(exploreList: List<ExploreResponse.Explore>) {
        this.exploreList = exploreList
        homeParentRecyclerviewAdapter.setExploreResponseList(exploreList)
        homeParentRecyclerviewAdapter.notifyDataSetChanged()
    }


    override fun onBannerClick(parentAdapterPostion: Int, position: Int) {

        val bannerActionUrl = exploreList.get(parentAdapterPostion).entities.get(position).actionUrl

        var customTabHelper: CustomTabHelper = CustomTabHelper()

        val builder = CustomTabsIntent.Builder()

        // modify toolbar color
        builder.setToolbarColor(ContextCompat.getColor(this@HomeActivity, R.color.colorPrimary))

        // add share button to overflow menu
        builder.addDefaultShareMenuItem()

        val anotherCustomTab = CustomTabsIntent.Builder().build()

        val requestCode = 100
        val intent = anotherCustomTab.intent
        intent.setData(Uri.parse(bannerActionUrl))

        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // add menu item to oveflow
        builder.addMenuItem("Sample item", pendingIntent)

        // menu item icon
        // val bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
        // builder.setActionButton(bitmap, "Android", pendingIntent, true)

        // modify back button icon
        // builder.setCloseButtonIcon(bitmap)

        // show website title
        builder.setShowTitle(true)

        // animation for enter and exit of tab
        builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)

        val customTabsIntent = builder.build()

//        // check is chrom available
        val packageName = customTabHelper.getPackageNameToUse(this, bannerActionUrl)
        customTabsIntent.intent.setPackage(packageName)
        customTabsIntent.launchUrl(this, Uri.parse(bannerActionUrl))

        /**
         *  If packageName is null, we shall open the webview here
         */
    }

    override fun onDexClick(parentAdapterPostion: Int, position: Int) {
        val dexId = exploreList.get(parentAdapterPostion).entities.get(position).id
        openDexActivity(dexId)
    }


    override fun handleError(throwable: Throwable?) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun openDexActivity(dexId: String) {
        val intent = Intent(this, DexActivity::class.java)
        intent.putExtra("dex_id", dexId)
        startActivity(intent)
    }

    override fun showToast() {
    }


}