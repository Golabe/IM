package buzz.pushfit.im.mvp.view.activity


import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.MyOnPageChangeListenerAdapter
import buzz.pushfit.im.adapter.NavPagerFragmentAdapter
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.factory.FragmentFactory
import buzz.pushfit.im.mvp.view.fragment.FriendsFragment
import buzz.pushfit.im.mvp.view.fragment.MessageFragment
import buzz.pushfit.im.mvp.view.fragment.SpaceFragment
import kotlinx.android.synthetic.main.abc_activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity
import java.util.*

class MainActivity : BaseActivity() {
    @SuppressLint("StringFormatInvalid")
    val titles = arrayOf("消息","联系人","动态")

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fragment, FragmentFactory.instance.getFragment(item.itemId))
        beginTransaction.commit()
        when(item.itemId){
            R.id.navigation_message ->between.text=titles[0]
            R.id.navigation_friends ->between.text=titles[1]
            R.id.navigation_space ->between.text=titles[2]
        }
        true
    }


    override fun getLayoutResId(): Int = R.layout.abc_activity_main

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun init() {
        super.init()
        between.text=getString(R.string.title_message)
        navigation.itemIconTintList = resources.getColorStateList(R.drawable.nav_menu_text_color, null)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        left.setOnClickListener { startActivity<MeActivity>() }
    }

}
