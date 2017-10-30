package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.adapter.MyEMCallBackAdapter
import buzz.pushfit.im.mvp.presenter.interfacces.IMePresenter
import buzz.pushfit.im.mvp.view.interfaces.IMeView
import com.hyphenate.chat.EMClient

/**
 * Created by yuequan on 2017/10/27.
 */
class MePresenter(val view: IMeView) : IMePresenter {

    override fun onLogout() {
        EMClient.getInstance().logout(true, object : MyEMCallBackAdapter() {
            override fun onSuccess() = uiThread { view.onLogoutSuccess() }


            override fun onError(p0: Int, p1: String?) = uiThread { view.onLogoutError() }

        })
    }
}