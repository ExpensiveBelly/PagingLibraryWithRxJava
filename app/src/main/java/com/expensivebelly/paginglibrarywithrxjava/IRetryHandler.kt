@file:Suppress("DEPRECATION")

package com.expensivebelly.paginglibrarywithrxjava

import android.content.Context
import android.net.NetworkInfo
import android.util.Log
import com.github.pwittchen.reactivenetwork.library.rx2.ConnectivityPredicate
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.io.IOException

class RetryHandler(private val context: Context) {

    fun handle(errors: Flowable<Throwable>): Flowable<Unit> {
        return errors.flatMap { throwable ->
            Log.d(
                this.javaClass::getCanonicalName.toString(),
                "retryWhen $throwable"
            )
            Flowable.defer {
                ReactiveNetwork
                    .observeNetworkConnectivity(context)
                    .filter(ConnectivityPredicate.hasState(NetworkInfo.State.CONNECTED))
                    .toFlowable(BackpressureStrategy.LATEST)
                    .flatMap {
                        if (throwable is IOException) Flowable.just(Unit) else Flowable.error(
                            throwable
                        )
                    }
            }
        }
    }
}