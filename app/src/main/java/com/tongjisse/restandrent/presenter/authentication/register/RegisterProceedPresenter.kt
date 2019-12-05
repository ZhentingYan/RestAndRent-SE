package com.tongjisse.restandrent.presenter.authentication.register

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.tongjisse.adventure.data.bean.UserInfo
import com.tongjisse.restandrent.model.UserInfoDao
import com.tongjisse.restandrent.presenter.BasePresenter
import com.tongjisse.restandrent.view.main.authentication.register.RegisterProceedView

class RegisterProceedPresenter (val view: RegisterProceedView): BasePresenter(){
    val mUserInfoDao= UserInfoDao()
    fun createUserInfo(user:UserInfo){
        mUserInfoDao.createUserInfo(user,object:SaveListener<String>(){
            override fun done(p0: String?, p1: BmobException?) {
                if(p1!=null)
                    view.showRegisterSuccess(p0)
                else view.showRegisterError(p1)
            }
        })
    }
}