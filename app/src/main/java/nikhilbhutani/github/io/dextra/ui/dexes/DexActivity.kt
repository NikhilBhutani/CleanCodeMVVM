package nikhilbhutani.github.io.dextra.ui.dexes

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_dexes.*
import nikhilbhutani.github.io.dextra.BR
import nikhilbhutani.github.io.dextra.R
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import nikhilbhutani.github.io.dextra.databinding.ActivityDexesBinding
import nikhilbhutani.github.io.dextra.factory.AppViewModelFactory
import nikhilbhutani.github.io.dextra.ui.base.BaseActivity
import javax.inject.Inject

class DexActivity : BaseActivity<ActivityDexesBinding, DexActivityViewModel>(), DexNavigator {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private lateinit var dexActivityViewModel: DexActivityViewModel

    private lateinit var dexUsersRecyclerViewAdapter: DexUsersRecyclerViewAdapter

    private lateinit var layoutManager: LinearLayoutManager

    private var previousTotal = 0
    private var visibleThreshold = 10
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var loadingMore = true

    private lateinit var nextDexId: String

    override fun getBindingVariable(): Int {
        return BR.dexviewmodel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_dexes
    }

    override fun getViewModel(): DexActivityViewModel {
        dexActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(DexActivityViewModel::class.java)
        return dexActivityViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dexActivityViewModel.setNavigator(this)

        layoutManager = LinearLayoutManager(this)
        recyclerview_dexes.layoutManager = layoutManager
        dexUsersRecyclerViewAdapter = DexUsersRecyclerViewAdapter(this)
        recyclerview_dexes.setHasFixedSize(true)
        recyclerview_dexes.adapter = dexUsersRecyclerViewAdapter


        recyclerview_dexes.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (loadingMore) {
                    if (visibleItemCount + firstVisibleItem >= totalItemCount && firstVisibleItem >= 0) {
                        loadMoreUsers()
                    }//                    && totalItemCount >= ClothesFragment.itemsCount
                }


            }
        })

        dexActivityViewModel.fetchUserList(intent.getStringExtra("dex_id"))

    }

    override fun setUserList(users: List<DexUserResponse.User>, next: String) {

        if (users.isNotEmpty()) {

            if (next == null || next.equals("false", true)) {
                loadingMore = false
            } else {
                nextDexId = next
            }

            dexUsersRecyclerViewAdapter.addUsers(users)
        } else {
            loadingMore = false
        }
    }


    fun loadMoreUsers() {

        /**
         *  Uncomment this when only dexId is coming in next in json
         */
        /// dexActivityViewModel.fetchUserList(nextDexId)
    }

    override fun handleError(throwable: Throwable?) {
    }

    override fun showToast() {
    }
}