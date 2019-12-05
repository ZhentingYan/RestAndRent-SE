package com.tongjisse.restandrent.view.main.authentication.register

import cn.bmob.v3.exception.BmobException

interface RegisterProceedView {
    fun showRegisterSuccess(objectId:String?)
    fun showRegisterError(error: BmobException?)
}