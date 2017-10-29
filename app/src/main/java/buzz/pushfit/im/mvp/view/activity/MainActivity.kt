package buzz.pushfit.im.mvp.view.activity


import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import buzz.pushfit.im.R
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.factory.FragmentFactory
import kotlinx.android.synthetic.main.abc_activity_main.*
import kotlinx.android.synthetic.main.layout_content_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener {
    @SuppressLint("StringFormatInvalid")
    val titles = arrayOf("消息", "联系人", "动态")

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fragment, FragmentFactory.instance.getFragment(item.itemId))
        beginTransaction.commit()
        when (item.itemId) {
            R.id.navigation_message -> {
                between.text = titles[0]
                right.visibility = View.GONE
            }
            R.id.navigation_friends -> {
                between.text = titles[1]
                right.visibility=View.VISIBLE
                right.setBackgroundResource(R.mipmap.ic_add_48)
                right.setOnClickListener { startActivity<AddFriendActivity>() }
            }
            R.id.navigation_space -> {
                between.text = titles[2]
                right.visibility=View.GONE
            }
        }
        true
    }


    override fun getLayoutResId(): Int = R.layout.abc_activity_main

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun init() {
        super.init()
        between.text = getString(R.string.title_message)
        navigation.itemIconTintList = resources.getColorStateList(R.drawable.nav_menu_text_color, null)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        left.setOnClickListener { startActivity<MeActivity>() }

        initDrawer()
    }

    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
