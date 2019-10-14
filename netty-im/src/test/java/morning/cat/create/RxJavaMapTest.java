package morning.cat.create;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.GroupedObservable;
import org.junit.jupiter.api.Test;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/10/12 11:18 AM
 */
public class RxJavaMapTest {

    @Test
    public void test1() {
        Observable.just("a", "b", "c")
                .map(s -> "hello " + s)
                .subscribe(System.out::println);
    }


    @Test
    public void test3() {
        // Scan操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据发射。它将函数的结果同第二项数据一起填充给这个函数来产生它自己的第二项数据。它持续进行这个过程来产生剩余的数据序列。这个操作符在某些情况下被叫做accumulator
        Observable.range(1, 100)
                .scan((s, v) -> s + v)
                .subscribe(System.out::println);
    }

    @Test
    public void test4() {
        // 定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。
        // buffer(count)以列表(List)的形式发射非重叠的缓存，每一个缓存至多包含来自原始Observable的count项数据（最后发射的列表数据可能少于count项）
        Observable.just("a", "b", "c", "g", "q")
                .buffer(2)
                .subscribe(System.out::println);
    }

    @Test
    public void test5() {
        // buffer(count, skip)从原始Observable的第一项数据开始创建新的缓存，此后每当收到skip项数据，用count项数据填充缓存：开头的一项和后续的count-1项，它以列表(List)的形式发射缓存，取决于count和skip的值，这些缓存可能会有重叠部分（比如skip < count时），也可能会有间隙（比如skip > count时）。
        Observable.just("a", "b", "c", "g", "q")
                .buffer(2, 3)
                .subscribe(System.out::println);
    }

    @Test
    public void test6() {
        // 定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次发射一项数据
        // Window和Buffer类似，但不是发射来自原始Observable的数据包，它发射的是Observables
        Observable.just("a", "b", "c", "g", "q")
                .window(2)
                .subscribe(System.out::println);
    }

    @Test
    public void test7() {
        // 将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列
        Observable.just("a", "b", "c", "g", "q")
                .groupBy(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Throwable {
                        return Integer.valueOf(s.toCharArray()[0]).intValue();
                    }
                })
                .subscribe(new Consumer<GroupedObservable<Integer, String>>() {
                    @Override
                    public void accept(GroupedObservable<Integer, String> integerStringGroupedObservable) throws Throwable {
                        System.out.println(integerStringGroupedObservable.getKey());
                    }
                });
    }

    @Test
    public void test8() {
        Observable.just("a", "b", "c", "g", "q")
                .flatMap(v -> Observable.just(v).map(w -> "hello " + w))
                .subscribe(System.out::println);
    }
}
