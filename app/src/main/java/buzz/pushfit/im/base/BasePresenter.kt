package buzz.pushfit.im.base

import android.os.Handler
import android.os.Looper


/**
 * Created by yuequan on 2017/10/27.
 */
interface BasePresenter {

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    //切换ui线程
    fun uiThread(f: () -> Unit) {
        handler.post { f() }
    }
}