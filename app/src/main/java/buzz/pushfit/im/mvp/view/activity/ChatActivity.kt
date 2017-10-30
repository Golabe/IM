package buzz.pushfit.im.mvp.view.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.View
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.EMMessageListenerAdapter
import buzz.pushfit.im.adapter.TextWatcherAdapter
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.adapter.MessageListAdapter
import buzz.pushfit.im.mvp.presenter.impl.ChatPresenter
import buzz.pushfit.im.mvp.view.interfaces.IChatView
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.abc_activity_chat.*
import kotlinx.android.synthetic.main.layout_header.*
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(), IChatView {

    val presenter = ChatPresenter(this)
    lateinit var username: String
    private var msgListener: EMMessageListener = object : EMMessageListenerAdapter() {

        override fun onMessageReceived(messages: List<EMMessage>) {
            presenter.addMessage(username, messages)

            runOnUiThread {
                chatRecyclerView.adapter.notifyDataSetChanged()
                scrollToBottom()
            }
        }
    }

    override fun getLayoutResId(): Int = R.layout.abc_activity_chat

    override fun init() {
        super.init()

        initHeader()
        initEdit()
        initRecyclerView()


        edit.setOnEditorActionListener { p0, p1, p2 ->
            sendMessage()
            true
        }
        EMClient.getInstance().chatManager().addMessageListener(msgListener)

        send.setOnClickListener { sendMessage() }
        presenter.loadMessage(username)


    }

    private fun sendMessage() {
        hideSoftKeyboard()
        val message = edit.text.trim().toString()
        presenter.onSendMessage(username, message)
    }

    private fun initRecyclerView() {
        chatRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, presenter.messages)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                            presenter.loadMoreMessage(username)
                        }
                    }
                }
            })
        }

    }

    private fun initEdit() {
        edit.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(p0: Editable?) {
                send.isEnabled = !p0.isNullOrEmpty()
            }
        })
    }

    private fun initHeader() {
        username = intent.getStringExtra("username")
        headerLeft.visibility = View.VISIBLE
        headerLeft.setImageResource(R.mipmap.ic_back_48)
        headerLeft.setOnClickListener { finish() }
        val title = String.format(getString(R.string.chating, username))
        headerTitle.text = title

    }

    override fun onStartSendMessage() {
        chatRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        chatRecyclerView.adapter.notifyDataSetChanged()
        toast(getString(R.string.send_message_success))
        edit.text.clear()//清除编辑框
        scrollToBottom()
    }

    private fun scrollToBottom() {
        chatRecyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageFailed() {
        toast(getString(R.string.send_message_failed))
        chatRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onMessageLoad() {
        chatRecyclerView.adapter.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size:Int) {
        chatRecyclerView.adapter.notifyDataSetChanged()
        chatRecyclerView.scrollToPosition(size)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(msgListener)
    }
}
