package buzz.pushfit.im.mvp.view.fragment

import android.support.v7.widget.LinearLayoutManager
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.FriendsListAdapter
import buzz.pushfit.im.base.BaseFragment
import kotlinx.android.synthetic.main.layout_recycler.*

/**
 * Created by yuequan on 2017/10/27.
 */
class FriendsFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.abd_fragment_friends

    override fun init() {
        super.init()

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.colorPrimary)
            isRefreshing = true
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter=FriendsListAdapter(context)
        }

    }
}