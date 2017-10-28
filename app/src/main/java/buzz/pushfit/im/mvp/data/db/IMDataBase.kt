package buzz.pushfit.im.mvp.data.db

import buzz.pushfit.im.extention.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by yuequan on 2017/10/28.
 */
class IMDataBase {
    companion object {

        val dataBaseHelper = DataBaseHelper()
        val instance = IMDataBase()
    }

    fun saveContact(contact: Contact) {
        dataBaseHelper.use {
            insert(ContactTablets.NAME, *contact.map.toVarargArray())
        }
    }

    fun getAllContact(): List<Contact> = dataBaseHelper.use{
        select(ContactTablets.NAME).parseList(object : MapRowParser<Contact> {
            override fun parseRow(columns: Map<String, Any?>): Contact = Contact(columns.toMutableMap())
        })
    }

    fun deleteAllContact(){
        dataBaseHelper.use {
            delete(ContactTablets.NAME,null,null)
        }
    }
}