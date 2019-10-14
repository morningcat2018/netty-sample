package morning.cat;

//import io.reactivex.*;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/10/9 5:03 PM
 */
public class RxJava2 {

    @Test
    public void test() {
        Observable.fromArray("a", "b", "c").subscribe(System.out::println);

        Integer[] list = {5, 6, 7, 8};
        Observable.fromArray(list).subscribe(System.out::println);

        Observable.just("one object").subscribe(System.out::println);
    }

    @Test
    public void test2() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

    @Test
    public void test3() {
        Flowable<Integer> flow = Flowable.range(1, 10)
                .map(v -> v * v)
                .filter(v -> v % 2 == 0);
        flow.subscribe(System.out::println);
    }

    @Test
    public void test4() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 11 == 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
                for (int i = 0; i < 1000; i++) {
                    System.out.print("");
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void test5() throws InterruptedException {
        Flowable
                .fromCallable(() -> {
                    Thread.sleep(1000); //  imitate expensive computation
                    return "Done";
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish
    }
}
