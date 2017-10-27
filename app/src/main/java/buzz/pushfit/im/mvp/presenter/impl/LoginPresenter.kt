package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.extention.isValidPassword
import buzz.pushfit.im.extention.isValidUserName
import buzz.pushfit.im.mvp.presenter.ILoginPresenter
import buzz.pushfit.im.mvp.view.ILoginView

/**
 * Created by yuequan on 2017/10/27.
 */
class LoginPresenter(val view: ILoginView) : ILoginPresenter {

    override fun onLogin(username: String, password: String) {
        if (username.isValidUserName()) {
            if (password.isValidPassword()) {
                view.onStartLogin()
                onLoginEaseMob(username, password)//登陆环信
            } else view.onPasswordError()

        } else view.onUserNameError()
    }

    private fun onLoginEaseMob(username: String, password: String) {

    }
}