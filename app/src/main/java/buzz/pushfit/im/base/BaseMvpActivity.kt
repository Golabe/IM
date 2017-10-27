package buzz.pushfit.im.base

import android.os.Bundle
import java.util.*

/**
 * Created by yuequan on 2017/10/27.
 */
abstract class BaseMvpActivity : BaseActivity() {
    protected var mPresenter: LinkedList<BasePresenter<*>> = LinkedList()
    protected abstract fun onFetchPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFetchPresenter()
    }

    protected fun addPresenter(presenter: BasePresenter<*>) {
        mPresenter.add(presenter)
    }

    override fun onDestroy() {
        for (p: BasePresenter<*> in mPresenter) {
            p.onDetach()
        }
        super.onDestroy()
    }

}