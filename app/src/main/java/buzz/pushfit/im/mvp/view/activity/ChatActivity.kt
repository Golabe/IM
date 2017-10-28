package buzz.pushfit.im.mvp.view.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.TextWatcherAdapter
import buzz.pushfit.im.base.BaseActivity
import kotlinx.android.synthetic.main.abc_activity_chat.*
import kotlinx.android.synthetic.main.layout_header.*

class ChatActivity : BaseActivity() {


    override fun getLayoutResId(): Int = R.layout.abc_activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEdit()
    }

    private fun initEdit() {
        edit.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(p0: Editable?) {
                send.isEnabled=!p0.isNullOrEmpty()
            }
        })
    }

    private fun initHeader() {
        val username= intent.getStringExtra("username")
        headerLeft.visibility=View.VISIBLE
        headerLeft.setImageResource(R.mipmap.ic_back_48)
        headerLeft.setOnClickListener { finish() }
        val title= String.format(getString(R.string.chating,username))
        headerTitle.text=title

    }


}
