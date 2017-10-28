package buzz.pushfit.im.mvp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import buzz.pushfit.im.app.IMApplication
import org.jetbrains.anko.db.*

/**
 * Created by yuequan on 2017/10/28.
 */
class DataBaseHelper(ctx: Context = IMApplication.instance)
    : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(ContactTablets.NAME, true
                , ContactTablets.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT
                , ContactTablets.CONTACT to TEXT)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(ContactTablets.NAME, true)
        onCreate(p0)
    }

    companion object {

        val NAME = "im.db"
        val VERSION = 1
    }
}