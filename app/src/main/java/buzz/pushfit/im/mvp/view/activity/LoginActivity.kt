package buzz.pushfit.im.mvp.view.activity

import buzz.pushfit.im.R
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.mvp.view.ILoginView
import kotlinx.android.synthetic.main.abc_activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by yuequan on 2017/10/27.
 */
class LoginActivity : BaseActivity(), ILoginView {


    override fun getLayoutResId(): Int = R.layout.abc_activity_login


    override fun onUserNameError() {
        username.error = getString(R.string.username_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        showProgress(getString(R.string.login_loading))
    }

    override fun onLoginSuccess() {
        //隐藏进度条
        dismissProgress()
        //进入主界面
        startActivity<MainActivity>()

        finish()
    }

    override fun onLoginFailed() {
        //隐藏进度条
        dismissProgress()
        toast(getString(R.string.login_failed))
    }

}