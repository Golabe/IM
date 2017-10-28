package buzz.pushfit.im.mvp.view.activity


import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import buzz.pushfit.im.R
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.mvp.presenter.impl.LoginPresenter
import buzz.pushfit.im.mvp.view.ILoginView
import kotlinx.android.synthetic.main.abc_activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by yuequan on 2017/10/27.
 */
class LoginActivity : BaseActivity(), ILoginView {
    private val mPresenter = LoginPresenter(this)
    override fun getLayoutResId(): Int = R.layout.abc_activity_login

    override fun init() {
        super.init()
        login.setOnClickListener { login() }
        password.setOnEditorActionListener { _, _, _ ->
            login()
            true
        }

        forgotPassword.setOnClickListener {  }
        newAccount.setOnClickListener { startActivity<RegisterActivity>() }


    }

    //登陆
    fun login() {
        //隐藏软件盘
        hideSoftKeyboard()
        if (hasWriteExternalStoragePermission()){
        val username = username.text.trim().toString()
        val password = password.text.trim().toString()
        mPresenter.onLogin(username, password)
        } else applyWriteExternalStoragePermission()
    }

    //申请写磁盘权限
    private fun applyWriteExternalStoragePermission() {
        var permissions= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permissions,0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            login()
        }else toast(getString(R.string.permission_reject))
    }


    //判断是否有写磁盘权限
    private fun hasWriteExternalStoragePermission(): Boolean {
        var result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result==PackageManager.PERMISSION_GRANTED
    }

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
