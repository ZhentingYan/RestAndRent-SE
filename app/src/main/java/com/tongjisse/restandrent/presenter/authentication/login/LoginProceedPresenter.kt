package com.tongjisse.restandrent.presenter.authentication.login

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.tongjisse.adventure.data.bean.UserInfo
import com.tongjisse.restandrent.model.UserInfoDao
import com.tongjisse.restandrent.presenter.BasePresenter
import com.tongjisse.restandrent.view.main.authentication.login.LoginView

class LoginProceedPresenter (val view:LoginView):BasePresenter(){
    val mUserInfoDao=UserInfoDao()
    fun loginUser(email:String,password:String){
        mUserInfoDao.queryUserInfoByEmail(email, object:FindListener<UserInfo> (){
            override fun done(p0: MutableList<UserInfo>?, p1: BmobException?) {
                if(p1!=null)
                    view.showLoginError(p1)
                else{
                    if(p0!!.size==0)
                        view.showLoginFailure(0)
                    else{
                        val user=p0[0]
                        if(!user.password.equals(password))
                            view.showLoginFailure(1)
                        else view.showLoginSuccess(user)
                    }

                }
            }
        })
    }
}