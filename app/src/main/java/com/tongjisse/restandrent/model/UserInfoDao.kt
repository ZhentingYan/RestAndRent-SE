package com.tongjisse.restandrent.model

import android.util.Log
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.tongjisse.adventure.data.bean.UserInfo
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.listener.FindListener


class UserInfoDao{
    fun createUserInfo(user:UserInfo,saveListener: SaveListener<String>) {
        var objectId: String?=null
        user.save(saveListener)
    }
    /*
    object : SaveListener<String>() {
            override fun done(p0: String?, p1: BmobException?) {
                if (p1 == null)
                    objectId = p0
                else throw p1
            }
        }
     */

    /**
     * 本方法传入的UserInfo需要包括更新后的用户数据、特别注意需要包括ObjectId
     * @param user：UserInfo
     * @author ZhentingYan
     */
    fun updateUserInfo(user:UserInfo,saveListener: SaveListener<String>) {
        user.save(saveListener)
    }

    /*
     object : SaveListener<String>() {
    override fun done(p0: String?, p1: BmobException?) {
    if (p1 != null)
    throw p1
    }
    }
     */
    fun queryUserInfoByEmail(email:String,findListener: FindListener<UserInfo>){
        var query = BmobQuery<UserInfo>()
        var result:UserInfo?=null
        query.addWhereEqualTo("email",email)
        query.findObjects(findListener)
    }
    /*
    object:FindListener<UserInfo>(){
            override fun done(p0: MutableList<UserInfo>?, p1: BmobException?) {
                if(p1!=null){
                    Log.e("Bmob",p1.message)
                    throw p1
                }
                else if(p0!=null)
                        result=p0.get(0)
            }
        }
     */
}