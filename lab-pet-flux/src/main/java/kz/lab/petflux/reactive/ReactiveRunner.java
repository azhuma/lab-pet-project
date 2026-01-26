package kz.lab.petflux.reactive;

import kz.lab.petflux.reactive.pubsub.PublisherImpl;
import kz.lab.petflux.reactive.pubsub.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class ReactiveRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Reactive Runner samples...");
        //demo1();
        //demo2();
        //demo3();
        //demo4();
        //demo5();
        //demo6();
        //demo7();
        //demo8();
        //demo9();
        //demo10();
        //demo11();
    }

    private void demo1() {
        // subscribe не запускает передачу элементов
        var pub = new PublisherImpl();
        var sub = new SubscriberImpl();
        pub.subscribe(sub);
    }

    private void demo2() {
        // делаем запрос на получение 3 элементов
        var pub = new PublisherImpl();
        var sub = new SubscriberImpl();
        pub.subscribe(sub);
        sub.getSubscription().request(3);
    }

    private void demo3() throws InterruptedException {
        // делаем запрос на получение элементов больше 10
        var pub = new PublisherImpl();
        var sub = new SubscriberImpl();
        pub.subscribe(sub);
        sub.getSubscription().request(4);
        Thread.sleep(Duration.ofSeconds(1));
        sub.getSubscription().request(4);
        Thread.sleep(Duration.ofSeconds(1));
        sub.getSubscription().request(4);
        sub.getSubscription().request(4); // pub уже не будет реагировать
    }

    private void demo4() throws InterruptedException {
        // делаем запрос после выхода
        var pub = new PublisherImpl();
        var sub = new SubscriberImpl();
        pub.subscribe(sub);
        sub.getSubscription().request(4);
        Thread.sleep(Duration.ofSeconds(1));
        sub.getSubscription().cancel();
        sub.getSubscription().request(4); // нет реакции
    }

    private void demo5() {
        // пробуем моно с кастомным подписчиком
        var mono = Mono.just("kaspilab"); // заполняем данными
        var sub = new SubscriberImpl();
        mono.subscribe(sub);
        sub.getSubscription().request(10);
        sub.getSubscription().request(2);
    }

    private void demo6() {
        // конфигурим моно стандартными методами
        // идет неявное создание сабскрайбера и запуск реквеста
        var mono = Mono.just(5);
        mono.subscribe(
                x -> log.info("received {}", x),
                err -> log.error(err.getMessage()),
                        () -> log.info("completed"),
                        subscription -> subscription.request(1)
        );
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }

    private void demo7() {
        // есть различные фабричные методы
        // также создали дженерик свбскрайбер с автоматическим вызовом реквеста

        var list = List.of(1, 2, 3);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());

        Mono.fromFuture(ReactiveRunner::getName)
                .subscribe(Util.subscriber()); // смотрим в каком потоке в логе
    }

    private void demo8() {
        Flux.just(1, 2, 3, "hello")
                .subscribe(Util.subscriber());
    }

    private void demo9() {
        // фильтрация, маппинг
        var flux = Flux.just(1,2,3,4,5,6);

        flux.subscribe(Util.subscriber("sub1"));

        flux.filter(i -> i > 7)
                .subscribe(Util.subscriber("sub2"));

        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + "a")
                .subscribe(Util.subscriber("sub3"));
    }

    private void demo10() {
        // дебаг
        // можно дать имена логам

        Flux.range(1, 5)
                .log()
                .map(i -> Util.faker().name().firstName())
                .log()
                .subscribe(Util.subscriber());
    }

    private void demo11() {
        // list вернет результат когда готовы все элементы

        var list = NameGenerator.getNamesList(5);
        System.out.println(list);

        NameGenerator.getNamesFlux(5)
                .subscribe(Util.subscriber());

    }
}
