package buzz.pushfit.im.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 * Created by yuequan on 2017/10/27.
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context

    //进度条弹窗
    private val progressDialog by lazy {
        ProgressDialog(this)
    }
    //隐藏软键盘
    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE )as InputMethodManager
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
    fun showProgress(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    //隐藏进度
    fun dismissProgress() {
        progressDialog.dismiss()
    }
    //隐藏软键盘
    fun hideSoftKeyboard(){
        inputMethodManager.hideSoftInputFromInputMethod(currentFocus.windowToken,0)
    }

}