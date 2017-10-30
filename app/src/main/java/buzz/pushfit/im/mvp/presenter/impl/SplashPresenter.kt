package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.mvp.presenter.interfacces.ISplashPresenter
import buzz.pushfit.im.mvp.view.interfaces.ISplashView
import com.hyphenate.chat.EMClient

/**
 * Created by yuequan on 2017/10/27.
 */
class SplashPresenter(val view: ISplashView) : ISplashPresenter {


    override fun onCheckLoginStatus() {
        if (isSignIn()) view.onSignIn() else view.onNotSignIn()
    }

    //检查是否登陆环信
    private fun isSignIn(): Boolean = EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}