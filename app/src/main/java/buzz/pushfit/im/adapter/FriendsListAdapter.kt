package buzz.pushfit.im.adapter

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.R
import buzz.pushfit.im.mvp.data.FriendsListItem
import buzz.pushfit.im.mvp.view.activity.ChatActivity
import buzz.pushfit.im.widget.FriendsListItemView
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by yuequan on 2017/10/28.
 */
class FriendsListAdapter(val context: Context, val friendItemList: MutableList<FriendsListItem>) : RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(FriendsListItemView(context))
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {

        val friendListItemView = holder.itemView as FriendsListItemView

        friendListItemView.bindView(friendItemList[position])
        val username = friendItemList[position].userName
        //点击跳转聊天界面
        friendListItemView.setOnClickListener { context.startActivity<ChatActivity>("username" to username) }

        //长按弹出删除好友的对话框
        friendListItemView.setOnLongClickListener {

            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.delete_friend_title))
                    .setMessage(context.getString(R.string.delete_friend_message))
                    .setPositiveButton(context.getString(R.string.delete), DialogInterface.OnClickListener { dialogInterface, i ->
                        deleteFriend(username)
                    })
                    .setNegativeButton(context.getString(R.string.cancel), null)
                    .show()

            true
        }
    }

    private fun deleteFriend(username: String) {


                EMClient.getInstance().contactManager().aysncDeleteContact(username,object : MyEMCallBackAdapter() {
                    override fun onSuccess() {
                        context.runOnUiThread { toast(getString(R.string.delete_friend_success)) }
                    }
                    override fun onError(p0: Int, p1: String?) {
                        context.runOnUiThread { toast(getString(R.string.delete_friend_failed)) }
                    }
                })


    }

    override fun getItemCount(): Int = friendItemList.size

    class FriendsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}