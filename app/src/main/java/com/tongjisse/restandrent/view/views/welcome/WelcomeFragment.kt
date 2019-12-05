package com.tongjisse.restandrent.view.views.Welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tongjisse.adventure.view.views.UserAuthentication.Registration.RegisterNameFragment
import com.tongjisse.restandrent.R
import com.tongjisse.restandrent.view.views.authentication.login.LoginFragment

class WelcomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvLogIn = view.findViewById(R.id.tvLogIn) as TextView


        tvLogIn.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            val logInFragment = LoginFragment()
            fragmentTransaction.replace(R.id.progressFragment, logInFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }

        //Create Account Button
        val bRegister = view.findViewById(R.id.bRegister) as Button
        bRegister.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            val registerNameFragment = RegisterNameFragment()
            fragmentTransaction.replace(R.id.progressFragment, registerNameFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }
}
