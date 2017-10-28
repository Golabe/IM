package buzz.pushfit.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.mvp.data.AddFriendItem
import buzz.pushfit.im.widget.AddFriendListItemView

/**
 * Created by yuequan on 2017/10/28.
 */
class AddFriendAdapter(val context: Context, val addFriendItems: MutableList<AddFriendItem>) : RecyclerView.Adapter<AddFriendAdapter.AddFriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AddFriendViewHolder {
        return AddFriendViewHolder(AddFriendListItemView(context))
    }

    override fun onBindViewHolder(holder: AddFriendViewHolder?, position: Int) {
        val addFriendListItemView = holder?.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFriendItems[position])
    }

    override fun getItemCount(): Int = addFriendItems.size

    class AddFriendViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}