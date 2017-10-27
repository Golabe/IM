package buzz.pushfit.im.mvp.presenter

import buzz.pushfit.im.base.BasePresenter

/**
 * Created by yuequan on 2017/10/27.
 */
interface ILoginPresenter : BasePresenter {

    fun onLogin(username: String, password: String)
}