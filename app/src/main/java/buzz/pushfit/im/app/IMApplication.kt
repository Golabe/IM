package buzz.pushfit.im.app

import android.app.Application
import buzz.pushfit.im.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * Created by yuequan on 2017/10/27.
 */
class IMApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }
}