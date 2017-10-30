package buzz.pushfit.im.mvp.presenter

import buzz.pushfit.im.base.BasePresenter
import com.hyphenate.chat.EMMessage

/**
 * Created by yuequan on 2017/10/28.
 */
interface IChatPresenter:BasePresenter {

    fun onSendMessage(contact: String,message:String)
    fun addMessage(username: String, messages: List<EMMessage>)
    fun loadMessage(username: String)
    fun loadMoreMessage(username: String)

}