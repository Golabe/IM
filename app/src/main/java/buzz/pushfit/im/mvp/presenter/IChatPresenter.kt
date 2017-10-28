package buzz.pushfit.im.mvp.presenter

import buzz.pushfit.im.base.BasePresenter

/**
 * Created by yuequan on 2017/10/28.
 */
interface IChatPresenter:BasePresenter {

    fun onSendMessage(contact: String,message:String)
}