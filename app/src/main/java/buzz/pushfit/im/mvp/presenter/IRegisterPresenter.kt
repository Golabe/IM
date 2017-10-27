package buzz.pushfit.im.mvp.presenter

import buzz.pushfit.im.base.BasePresenter


/**
 * Created by yuequan on 2017/10/27.
 */
interface IRegisterPresenter:BasePresenter {
    fun onRegister(username:String,password:String,againPassword:String)
}