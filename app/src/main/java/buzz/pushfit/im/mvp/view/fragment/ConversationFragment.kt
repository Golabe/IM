package buzz.pushfit.im.mvp.view.fragment

import android.support.v7.widget.LinearLayoutManager
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.ConversationListAdapter
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
    val presenter=ConversationPresenter(this)

    private val messageListener=object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: List<EMMessage>) {
            presenter.onLoadConversationsData()
        }
    }

    override fun getLayoutResId(): Int = R.layout.abd_fragment_conversation

    override fun init() {
        super.init()

        swipeRefreshLayout.apply {
            isRefreshing=true
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener { presenter.onLoadConversationsData() }
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter=ConversationListAdapter(context,presenter.conversations)
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)//监听消息刷新列表

    }

    override fun onLoadConversationsSuccess() {
        swipeRefreshLayout.isRefreshing=false
        recyclerView.adapter.notifyDataSetChanged()

    }

    override fun onLoadConversationsFailed() {
        swipeRefreshLayout.isRefreshing=false
    }

    override fun onResume() {
        super.onResume()
        presenter.onLoadConversationsData()
    }
    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)//销毁监听器
    }
}