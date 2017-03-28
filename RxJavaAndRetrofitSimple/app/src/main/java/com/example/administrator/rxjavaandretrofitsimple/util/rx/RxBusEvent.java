package com.example.administrator.rxjavaandretrofitsimple.util.rx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：
 */

public class RxBusEvent {

    private Subject<Object, Object> bus = new SerializedSubject(PublishSubject.create());
    private Map<Class<?>, Object> stickyEvents = new ConcurrentHashMap<>();

    private RxBusEvent() {

    }

    private void rebuild() {
        bus = new SerializedSubject(PublishSubject.create());
        stickyEvents.clear();
    }

    private void postStickyEventImpl(Object event) {
        synchronized (stickyEvents) {
            stickyEvents.put(event.getClass(), event);
        }
        post(event);
    }

    private <T> Observable<T> toObservableStickyImpl(final Class<T> klass) {
        synchronized (stickyEvents) {
            final Object event = stickyEvents.get(klass);
            if (event != null) {
                return bus.ofType(klass).mergeWith(Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        subscriber.onNext(klass.cast(event));
                    }
                }));
            }
            return bus.ofType(klass);
        }
    }

    private static final class Holder {
        private static final RxBusEvent BUS = new RxBusEvent();
    }

    private static RxBusEvent inst() {
        return Holder.BUS;
    }

    public static void post(Object event) {
        inst().bus.onNext(event);
    }

    public static <T> Observable<T> toObservable(Class<T> klass) {
        return inst().bus.ofType(klass);
    }

    public static boolean hasObservers() {
        return inst().bus.hasObservers();
    }

    public static void reset() {
        synchronized (inst()) {
            inst().rebuild();
        }
    }

    public static void postSticky(Object event) {
        inst().postStickyEventImpl(event);
    }

    public static <T> Observable<T> toObservableSticky(Class<T> klass) {
        return inst().toObservableStickyImpl(klass);
    }
}

