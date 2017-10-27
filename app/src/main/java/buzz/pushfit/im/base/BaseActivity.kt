package buzz.pushfit.im.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by yuequan on 2017/10/27.
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context
    val progressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        mContext = this
        init()
    }

    open fun init() {

    }

    //返回布局资源id
    abstract fun getLayoutResId(): Int

    //显示进度
    fun showProgress(messgae: String) {
        progressDialog.setMessage(messgae)
        progressDialog.show()
    }

    //隐藏进度
    fun dismissProgress() {
        progressDialog.dismiss()
    }

}