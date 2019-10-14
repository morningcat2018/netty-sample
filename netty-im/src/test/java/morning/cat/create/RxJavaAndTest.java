package morning.cat.create;

import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.Test;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/10/12 3:34 PM
 */
public class RxJavaAndTest {

    @Test
    public void test_just() {
        Observable.just("a", "b", "c")
//                .join()
                .subscribe(System.out::println);
    }


}
