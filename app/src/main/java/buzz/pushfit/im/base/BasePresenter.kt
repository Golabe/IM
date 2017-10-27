package buzz.pushfit.im.base

import android.view.View

/**
 * Created by yuequan on 2017/10/27.
 */
class BasePresenter<V :IBaseView> {
    lateinit var mView: View

    constructor(view: View) {
        this.mView = view
    }

    fun  onDetach(){
        mView= null!!
    }
}