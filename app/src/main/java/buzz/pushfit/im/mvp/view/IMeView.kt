package buzz.pushfit.im.mvp.view

import buzz.pushfit.im.base.IBaseView

/**
 * Created by yuequan on 2017/10/27.
 */
interface IMeView:IBaseView {

    fun onLogoutSuccess()
    fun onLogoutError()

}