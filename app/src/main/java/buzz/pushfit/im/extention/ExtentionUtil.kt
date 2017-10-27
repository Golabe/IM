package buzz.pushfit.im.extention

/**
 * Created by yuequan on 2017/10/27.
 */
fun String.isValidUserName():Boolean=this.matches(Regex("^[a-zA-Z]]\\w{2,6}"))
fun String.isValidPassword():Boolean=this.matches(Regex("^[0-9a-zA-Z]{6,12}"))