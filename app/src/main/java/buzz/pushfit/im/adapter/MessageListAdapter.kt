package buzz.pushfit.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.widget.ReceiveMessageItemView
import buzz.pushfit.im.widget.SendMessageItemView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

/**
 * Created by yuequan on 2017/10/28.
 */
class MessageListAdapter(val context: Context, private val messages: List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].direct() == EMMessage.Direct.SEND) ITEM_TYPE_SEND_MESSAGE else ITEM_TYPE_RECEIVE_MESSAGE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val showTimestamp = isShowTimestamp(position)

        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val sendMessageItemView = holder?.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position],showTimestamp)
        } else {
            val receiveMessageItemView = holder?.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[position],showTimestamp)
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        //如果第一条消息或者和第一条间隔时间比较长显示
        var showTimestamp = true
        if (position > 0) {
            showTimestamp = ! DateUtils.isCloseEnough(messages[position].msgTime, messages[position - 1].msgTime)
        }
        return showTimestamp

    }


    override fun getItemCount(): Int = messages.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_SEND_MESSAGE) SendMessageViewHolder(SendMessageItemView(context)) else ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    class SendMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
    class ReceiveMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}
