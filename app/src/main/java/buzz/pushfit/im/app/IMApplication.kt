package buzz.pushfit.im.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import buzz.pushfit.im.BuildConfig
import buzz.pushfit.im.adapter.EMMessageListenerAdapter
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions

/**
 * Created by yuequan on 2017/10/27.
 */
class IMApplication : Application() {

    companion object {
        lateinit var instance: IMApplication
    }
    val messageListener=object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: List<EMMessage>) {
            super.onMessageReceived(messages)
            //前台播放短音
            //后台播放长音

        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        Bmob.initialize(this, "e452cb800a33ff9a25aa99035dbc7df5")
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun isForeground():Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses
                .filter { it.processName==packageName }
                .forEach { //找到App进程
                    return    it.importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                }
        return false
    }
}