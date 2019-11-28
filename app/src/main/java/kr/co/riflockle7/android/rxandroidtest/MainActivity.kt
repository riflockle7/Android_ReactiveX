package kr.co.riflockle7.android.rxandroidtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    var count = 0

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val disposable = Observable.just("one", "two", "three", "four", "five")
//            .subscribeOn(Schedulers.newThread())
//            .flatMap { data ->
//                Observable.create<String> {
//                    it.onComplete()
//                    it.onNext("$data * $data")
//                }
//                // Observable.just(data).map { data -> "$data * $data" }
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d("RxAndroid Example : ", it)
//            }
//
//        compositeDisposable.add(disposable)

        // Cold Observable
//         val gugudanObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
        // Hot Observable
        val gugudanObservable = PublishSubject.create<Int>()

        val gugudanDisposable = gugudanObservable
            .subscribeOn(Schedulers.newThread())
            .flatMap { num ->
                Observable.create<String> {
                    for (row in 1..9) {
                        it.onNext("$num * $row = ${num * row}")
                    }
                    it.onComplete()
                }
            }
            // .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("publish Subject : ", it)
                tv_text.text = it
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }

        compositeDisposable.add(gugudanDisposable)


        btn_print_gugu.setOnClickListener {
            // Hot Observable
//            gugudanObservable.onNext(count++)
//
//            val gugudanDisposable2 = gugudanObservable
//                .subscribeOn(Schedulers.io())
//                .flatMap { num ->
//                    Observable.create<String> {
//                        for (row in 1..9) {
//                            it.onNext("$num * $row = ${num * row}")
//                        }
//                        it.onComplete()
//                    }
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    Log.d("publish Subject ${count} : ", it)
//                }
//
//            compositeDisposable.add(gugudanDisposable2)
        }

        btn_print_gugu_func.setOnClickListener {
            gugudanObservable.onNext(count++)

//            val gugudanDisposable2 = gugudanObservable
//                .subscribeOn(Schedulers.io())
//                .flatMap(
//                    object : Function<Int, Observable<String>> {
//                        override fun apply(num: Int): Observable<String> {
//                            var array = arrayListOf<String>()
//                            for (row in 1..9) {
//                                array.add("$num * $row = ${num * row}")
//                            }
//                            return Observable.fromIterable(array)
//                        }
//                    }
//                )
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    Log.d("publish Subject_func ${count} : ", it)
//                }
//
//            compositeDisposable.add(gugudanDisposable2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
    }
}
