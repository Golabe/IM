package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.adapter.MyEMCallBackAdapter
import buzz.pushfit.im.mvp.presenter.IChatPresenter
import buzz.pushfit.im.mvp.view.IChatView
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

/**
 * Created by yuequan on 2017/10/28.
 */
class ChatPresenter(val view: IChatView) : IChatPresenter {
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


}