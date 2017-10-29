package buzz.pushfit.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.widget.ReceiveMessageItemView
import buzz.pushfit.im.widget.SendMessageItemView
import com.hyphenate.chat.EMMessage

/**
 * Created by yuequan on 2017/10/28.
 */
class MessageListAdapter(val context: Context, val messages: List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position].direct() == EMMessage.Direct.SEND) return ITEM_TYPE_SEND_MESSAGE else return ITEM_TYPE_RECEIVE_MESSAGE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (getItemViewType(position)== ITEM_TYPE_SEND_MESSAGE){
            val sendMessageItemView = holder?.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position])
        }else{
            val receiveMessageItemView = holder?.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[position])
        }
    }


    override fun getItemCount(): Int = messages.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType== ITEM_TYPE_SEND_MESSAGE) return SendMessageViewHolder(SendMessageItemView(context)) else return ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    class SendMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
    class ReceiveMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}