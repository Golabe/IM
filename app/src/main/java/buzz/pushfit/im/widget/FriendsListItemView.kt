package buzz.pushfit.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R

/**
 * Created by yuequan on 2017/10/28.
 */
class FriendsListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_friends_view,this)
    }
}