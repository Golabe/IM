package buzz.pushfit.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.mvp.model.FriendsListItem
import buzz.pushfit.im.mvp.view.activity.ChatActivity
import buzz.pushfit.im.widget.FriendsListItemView
import org.jetbrains.anko.startActivity

/**
 * Created by yuequan on 2017/10/28.
 */
class FriendsListAdapter(val context: Context,val friendItemList: MutableList<FriendsListItem>) : RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(FriendsListItemView(context))
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {

        val friendListItemView=holder.itemView as FriendsListItemView

        friendListItemView.bindView(friendItemList[position])
        val  username= friendItemList[position].userName
        //点击跳转聊天界面
        friendListItemView.setOnClickListener { context.startActivity<ChatActivity>("username" to username) }
    }

    override fun getItemCount():Int=friendItemList.size

    class FriendsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}