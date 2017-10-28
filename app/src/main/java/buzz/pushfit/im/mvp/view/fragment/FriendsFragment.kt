package buzz.pushfit.im.mvp.view.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.EMContactListenerAdapter
import buzz.pushfit.im.adapter.FriendsListAdapter
import buzz.pushfit.im.base.BaseFragment
import buzz.pushfit.im.mvp.presenter.impl.FriendsPresenter
import buzz.pushfit.im.mvp.view.IFriendsView
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.layout_recycler.*

/**
 * Created by yuequan on 2017/10/27.
 */
class FriendsFragment : BaseFragment(), IFriendsView {
    val presenter = FriendsPresenter(this)
    lateinit var mRefreshLayout: SwipeRefreshLayout
    lateinit var mRecyclerView: RecyclerView
    override fun getLayoutResId(): Int = R.layout.abd_fragment_friends

    override fun init() {
        super.init()
        mRefreshLayout = activity.findViewById(R.id.swipeRefreshLayout)
        mRecyclerView = activity.findViewById(R.id.recyclerView)
        presenter.onLoadFriendsData()//加载好友列表
        mRefreshLayout.apply {
            setColorSchemeResources(R.color.colorPrimary)
            isRefreshing = true
            //下拉加载好友列表
            setOnRefreshListener {
                presenter.onLoadFriendsData()
            }
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = FriendsListAdapter(context, presenter.friendItemList)
        }

        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter() {
            override fun onContactDeleted(p0: String?) {
                super.onContactDeleted(p0)
                //重新获取联系人列表
                presenter.onLoadFriendsData()
            }
        })

    }

    override fun onLoadFriendsSuccess() {

        mRefreshLayout.isRefreshing = false

        mRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onLoadFriendsFailed() {

        mRefreshLayout.isRefreshing = false

    }

}
