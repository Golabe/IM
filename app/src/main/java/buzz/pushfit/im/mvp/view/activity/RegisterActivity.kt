package buzz.pushfit.im.mvp.view.activity

import android.view.KeyEvent
import android.widget.TextView
import buzz.pushfit.im.R
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.mvp.presenter.impl.RegisterPresenter
import buzz.pushfit.im.mvp.view.IRegisterView
import kotlinx.android.synthetic.main.abc_activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(), IRegisterView {


    val preseneter = RegisterPresenter(this)

    override fun getLayoutResId(): Int = R.layout.abc_activity_register

    override fun init() {
        super.init()

        register.setOnClickListener { register() }
        againPassword.setOnEditorActionListener { _, _, _ ->
            register()
            true
        }
    }

    private fun register() {
        val username = username.text.trim().toString()
        val password = password.text.trim().toString()
        val againPassword = againPassword.text.trim().toString()
        hideSoftKeyboard()
        preseneter.onRegister(username, password, againPassword)
    }

    override fun onUserNameError() {
        username.error = getString(R.string.username_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onAgainPassword() {
        againPassword.error = getString(R.string.again_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.register_loading))
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        toast(getString(R.string.register_success))
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast(getString(R.string.register_failed))
    }

    override fun onUserNameExist() {
        dismissProgress()
        toast(getString(R.string.username_exist))
    }

}
