package buzz.pushfit.im.mvp.view.interfaces

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/30.
 */
interface IConversationView :IBaseView {

    fun onLoadConversationsSuccess()
    fun onLoadConversationsFailed()

}