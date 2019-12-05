package com.tongjisse.restandrent.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : Presenter {
    protected var subscription = CompositeDisposable()

    override fun onViewDestroyed() {
        subscription.dispose()
    }
}