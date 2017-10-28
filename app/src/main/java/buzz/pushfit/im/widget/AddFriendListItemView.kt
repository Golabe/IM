package buzz.pushfit.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import buzz.pushfit.im.mvp.data.AddFriendData
import kotlinx.android.synthetic.main.item_add_friend_view.view.*

/**
 * Created by yuequan on 2017/10/28.
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    init {

        View.inflate(context,R.layout.item_add_friend_view,this)
    }

    fun  bindView(addFriendData: AddFriendData) {

        if (addFriendData.isAdded){

        }
        friendName.text=addFriendData.userName
        friendCreateTime.text=addFriendData.createTime


    }
}