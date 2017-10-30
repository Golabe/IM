package buzz.pushfit.im.factory

import android.support.v4.app.Fragment
import buzz.pushfit.im.R
import buzz.pushfit.im.mvp.view.fragment.FriendsFragment
import buzz.pushfit.im.mvp.view.fragment.ConversationFragment
import buzz.pushfit.im.mvp.view.fragment.SpaceFragment

/**
 * Created by yuequan on 2017/10/27.
 */
class FragmentFactory private constructor(){
    val conversation by lazy {
        ConversationFragment()
    }

    val  friends by lazy {
        FriendsFragment()
    }

    val space by lazy {
        SpaceFragment()
    }
    companion object {
        val  instance=FragmentFactory()
    }

    fun getFragment(tabId:Int):Fragment?{

        when(tabId){
            R.id.navigation_message ->return conversation
            R.id.navigation_friends ->return friends
            R.id.navigation_space ->return space
        }
        return null
    }
}