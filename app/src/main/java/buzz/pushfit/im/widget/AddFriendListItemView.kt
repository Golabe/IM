package buzz.pushfit.im.widget

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.MyEMCallBackAdapter
import buzz.pushfit.im.mvp.data.AddFriendItem
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.item_add_friend_view.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import android.widget.EditText



/**
 * Created by yuequan on 2017/10/28.
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {

        View.inflate(context, R.layout.item_add_friend_view, this)
    }

    fun bindView(addFriendItem: AddFriendItem) {

        //判断是否已经是好友
        if (addFriendItem.isAdded) {
            addFriend.isEnabled = false
            addFriend.text = context.getString(R.string.already_add)
            addFriend.setBackgroundResource(R.color.qq_gray)

        } else {
            addFriend.setTextColor(Color.WHITE)
            addFriend.isEnabled = true
            addFriend.text = context.getString(R.string.add)
            addFriend.setBackgroundResource(R.color.colorPrimary)
        }
        friendName.text = addFriendItem.userName
        friendCreateTime.text = addFriendItem.createTime

        addFriend.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            // 获取布局
            val view = View.inflate(context, R.layout.layout_add_friend_validate, null)
            // 获取布局中的控件
            val cause = view.findViewById<View>(R.id.validateMessage) as EditText

            val send = view.findViewById<View>(R.id.sendValidate)
            val cancel = view.findViewById<View>(R.id.cancelValidate)

            // 设置参数
            builder.setTitle("发送验证信息").setIcon(R.mipmap.ic_launcher)
                    .setView(view)
            // 创建对话框
            val alertDialog = builder.create()

            send.setOnClickListener {
                addFriends(addFriendItem.userName,cause.text.trim().toString())
                alertDialog.dismiss()
            }
            cancel.setOnClickListener { alertDialog.dismiss() }

            alertDialog.show()
        }


    }

    fun addFriends(name: String, cause: String) {
        EMClient.getInstance().contactManager().aysncAddContact(name, cause, object : MyEMCallBackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast("发送请求成功") }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { "发送请求失败" }
            }
        })
    }
}