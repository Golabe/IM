package buzz.pushfit.im.extention

/**
 * Created by yuequan on 2017/10/27.
 */
fun String.isValidUserName():Boolean= this.length in 2..12
fun String.isValidPassword():Boolean=this.length in 8..12