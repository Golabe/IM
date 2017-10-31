package buzz.pushfit.im.adapter

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import buzz.pushfit.im.R
import buzz.pushfit.im.mvp.view.activity.ChatActivity
import buzz.pushfit.im.widget.ConversationListItemView
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import org.jetbrains.anko.startActivity

/**
 * Created by yuequan on 2017/10/30.
 */
class ConversationListAdapter(val context: Context, private val conversations: MutableList<EMConversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val conversationListItemView = holder?.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])
        val username = conversations[position].conversationId()
        conversationListItemView.setOnClickListener {
            context.startActivity<ChatActivity>("username" to conversations[position].conversationId())
        }
        conversationListItemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                    .setMessage(context.getString(R.string.delete))
                    .setPositiveButton(R.string.delete) { p0, p1 -> deleteConversation(username) }
                    .setNegativeButton(R.string.cancel,null)
                    .show()
            true
        }
    }

    private fun deleteConversation(username: String?) {
        //删除和某个user会话，如果需要保留聊天记录，传false
        EMClient.getInstance().chatManager().deleteConversation(username, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ConversationViewHolder(ConversationListItemView(context))

    }

    class ConversationViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}
