package buzz.pushfit.im.mvp.view.activity

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import buzz.pushfit.im.R
import buzz.pushfit.im.adapter.AddFriendAdapter
import buzz.pushfit.im.base.BaseActivity
import buzz.pushfit.im.mvp.presenter.impl.AddFriendPresenter
import buzz.pushfit.im.mvp.view.IAddFriendView
import kotlinx.android.synthetic.main.abc_activity_add_friend.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(), IAddFriendView {
    val preseneter = AddFriendPresenter(this)

    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun getLayoutResId(): Int = R.layout.abc_activity_add_friend

    override fun init() {
        super.init()
        between.text = getString(R.string.add_friends)

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        mRecyclerView = findViewById(R.id.recyclerView)

        mSwipeRefreshLayout.apply {
            isRefreshing = true

        }

        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendAdapter(context)
        }

        searchView.apply {
            searchView.isSubmitButtonEnabled=true
            searchView.onActionViewExpanded()
            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    if (query!=null){
                        preseneter.onSearchFriends(query)
                    }else toast(context.getString(R.string.input_search_friend))
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

    }



    override fun onSearchSuccess() {

        dismissProgress()
        toast(getString(R.string.search_success))
        mRecyclerView.adapter.notifyDataSetChanged()

    }

    override fun onSearchFailed() {
        dismissProgress()
        toast(getString(R.string.search_failed))
    }

}
