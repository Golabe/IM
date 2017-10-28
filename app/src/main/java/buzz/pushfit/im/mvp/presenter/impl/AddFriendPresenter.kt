package buzz.pushfit.im.mvp.presenter.impl

import buzz.pushfit.im.mvp.data.AddFriendData
import buzz.pushfit.im.mvp.presenter.IAddFriendPresenter
import buzz.pushfit.im.mvp.view.IAddFriendView

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.BmobUser
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync


/**
 * Created by yuequan on 2017/10/28.
 */
class AddFriendPresenter(val view: IAddFriendView) : IAddFriendPresenter {

    val addFriendItems = mutableListOf<AddFriendData>()
    override fun onSearchFriends(searchFriend: String) {
        val query = BmobQuery<BmobUser>()

        doAsync {
            query.addWhereEqualTo("username", searchFriend)
                    .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)

            query.findObjects(object : FindListener<BmobUser>() {

                override fun done(p0: List<BmobUser>, e: BmobException?) {
                    if (e == null) {
                        // toast("查询用户成功:" + `object`.size)
                        uiThread {
                            //处理数据
                            p0?.forEach {
                                val addFriendItem = AddFriendData(it.username, it.createdAt)
                                addFriendItems.add(addFriendItem)
                            }
                            view.onSearchSuccess()
                        }
                    } else {
                        // toast("更新用户信息失败:" + e.message)
                        uiThread {
                            view.onSearchFailed()
                        }
                    }
                }
            })
        }

    }
}