package buzz.pushfit.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.widget.ConversationListItemView
import com.hyphenate.chat.EMConversation

/**
 * Created by yuequan on 2017/10/30.
 */
class ConversationListAdapter(val context: Context, private val conversations: MutableList<EMConversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val conversationListItemView = holder?.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ConversationViewHolder(ConversationListItemView(context))

    }

    class ConversationViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}
