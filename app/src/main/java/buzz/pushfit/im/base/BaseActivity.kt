package buzz.pushfit.im.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by yuequan on 2017/10/27.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    open fun init() {

    }
    //返回布局资源id
    abstract fun getLayoutResId(): Int

}