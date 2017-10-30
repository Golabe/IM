package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.mvp.presenter.interfacces.IConversationPresenter
import buzz.pushfit.im.mvp.view.interfaces.IConversationView
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

/**
 * Created by yuequan on 2017/10/30.
 */
class ConversationPresenter(val view: IConversationView) : IConversationPresenter {
    val conversations = mutableListOf<EMConversation>()
    override fun onLoadConversationsData() {
        conversations.clear()
        doAsync {
            //加载会话列表
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            try {
                uiThread { view.onLoadConversationsSuccess() }
            } catch (e: HyphenateException) {
                view.onLoadConversationsFailed()
            }

        }

    }
}
