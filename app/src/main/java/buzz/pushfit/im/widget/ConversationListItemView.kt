package buzz.pushfit.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import buzz.pushfit.im.R
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.item_conversation_view.view.*
import java.util.*

/**
 * Created by yuequan on 2017/10/30.
 */
class ConversationListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_conversation_view, this)
    }

    fun bindView(emConversation: EMConversation) {

        updateName(emConversation)

        updateLastMessage(emConversation)

        updateTimestamp(emConversation)

        updateUnReadMessage(emConversation)
    }

    private fun updateName(emConversation: EMConversation) {
        userName.text = emConversation.conversationId()
    }

    private fun updateUnReadMessage(emConversation: EMConversation) {
        val unreadMsgCount = emConversation.unreadMsgCount
        when {
            unreadMsgCount > 99 -> unreadCount.text = context.getString(R.string.max_show_message_count)
            unreadMsgCount > 0 -> {
                unreadCount.visibility = View.VISIBLE
                unreadCount.text = unreadMsgCount.toString()
            }
            else -> unreadCount.visibility = View.GONE
        }
    }

    private fun updateTimestamp(emConversation: EMConversation) {
        val timestampString = DateUtils.getTimestampString(Date(emConversation.lastMessage.msgTime))
        timestamp.text = timestampString
    }

    private fun updateLastMessage(emConversation: EMConversation) {
        if (emConversation.lastMessage.type == EMMessage.Type.TXT) {
            val body = emConversation.lastMessage.body as EMTextMessageBody
            lastMessage.text = body.message

        } else lastMessage.text = context.getString(R.string.no_text_message)
    }
}