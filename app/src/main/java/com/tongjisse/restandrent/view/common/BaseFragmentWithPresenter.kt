package com.tongjisse.restandrent.view.common

import android.support.v4.app.Fragment
import com.tongjisse.restandrent.presenter.Presenter

abstract class BaseFragmentWithPresenter : Fragment() {
    abstract val presenter: Presenter
    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}