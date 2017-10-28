package buzz.pushfit.im.extention

/**
 * Created by yuequan on 2017/10/27.
 */
fun String.isValidUserName():Boolean= this.length in 2..12
fun String.isValidPassword():Boolean=this.length in 8..12

fun <K,V>MutableMap<K,V>.toVarargArray():Array<Pair<K,V>> =map {
        Pair(it.key,it.value) }.toTypedArray()

