package com.tongjisse.restandrent.view.views.Welcome

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tongjisse.restandrent.R
import com.tongjisse.restandrent.view.common.toast
import com.tongjisse.restandrent.utils.SessionManager
import com.tongjisse.restandrent.view.views.Main.MenuActivity

class WelcomeActivity : AppCompatActivity() {
    lateinit var mSessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        mSessionManager = SessionManager(this)
        /**
         * 如果SharedPreferences存在Session信息，则直接登陆
         * 否则，进入欢迎界面
         * @author ZhentingYan
         */
        /*
        if (!mSessionManager.email.equals("")) {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            applicationContext!!.toast("${mSessionManager.firstName + mSessionManager.lastName}，欢迎探索Adventure")
        } else supportFragmentManager.beginTransaction().replace(R.id.progressFragment, WelcomeFragment()).commit()
        */
        supportFragmentManager.beginTransaction().replace(R.id.progressFragment, WelcomeFragment()).commit()
    }
}
