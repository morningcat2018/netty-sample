package morning.cat;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/9/29 10:12 AM
 */
public class RxJavaTest {

    @Test
    public void test1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("RxJava-->onNext");
                emitter.onComplete();
                //emitter.onError(new Throwable());
            }
        }).subscribe(new MyObserver());
    }

    @Test
    public void test2() {
        Observable.just("Hello RxJava")
                .subscribe(new MyObserver());


    }

    @Test
    public void test3(){

    }
}

class MyObserver<String> implements Observer<String> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String s) {
        System.out.println("onNext:" + s);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
