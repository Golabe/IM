package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.adapter.MyEMCallBackAdapter
import buzz.pushfit.im.mvp.presenter.interfacces.IChatPresenter
import buzz.pushfit.im.mvp.view.interfaces.IChatView
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync

/**
 * Created by yuequan on 2017/10/28.
 */
class ChatPresenter(val view: IChatView) : IChatPresenter {
    companion object {
        val PAGE_SIZE=10
    }

    override fun loadMessage(username: String) {

        doAsync {

            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            messages.addAll(conversation.allMessages)

            uiThread {
                view.onMessageLoad()
            }
        }
    }


    val messages = mutableListOf<EMMessage>()
    override fun onSendMessage(contact: String, message: String) {
        val emdMessage = EMMessage.createTxtSendMessage(message, contact)
        emdMessage.setMessageStatusCallback(object : MyEMCallBackAdapter() {
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMessageFailed() }
            }
        })
        messages.add(emdMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emdMessage)//发送消息

    }

    override fun addMessage(username: String, message: List<EMMessage>) {
        message?.let { messages.addAll(it) }
        //更新消息为已读
        //获取跟联系人的回话，然后标记会话里面的消息为全部已读

        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }
    override fun loadMoreMessage(username: String) {

        doAsync {

            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val msgId = messages[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(msgId, PAGE_SIZE)
            conversation.markAllMessagesAsRead()//标记消息为已读
            messages.addAll(0,loadMoreMsgFromDB)
            uiThread {view.onMoreMessageLoaded(loadMoreMsgFromDB.size)  }
        }
    }

}