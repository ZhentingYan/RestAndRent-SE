package com.tongjisse.restandrent.view.common

import android.support.v7.app.AppCompatActivity
import com.tongjisse.restandrent.presenter.Presenter

abstract class BaseActivityWithPresenter : AppCompatActivity() {
    abstract val presenter: Presenter
    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}