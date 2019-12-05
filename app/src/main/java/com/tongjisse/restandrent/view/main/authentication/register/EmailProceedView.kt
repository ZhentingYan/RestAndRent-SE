package com.tongjisse.restandrent.view.main.authentication.register

import cn.bmob.v3.exception.BmobException

interface EmailProceedView {
    fun showValidSuccess()
    fun showValidError(error:BmobException?)
}