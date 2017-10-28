package buzz.pushfit.im.mvp.view.activity

import buzz.pushfit.im.R
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.mvp.presenter.impl.MePresenter
import buzz.pushfit.im.mvp.view.IMeView
import kotlinx.android.synthetic.main.abc_activity_me.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MeActivity : BaseActivity(), IMeView {

    val presenter=MePresenter(this)

    override fun getLayoutResId(): Int = R.layout.abc_activity_me
    override fun init() {
        super.init()
        logout.setOnClickListener { presenter.onLogout() }
    }

    override fun onLogoutSuccess() {
        toast(getString(R.string.logout_success))
        startActivity<RegisterActivity>()
        finish()
    }

    override fun onLogoutError() = toast(getString(R.string.logout_error))

}
