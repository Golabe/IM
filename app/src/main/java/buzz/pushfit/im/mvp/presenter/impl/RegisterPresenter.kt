package buzz.pushfit.im.mvp.presenter.impl

import android.util.Log
import buzz.pushfit.im.extention.isValidPassword
import buzz.pushfit.im.extention.isValidUserName
import buzz.pushfit.im.mvp.presenter.IRegisterPresenter
import buzz.pushfit.im.mvp.view.IRegisterView
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.BmobUser
import com.hyphenate.chat.EMClient

import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by yuequan on 2017/10/27.
 */
class RegisterPresenter(val view: IRegisterView) : IRegisterPresenter {
    val TAG="RegisterPresenter"

    override fun onRegister(username: String, password: String, againPassword: String) {
        if (username.isValidUserName()) {
            if (password.isValidPassword()) {
                if (againPassword.isValidPassword() || password.equals(againPassword)) {

                    view.onStartRegister()

                    register(username, password)

                } else view.onAgainPassword()

            } else view.onPasswordError()

        } else view.onUserNameError()
    }

    private fun register(username: String, password: String) {
        val bu = BmobUser()
        bu.username = username
        bu.setPassword(password)

        //注意：不能用save方法进行注册
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser?, e: BmobException?) {
                if (e == null) {
                    registerEaseMob(username, password)//注册到环信
                } else {
                    //用户名存在
                    if (e.errorCode==202) view.onUserNameExist() else view.onRegisterFailed()
                }
            }
        })
    }

    fun registerEaseMob(username: String, password: String) {

        doAsync {
            try {
                //注册失败会抛出HyphenateException
                EMClient.getInstance().createAccount(username, password)//同步方法
                uiThread { view.onRegisterSuccess() }
            }catch (e:HyphenateException){
                //注册失败
                uiThread { view.onRegisterFailed() }
            }
        }
    }
}