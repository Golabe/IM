package buzz.pushfit.im.mvp.view

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/27.
 */
interface ILoginView:IBaseView {

    fun onUserNameError()
    fun onPasswordError()
    fun onStartLogin()

    fun onLoginSuccess()
    fun onLoginFailed(code:Int,message:String)
}