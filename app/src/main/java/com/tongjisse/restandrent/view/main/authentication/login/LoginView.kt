package com.tongjisse.restandrent.view.main.authentication.login

import cn.bmob.v3.exception.BmobException
import com.tongjisse.adventure.data.bean.UserInfo

interface LoginView {
    fun showLoginError(error:BmobException)
    fun showLoginFailure(status:Int)
    fun showLoginSuccess(user:UserInfo)
}