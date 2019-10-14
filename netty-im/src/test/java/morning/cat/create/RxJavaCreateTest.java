package morning.cat.create;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.functions.Supplier;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/10/11 4:16 PM
 */
public class RxJavaCreateTest {

    @Test
    public void test_just() {
        Observable.just("a", "b", "c")
                .map(s -> "hello " + s)
                .subscribe(System.out::println);
    }

    @Test
    public void test_from() {
        String[] array = {"a", "b", "c"};
        Observable.fromArray(array)
                .subscribe(System.out::println);

        List<Integer> list = Arrays.asList(1, 2, 3, 45);
        Observable.fromIterable(list)
                .subscribe(System.out::println);


    }

    @Test
    public void test_fromFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<String> future = executor.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "hello world";
                    }
                }
        );
        Observable.fromFuture(future).subscribe(System.out::println);
    }

    @Test
    public void test_fromPublisher() {
        Observable.fromPublisher(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                subscriber.onNext("fromPublisher");
            }
        }).subscribe(System.out::println);
    }

    @Test
    public void test_create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    observableEmitter.onNext("world");
                    observableEmitter.onComplete();
                    //throw new RuntimeException("this is a question.");
                } catch (Throwable t) {
                    observableEmitter.onNext("error:" + t.toString());
                }

            }
        }).subscribe(System.out::println);
    }

    @Test
    public void test_defer() {
        Observable.defer(new Supplier<ObservableSource<String>>() {

            @Override
            public ObservableSource<String> get() throws Throwable {
                return new ObservableSource<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        observer.onNext("world");
                        observer.onComplete();
                    }
                };
            }
        }).subscribe(System.out::println);
    }

    @Test
    public void test_interval() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void test_range() throws InterruptedException {
        Observable.range(10, 10).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    public void test_timer() throws InterruptedException {
        Observable.timer(1, TimeUnit.SECONDS).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void test6() {
        // 创建一个不发射任何数据但是正常终止的Observable
        Observable o1 = Observable.empty();

        // 创建一个不发射数据也不终止的Observable
        Observable o2 = Observable.never();

        // 创建一个不发射数据以一个错误终止的Observable
        Observable o3 = Observable.error(new RuntimeException("rxjava error."));
    }
}
