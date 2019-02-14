package nikhilbhutani.github.io.dextra.ui.listeners

interface RecyclerViewItemClickListener {
    fun onBannerClick(parentAdapterPostion: Int, position: Int)

    fun onDexClick(parentAdapterPostion: Int, position: Int)
}