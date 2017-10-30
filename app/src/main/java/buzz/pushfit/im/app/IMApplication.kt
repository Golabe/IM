package buzz.pushfit.im.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import android.support.v4.app.TaskStackBuilder
import buzz.pushfit.im.BuildConfig
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.EMMessageListenerAdapter
import buzz.pushfit.im.mvp.view.activity.ChatActivity
import cn.bmob.v3.Bmob
import com.hyphenate.chat.*
import org.jetbrains.anko.doAsync

/**
 * Created by yuequan on 2017/10/27.
 */
class IMApplication : Application() {

    companion object {
        lateinit var instance: IMApplication
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val shortMusic by lazy {
        soundPool.load(instance, R.raw.shortmusic, 0)
    }
    val longMusic by lazy {
        soundPool.load(instance, R.raw.longmusic, 0)
    }
    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: List<EMMessage>) {
            super.onMessageReceived(messages)
            doAsync {
                if (isForeground()) {
                    //如果在前台播放短音
                    soundPool.play(longMusic, 1f, 1f, 0, 0, 1f)
                } else {
                    //如果在后台播放长音
                    soundPool.play(shortMusic, 1f, 1f, 0, 0, 1f)
                    showNotification(messages)

                }
            }

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

    private fun showNotification(messages: List<EMMessage>?) {
        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var contentText = getString(R.string.no_text_message)
        messages?.forEach {
            if (it.type == EMMessage.Type.TXT) {
                contentText = (it.body as EMTextMessageBody).message
            }
            val  intent=Intent(this,ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())
            val taskStackBuilder=TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)

            val pendingIntent=taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)

            val notification = Notification.Builder(this)
                    .setContentTitle(getString(R.string.have_new_message))
                    .setContentText(contentText)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .notification
            notificationManager.notify(0,notification)
        }
    }

    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses
                .filter { it.processName == packageName }
                .forEach {
                    //找到App进程
                    return it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                }
        return true
    }
}