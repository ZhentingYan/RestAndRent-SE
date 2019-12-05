package com.tongjisse.restandrent.view.views.authentication.login

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
import com.tongjisse.restandrent.presenter.authentication.login.LoginProceedPresenter
import com.tongjisse.restandrent.utils.SessionManager
import com.tongjisse.restandrent.view.common.BaseFragmentWithPresenter
import com.tongjisse.restandrent.view.common.toast
import com.tongjisse.restandrent.view.main.authentication.login.LoginView
import com.tongjisse.restandrent.view.views.Main.MenuActivity
import com.tongjisse.restandrent.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragmentWithPresenter(), LoginView {
    override val presenter by lazy { LoginProceedPresenter(this) }
    lateinit var mSessionManager: SessionManager
    override fun showLoginSuccess(user: UserInfo) {
        mSessionManager.createLoginSession(user.objectId,user.email, user.first_name, user.last_name, user.age,user.phone_num)
        val intent = Intent(activity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        context!!.toast("${mSessionManager.firstName + mSessionManager.lastName}，欢迎探索Rest&Rent")
    }

    override fun showLoginError(error: BmobException) {
         context!!.toast("访问数据存储服务异常，错误信息:${error.message}")
    }

    override fun showLoginFailure(status: Int) {
        if(status==0)
            context!!.toast("此邮箱还没有注册哦，请先注册Rest&Rent")
        else context!!.toast("邮箱与密码不匹配，请重新尝试登陆！")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mSessionManager = SessionManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                logInProceed()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        bRegProceed.setOnClickListener {
            presenter.loginUser(etEmail.text.toString(), etPassword.text.toString())
        }
    }


    fun logInProceed() {
        if (etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()) {
            bRegProceed.isEnabled = false
            bRegProceed.setBackgroundResource(R.drawable.reg_proceed_button_fail)
            bRegProceed.setTextColor(Color.parseColor("#ff6666"))
        } else {
            bRegProceed.isEnabled = true
            bRegProceed.setBackgroundResource(R.drawable.reg_proceed_button)
            bRegProceed.setTextColor(Color.parseColor("#ff6666"))

        }
    }
}
