package com.whut.www.rxjavademo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by noone on 2017/5/23.
 * 真正的速度是看不见的 !
 * Today is toady , we will go !
 */

public class RxJavaDemo {
    public static void main(String args[]){
        Flowable.just("Hello RxJava").subscribe(System.out::println);
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.print("Subscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.print(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.print("error");
            }

            @Override
            public void onComplete() {
                System.out.print("Complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
        Flowable.just("Hello RxJava")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });
    }
}
