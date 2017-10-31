package buzz.pushfit.im.mvp.view.fragment

import android.support.v7.widget.LinearLayoutManager
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.ConversationListAdapter
import buzz.pushfit.im.adapter.EMContactListenerAdapter
import buzz.pushfit.im.adapter.EMMessageListenerAdapter
import buzz.pushfit.im.base.BaseFragment
import buzz.pushfit.im.mvp.presenter.impl.ConversationPresenter
import buzz.pushfit.im.mvp.view.interfaces.IConversationView
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.layout_recycler.*

/**
 * Created by yuequan on 2017/10/27.
 */
class ConversationFragment : BaseFragment(), IConversationView {
    val presenter = ConversationPresenter(this)

    private val mMessageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: List<EMMessage>) {
            presenter.onLoadConversationsData()
        }

    }
    private val mEmContactListener = object : EMContactListenerAdapter() {
        override fun onContactAdded(p0: String?) {
            EMClient.getInstance().chatManager().deleteConversation(p0, false)
            presenter.onLoadConversationsData()
        }
    }


    override fun getLayoutResId(): Int = R.layout.abd_fragment_conversation

    override fun init() {
        super.init()

        initSwipeRefreshLayout()
        initRecyclerView()
        EMClient.getInstance().chatManager().addMessageListener(mMessageListener)//监听消息刷新列表
        EMClient.getInstance().contactManager().setContactListener(mEmContactListener)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context, presenter.conversations)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            isRefreshing = true
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener { presenter.onLoadConversationsData() }
        }
    }

    override fun onLoadConversationsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter.notifyDataSetChanged()

    }

    override fun onLoadConversationsFailed() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onResume() {
        super.onResume()
        presenter.onLoadConversationsData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter == null
        EMClient.getInstance().chatManager().removeMessageListener(mMessageListener)//销毁监听器
    }
}