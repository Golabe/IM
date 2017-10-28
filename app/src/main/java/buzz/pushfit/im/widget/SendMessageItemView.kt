package buzz.pushfit.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.item_send_message_view.view.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by yuequan on 2017/10/28.
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_send_message_view, this)
    }

    fun bindView(emMessage: EMMessage) {
        updateTimestamp(emMessage)
        updateMessage(emMessage)
        updateProgress(emMessage)

    }

    private fun  updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when(it){
                EMMessage.Status.INPROGRESS ->{
                    sendProgress.visibility= View.VISIBLE
                    send_failed.visibility= View.GONE
                }
                EMMessage.Status.SUCCESS->{
                    sendProgress.visibility= View.GONE
                    send_failed.visibility= View.GONE
                }
                EMMessage.Status.FAIL->{
                    sendProgress.visibility= View.GONE
                    send_failed.visibility= View.VISIBLE
                }
            }
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type==EMMessage.Type.TXT){
            sendMessage.text=(emMessage.body as EMTextMessageBody).message
        }else context.toast(context.getString(R.string.not_txt_message))
    }

    private fun  updateTimestamp(emMessage: EMMessage) {

        timestamp.text=DateUtils.getTimestampString(Date(emMessage.msgTime))
    }
}