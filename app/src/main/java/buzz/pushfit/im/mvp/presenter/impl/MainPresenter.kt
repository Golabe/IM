package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.adapter.MyEMCallBackAdapter
import buzz.pushfit.im.mvp.presenter.IMainPresenter
import buzz.pushfit.im.mvp.view.IMainView
import com.hyphenate.chat.EMClient

/**
 * Created by yuequan on 2017/10/29.
 */
class MainPresenter(val view:IMainView):IMainPresenter {
    override fun onLogout() {
        EMClient.getInstance().logout(true, object : MyEMCallBackAdapter() {
            override fun onSuccess() = uiThread { view.onLogoutSuccess() }
            override fun onError(p0: Int, p1: String?) = uiThread { view.onLogoutError() }

        })
    }
}