package buzz.pushfit.im.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import buzz.pushfit.im.mvp.data.AddFriendItem
import kotlinx.android.synthetic.main.item_add_friend_view.view.*

/**
 * Created by yuequan on 2017/10/28.
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {

        View.inflate(context, R.layout.item_add_friend_view, this)
    }

    fun bindView(addFriendData: AddFriendItem) {

        //判断是否已经是好友
        if (addFriendData.isAdded) {
            addFriend.isEnabled = false
            addFriend.text = context.getString(R.string.already_add)
            addFriend.setBackgroundResource(R.color.qq_gray)

        } else {
            addFriend.setTextColor(Color.WHITE)
            addFriend.isEnabled = true
            addFriend.text = context.getString(R.string.add)
            addFriend.setBackgroundResource(R.color.colorPrimary)
        }
        friendName.text = addFriendData.userName
        friendCreateTime.text = addFriendData.createTime


    }
}