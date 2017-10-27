package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.extention.isValidPassword
import buzz.pushfit.im.extention.isValidUserName
import buzz.pushfit.im.mvp.presenter.IRegisterPresenter
import buzz.pushfit.im.mvp.view.IRegisterView

/**
 * Created by yuequan on 2017/10/27.
 */
class RegisterPresenter(val view: IRegisterView) : IRegisterPresenter {

    override fun onRegister(username: String, password: String, againPassword: String) {
        if (username.isValidUserName()) {
            if (password.isValidPassword()) {
                if (againPassword.isValidPassword()||password.equals(againPassword)) {

                    view.onStartRegister()

                } else view.onAgainPassword()

            } else view.onPasswordError()

        } else view.onUserNameError()
    }
}