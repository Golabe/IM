package buzz.pushfit.im.mvp.view

import android.os.Handler
import buzz.pushfit.im.AMainActivity
import buzz.pushfit.im.R
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.mvp.presenter.impl.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 * Created by yuequan on 2017/10/27.
 */
class ASplashActivity : BaseActivity(), ISplashView {
    private val presenter=SplashPresenter(this)
    companion object {
        val DELAY = 2000L//延迟时间
    }

    val handler by lazy {
        Handler()
    }

    override fun getLayoutResId(): Int = R.layout.abc_activity_splash

    override fun init() {
        super.init()

        presenter.onCheckLoginStatus()
    }

    //跳转登录界面
    override fun onNotSignIn() {
        handler.postDelayed({
            startActivity<ALoginActivity>()
            finish()
        }, DELAY)

    }

    //跳转主界面
    override fun onSignIn() {
        startActivity<AMainActivity>()
        finish()
    }
}