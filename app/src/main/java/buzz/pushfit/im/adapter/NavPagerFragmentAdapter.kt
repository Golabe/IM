package buzz.pushfit.im.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by yuequan on 2017/10/27.
 */
class NavPagerFragmentAdapter : FragmentPagerAdapter {
    var fragments = LinkedList<Fragment>()


    constructor(fm: FragmentManager?,fragments:LinkedList<Fragment>) : super(fm){
        this.fragments=fragments

    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }


}