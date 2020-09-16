package br.ind.siam.alcinolago.maleta.rx

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

abstract class DefaultCompletableObserver : CompletableObserver {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
    }

}
