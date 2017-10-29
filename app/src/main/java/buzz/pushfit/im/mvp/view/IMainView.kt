package buzz.pushfit.im.mvp.view

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/29.
 */
interface IMainView:IBaseView {

    fun onLogoutSuccess()
    fun onLogoutError()
}