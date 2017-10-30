package buzz.pushfit.im.mvp.data.db

/**
 * Created by yuequan on 2017/10/29.
 */
data class Userinfo (private val map:MutableMap<String,Any?>){
    val _id by map
    val userId by map //用户id
    val username by map //用户名
    val userLogo by map //用户头像
    val signature by map //个性签名

}