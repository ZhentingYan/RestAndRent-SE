package com.tongjisse.adventure.view.views.UserAuthentication.Registration


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import com.tongjisse.restandrent.R
import com.tongjisse.restandrent.presenter.Presenter
import com.tongjisse.restandrent.presenter.authentication.register.EmailProceedPresenter
import com.tongjisse.restandrent.view.common.BaseFragmentWithPresenter
import com.tongjisse.restandrent.view.common.toast
import com.tongjisse.restandrent.view.main.authentication.register.EmailProceedView
import kotlinx.android.synthetic.main.fragment_register_email.*
import java.util.regex.Pattern


class RegisterEmailFragment : BaseFragmentWithPresenter(), EmailProceedView {
    override val presenter by lazy { EmailProceedPresenter(this) }

    override fun showValidError(error: BmobException?) {
        if (error == null)
            context!!.toast("${etEmail.text.toString()}已经被注册，请更换邮箱再试!")
        else context!!.toast("访问数据存储服务异常，错误信息:${error.message}")
    }

    override fun showValidSuccess() {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val registerPasswordFragment = RegisterPasswordFragment()
        fragmentTransaction.replace(R.id.progressFragment, registerPasswordFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register_email, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textWatcher = object : TextWatcher {
            //Check if email is valid
            override fun afterTextChanged(s: Editable) {
                registrationProceed()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        etEmail.addTextChangedListener(textWatcher)
    }

    fun registrationProceed() {
        if (isValidEmail(etEmail.text.toString().trim { it <= ' ' })) {
            bRegProceed.isEnabled = true
            bRegProceed.setBackgroundResource(R.drawable.reg_proceed_button)
            bRegProceed.setTextColor(Color.parseColor("#ff6666"))
            bRegProceed.setOnClickListener {
                EMAIL = etEmail.text.toString()
                presenter.validateUserEmail(EMAIL)
            }
        } else {
            bRegProceed.isEnabled = false
            bRegProceed.setBackgroundResource(R.drawable.reg_proceed_button_fail)
            bRegProceed.setTextColor(Color.parseColor("#ff6666"))
        }
    }

    companion object {
        lateinit var EMAIL: String

        fun isValidEmail(email: String): Boolean {
            val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            val pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
    }
}
