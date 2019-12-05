package com.tongjisse.restandrent.view.views.UserAuthentication.Registration

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.exception.BmobException
import com.tongjisse.adventure.data.bean.UserInfo
import com.tongjisse.adventure.view.views.UserAuthentication.Registration.RegisterAgeFragment
import com.tongjisse.adventure.view.views.UserAuthentication.Registration.RegisterEmailFragment
import com.tongjisse.adventure.view.views.UserAuthentication.Registration.RegisterNameFragment
import com.tongjisse.adventure.view.views.UserAuthentication.Registration.RegisterPasswordFragment
import com.tongjisse.restandrent.R
import com.tongjisse.restandrent.presenter.authentication.register.RegisterProceedPresenter
import com.tongjisse.restandrent.utils.SessionManager
import com.tongjisse.restandrent.view.common.BaseFragmentWithPresenter
import com.tongjisse.restandrent.view.common.toast
import com.tongjisse.restandrent.view.main.authentication.register.RegisterProceedView
import com.tongjisse.restandrent.view.views.Main.MenuActivity
import com.tongjisse.restandrent.view.views.Welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_phone_num.*
import java.sql.SQLException
import java.util.regex.Pattern

/**
 * Created by Owner on 2017-07-21.
 */

class PhoneNumFragment : BaseFragmentWithPresenter(), RegisterProceedView {
    override val presenter by lazy { RegisterProceedPresenter(this) }
    var userInfo=UserInfo()
    lateinit var mSessionManager: SessionManager

    override fun showRegisterError(error: BmobException?) {
        context!!.toast("访问数据存储服务异常，错误信息:${error!!.message}")
    }

    override fun showRegisterSuccess(objectId: String?) {
        mSessionManager.createLoginSession(objectId,userInfo.email, userInfo.first_name, userInfo.last_name, userInfo.age,userInfo.phone_num)
        val intent = Intent(activity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        context!!.toast("${userInfo.first_name + userInfo.last_name},欢迎探索Adventure!")
    }

    fun proceed() {
        if (etPhone.text.length > 10) {
            if (isValidPhoneNum(etPhone.text.toString())) {
                bRegProceed.isEnabled = true
                bRegProceed.setBackgroundResource(R.drawable.reg_proceed_button)
                bRegProceed.setTextColor(Color.parseColor("#ff6666"))
            }

        } else {
            bRegProceed.isEnabled = false
            bRegProceed.setBackgroundResource(R.drawable.reg_proceed_button_fail)
            bRegProceed.setTextColor(Color.parseColor("#ff6666"))
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_phone_num, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mSessionManager = SessionManager(context)
        bRegProceed.setOnClickListener {
            //Generate random num
            PHONENUM = etPhone.text.toString()
            // Add to database
            userInfo.age=RegisterAgeFragment.AGE
            userInfo.email=RegisterEmailFragment.EMAIL
            userInfo.first_name=RegisterNameFragment.FIRST_NAME
            userInfo.last_name=RegisterNameFragment.LAST_NAME
            userInfo.password=RegisterPasswordFragment.PASSWORD
            userInfo.phone_num= PHONENUM
            presenter.createUserInfo(userInfo)
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                proceed()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        etPhone.addTextChangedListener(textWatcher)
    }

    fun isValidPhoneNum(PHONENUM: String): Boolean {
        val PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$"
        val pattern = Pattern.compile(PHONE_PATTERN)
        val matcher = pattern.matcher(PHONENUM)
        return matcher.matches()
    }

    companion object {
        lateinit var PHONENUM: String
    }
}