package buzz.pushfit.im.mvp.presenter.impl

import android.util.Log
import buzz.pushfit.im.extention.isValidPassword
import buzz.pushfit.im.extention.isValidUserName
import buzz.pushfit.im.mvp.presenter.ILoginPresenter
import buzz.pushfit.im.mvp.view.ILoginView
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

/**
 * Created by yuequan on 2017/10/27.
 */
class LoginPresenter(val view: ILoginView) : ILoginPresenter {
    val TAG = "LoginPresenter"

    override fun onLogin(username: String, password: String) {
        if (username.isValidUserName()) {
            if (password.isValidPassword()) {
                view.onStartLogin()
                onLoginEaseMob(username, password)//登陆环信
            } else view.onPasswordError()

        } else view.onUserNameError()
    }

    //登陆环信
    private fun onLoginEaseMob(username: String, password: String) {
        EMClient.getInstance().login(username, password, object : EMCallBack {
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                Log.d(TAG, "成功")
                uiThread {
                    view.onLoginSuccess()//登陆成功回调
                }
            }

            override fun onProgress(p0: Int, p1: String?) {
            }

            override fun onError(code: Int, message: String?) {
                uiThread {
                    view.onLoginFailed(code, message!!)
                }
            }

        })
    }
}