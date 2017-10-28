package buzz.pushfit.im.mvp.view

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/28.
 */
interface IChatView:IBaseView {
    fun onStartSendMessage()
    fun onSendMessageSuccess()
    fun onSendMessageFailed()
}