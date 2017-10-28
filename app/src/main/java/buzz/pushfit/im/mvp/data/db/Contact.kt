package buzz.pushfit.im.mvp.data.db

/**
 * Created by yuequan on 2017/10/28.
 */
data class Contact(val map: MutableMap<String, Any?>) {
    val _id by map
    val name by map
}