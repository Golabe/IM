package buzz.pushfit.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.widget.FriendsListItemView

/**
 * Created by yuequan on 2017/10/28.
 */
class FriendsListAdapter(val context: Context) : RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(FriendsListItemView(context))
    }

    override fun onBindViewHolder(holder: FriendsViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int =20

    class FriendsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}