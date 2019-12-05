package com.tongjisse.restandrent.presenter.authentication.register

import android.util.Log
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.tongjisse.adventure.data.bean.UserInfo
import com.tongjisse.restandrent.model.UserInfoDao
import com.tongjisse.restandrent.presenter.BasePresenter
import com.tongjisse.restandrent.view.main.authentication.register.EmailProceedView

class EmailProceedPresenter (val view:EmailProceedView):BasePresenter(){
    val mUserInfoDao=UserInfoDao()
    fun validateUserEmail(email:String){
            mUserInfoDao.queryUserInfoByEmail(email,object: FindListener<UserInfo>(){
                override fun done(p0: MutableList<UserInfo>?, p1: BmobException?) {
                    if(p1!=null){
                        Log.e("Bmob",p1.message)
                        view.showValidError(p1)
                    }
                    else {
                        if (p0!!.size==0)
                            view.showValidSuccess()
                        else view.showValidError(null)
                    }
                }
            })
    }
}