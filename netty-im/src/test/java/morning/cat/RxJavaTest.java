package morning.cat;

//import io.reactivex.*;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.*;
import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

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
        }).subscribe(new MyObserver2());
    }

    @Test
    public void test2() {
        Observable.just("Hello RxJava")
                .subscribe(new MyObserver2());


    }

    @Test
    public void test3() {
        String[] names = {"hello", "RxJava", "xxx"};
        Observable.fromArray(names)
                .observeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
//                .observeOn(Schedulers.computation())
                .subscribe(new MyObserver2());
    }

    @Test
    public void test3_1() {
        Observable.just("java", "C++", "python")
                .map(t -> Node.builder().id(1).name(t).build())
                .observeOn(Schedulers.single())
                .subscribe(new MyObserver2());

    }

    @Test
    public void test4() {
        Observable.just("java", "C++", "python")
                //.observeOn(Schedulers.io())
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
//                .subscribe(System.out::println);
                .subscribe(new MyObserver2());
    }

    @Test
    public void test5() {
        Flowable.just("java", "C++", "python")
                .flatMap(v -> Flowable.just(v)
                        .subscribeOn(Schedulers.computation())
                        .map(w -> "Hello " + w)
                )
                .subscribe(new MyConsumer());
    }

    @Test
    public void test6() {
        Flowable.just("java", "C++", "python")
                .parallel()
                .runOn(Schedulers.computation())
                .map(w -> "Hello " + w)
                .sequential()
                .subscribe(new MyConsumer());
    }
}

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Node {
    private int id;
    private String name;
}

class MyObserver2<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T s) {
        System.out.print(Thread.currentThread().getName() + " --> ");
        if (s instanceof Node) {
            Node n = (Node) s;
            System.out.println("[ id=" + n.getId() + " & name=" + n.getName() + " ]");
            return;
        }
        System.out.println(s);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("onError");
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}

class MyConsumer<T> implements Consumer<T> {
    @Override
    public void accept(T t) throws Throwable {
        System.out.println(Thread.currentThread().getName() + " --> " + t);
    }
}