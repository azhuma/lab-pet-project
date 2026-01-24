package kz.lab.petflux.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import java.time.Duration;


@Slf4j
public class Merge {

    public static void main(String[] args) {

        //demo1();
        demo2();
        Util.sleepSeconds(3);
    }

    private static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
            //.take(2)
            .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer2()
                .mergeWith(producer1())
                //.mergeWith(producer3())
                .take(5)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                   .transform(Util.fluxLogger("producer1"))
                   .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(10, 20, 30)
                   .transform(Util.fluxLogger("producer2"))
                   .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.just(100, 200, 300)
                   .transform(Util.fluxLogger("producer3"))
                   .delayElements(Duration.ofMillis(10));
    }

}
