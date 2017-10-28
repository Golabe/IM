package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.mvp.presenter.IChatPresenter
import buzz.pushfit.im.mvp.view.IChatView

/**
 * Created by yuequan on 2017/10/28.
 */
class ChatPresenter(val view:IChatView):IChatPresenter {
    override fun onSendMessage(contact: String, message: String) {

    }


}