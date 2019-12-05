package com.tongjisse.restandrent.util.application

import android.app.Application
import cn.bmob.v3.Bmob

class RestAndRent : Application(){
    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(this, Constant.BMOB_APP_ID)
    }
}