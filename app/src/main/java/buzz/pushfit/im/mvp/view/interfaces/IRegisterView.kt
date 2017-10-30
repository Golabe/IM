package buzz.pushfit.im.mvp.view.interfaces

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/27.
 */
interface IRegisterView : IBaseView {

    fun onUserNameError()
    fun onPasswordError()
    fun onAgainPassword()
    fun onStartRegister()
    fun onRegisterSuccess()
    fun onRegisterFailed()
    fun onUserNameExist()
}