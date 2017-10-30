package buzz.pushfit.im.mvp.view.interfaces

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/27.
 */
interface ISplashView : IBaseView {

    fun onNotSignIn()//没有登陆ui逻辑
    fun onSignIn()//登陆ui逻辑
}