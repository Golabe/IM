package buzz.pushfit.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.item_receive_message_view.view.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by yuequan on 2017/10/28.
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_receive_message_view, this)
    }

    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)

    }
    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type==EMMessage.Type.TXT){
            receiveMessage.text=(emMessage.body as EMTextMessageBody).message
        }else context.toast(context.getString(R.string.not_txt_message))
    }

    private fun  updateTimestamp(emMessage: EMMessage, showTimestamp: Boolean) {
        if (showTimestamp) {
            timestamp.visibility= View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else timestamp.visibility= View.GONE
    }
}