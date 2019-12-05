package com.tongjisse.restandrent.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


object KeyboardUtil {
    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

}
