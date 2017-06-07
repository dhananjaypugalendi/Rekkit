package com.dhananjay.rekkit

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dhananjay on 7/6/17.
 */
open class RxBaseFragment() : Fragment() {

    protected var compositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        compositeDisposable = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
        compositeDisposable.clear()
    }
}