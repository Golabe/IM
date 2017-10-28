package buzz.pushfit.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import buzz.pushfit.im.mvp.data.FriendsListItem
import kotlinx.android.synthetic.main.item_friends_view.view.*

/**
 * Created by yuequan on 2017/10/28.
 */
class FriendsListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_friends_view, this)
    }

    fun bindView(friendsListItem: FriendsListItem) {

        if (friendsListItem.showFirstLatter) {
            firstLatter.visibility = View.VISIBLE
            firstLatter.text = friendsListItem.firstLatter.toString()
        }else firstLatter.visibility= View.GONE

        userName.text = friendsListItem.userName
    }
}