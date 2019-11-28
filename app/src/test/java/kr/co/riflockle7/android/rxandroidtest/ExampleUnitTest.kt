package kr.co.riflockle7.android.rxandroidtest

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import java.util.function.Function

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun rxJavaTest() {
        Observable.just("one", "two", "three", "four", "five")
            .subscribeOn(Schedulers.newThread())
            .flatMap { data ->
                Observable.just(data).map { data -> "$data * $data" }
            }
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("", it)
            }
    }
}
