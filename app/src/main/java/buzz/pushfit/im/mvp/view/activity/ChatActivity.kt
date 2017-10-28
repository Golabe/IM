package buzz.pushfit.im.mvp.view.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.TextWatcherAdapter
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.adapter.MessageListAdapter
import buzz.pushfit.im.mvp.presenter.impl.ChatPresenter
import buzz.pushfit.im.mvp.view.IChatView
import kotlinx.android.synthetic.main.abc_activity_chat.*
import kotlinx.android.synthetic.main.layout_header.*
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(), IChatView {

    val presenter = ChatPresenter(this)
    lateinit var mRecyclerView: RecyclerView
    lateinit var username: String

    override fun getLayoutResId(): Int = R.layout.abc_activity_chat

    override fun init() {
        super.init()

        initHeader()
        initEdit()
        initRecyclerView()

        send.setOnClickListener {
            sendMessage()

        }

        edit.setOnEditorActionListener { p0, p1, p2 ->
            sendMessage()
            true
        }

    }

    private fun sendMessage() {
        hideSoftKeyboard()
        val message = edit.text.trim().toString()
        presenter.onSendMessage(username, message)
    }

    private fun initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter= MessageListAdapter(context, presenter.messages)

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
        mRecyclerView.adapter.notifyDataSetChanged()
        mRecyclerView.smoothScrollToPosition(presenter.messages.size)
    }

    override fun onSendMessageSuccess() {
        mRecyclerView.adapter.notifyDataSetChanged()
        mRecyclerView.smoothScrollToPosition(presenter.messages.size)
        toast(getString(R.string.send_message_success))
        edit.text.clear()//清除编辑框
    }

    override fun onSendMessageFailed() {
        toast(getString(R.string.send_message_failed))
        mRecyclerView.adapter.notifyDataSetChanged()
        mRecyclerView.smoothScrollToPosition(presenter.messages.size)
    }

}
