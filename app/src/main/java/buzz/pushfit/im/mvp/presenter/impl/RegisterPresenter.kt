package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.extention.isValidPassword
import buzz.pushfit.im.extention.isValidUserName
import buzz.pushfit.im.mvp.presenter.IRegisterPresenter
import buzz.pushfit.im.mvp.view.IRegisterView
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.BmobUser


/**
 * Created by yuequan on 2017/10/27.
 */
class RegisterPresenter(val view: IRegisterView) : IRegisterPresenter {

    override fun onRegister(username: String, password: String, againPassword: String) {
        if (username.isValidUserName()) {
            if (password.isValidPassword()) {
                if (againPassword.isValidPassword() || password.equals(againPassword)) {

                    view.onStartRegister()

                    register(username, password)

                } else view.onAgainPassword()

            } else view.onPasswordError()

        } else view.onUserNameError()
    }

    private fun register(username: String, password: String) {
        val bu = BmobUser()
        bu.username = username
        bu.setPassword(password)

        //注意：不能用save方法进行注册
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser, e: BmobException?) {
                if (e == null) {
                    view.onRegisterSuccess()
                } else {
                    view.onRegisterFailed()
                }
            }
        })
    }
}