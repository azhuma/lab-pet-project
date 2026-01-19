package kz.lab.petproject.runner;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


@Slf4j
@Component
public class ConcurrencyRunner implements CommandLineRunner {

    private static boolean flag = true;

    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {
        //demoSimpleRunnable();
        //demoThreadPool();
        //demoLostUpdate();
        //demoStream();
        //demoCompletableFuture();
    }

    @SneakyThrows
    private void sleep(long millis) {
        Thread.sleep(millis);
    }

    @SneakyThrows
    private void sleep(Duration duration) {
        sleep(duration.toMillis());
    }

    @SneakyThrows
    private void demoSimpleRunnable() {
        // Runnable - функциональный интерфейс
        // самая ранняя реализация потоков
        // главный минус - не возвращает напрямую результат
        // что надо делать, чтобы вернуть результат в основной поток?

        System.out.println("demoSimpleRunnable started");
        Runnable job = () -> {
            System.out.println("thread started");
            sleep(Duration.ofSeconds(3));
            System.out.println("thread finished");
        };
        Thread thread = new Thread(job);
        thread.start();

        System.out.println("doing something else in main thread: 1");
        Thread.sleep(Duration.ofSeconds(2));
        System.out.println("doing something else in main thread: 2");

        //thread.join();  // блокирующая операция - ожидание завершения потока
        System.out.println("demoSimpleRunnable finished");
    }

    private Integer doSomething() {
        int i = new java.util.Random().nextInt(10);
        sleep(10);
        System.out.println("thread finished: " + i);
        return i;
    }

    @SneakyThrows
    private void demoThreadPool() {
        // ExecutorService - это пул потоков
        // Есть разные фабричные методы для создания пула включая кастомный
        // Сабмитим интерфейс Callable
        // В отличии от Runnable теперь можно вернуть результат
        // Результат получаем во Future

        List<Future<Integer>> futures = new ArrayList<>();

        Instant start = Instant.now();
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(() -> System.out.println("tick"), 0, 1, TimeUnit.SECONDS);

        try (ExecutorService threadPool = Executors.newSingleThreadExecutor()) {
        //try (ExecutorService threadPool = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 10; i++) {
                futures.add(threadPool.submit(this::doSomething));
            }
            sleep(50);
            for (var future : futures) {
                System.out.println(future.isDone());
                if (!future.isDone()) {
                    future.cancel(true);
                } else {
                    System.out.println(future.get()); // блокирует основной поток
                }
            }
        }

//        for (var future : futures) {
//            System.out.println("x: " + future.get());
//        }

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time elapsed: " + timeElapsed.toMillis());
    }

    @Getter
    private static class MyThread implements Runnable {
        private int value = 0;
        //private AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                increment();
                Thread.yield();
            }
        }

        // not synchronized
        public void increment() {
            int i = value;
            value = i + 1;
            //value++;
            //atomicInteger.getAndIncrement();
        }
    }

    @SneakyThrows
    private void demoLostUpdate() {
        MyThread myThread = new MyThread();
        var t1 = new Thread(myThread, "t1");
        var t2 = new Thread(myThread, "t2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(myThread.getValue());
        //System.out.println(myThread.getAtomicInteger());
    }

    private void demoStream() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream().parallel().forEach(value -> System.out.println(Thread.currentThread().getName() + ":" + value));

        MyThread myThread = new MyThread();
        IntStream.range(1, 1000)
                .parallel()
                .unordered()
                .forEach(value -> myThread.increment());
        System.out.println(myThread.getValue());
    }

    private final ExecutorService pool = Executors.newFixedThreadPool(10);

    private Future<String> executeAsync() {
        return pool.submit(() -> {
            System.out.println("executing task");
            sleep(Duration.ofSeconds(3));
            return "done";
        });
    }

    private Future<String> executeAsyncCompletable() {
        CompletableFuture<String> future = new CompletableFuture<>();

        pool.submit(() -> {
            System.out.println("executing task");
            sleep(Duration.ofSeconds(3));
            future.complete("done");

            System.out.println("executing another task");
            sleep(Duration.ofSeconds(3));
            System.out.println("done 2");
        });

        return future;
    }

    @SneakyThrows
    private void demoCompletableFuture() {
        // Future возвращает результат
        // Completable Future позволяет строить цепочки, комбинации итд

        // 1
        Future<String> future = executeAsync();
        //Future<String> future = executeAsyncCompletable();
        System.out.println(future.get());
        System.out.println("continue running main thread...");

        // 2
//        System.out.println("starting async task from main thread: " + Thread.currentThread().getName());
//        CompletableFuture<Void>  t1 = CompletableFuture.runAsync(() -> {
//            System.out.println("running async task in thread: " + Thread.currentThread().getName());
//        });
//
//        CompletableFuture<String>  t2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("running async task in thread: " + Thread.currentThread().getName());
//            return "done"; // надо вернуть T
//        });
//        System.out.println(t2.get());

        // 3
        // chain in fluent api - Completion Stage API
        var t = CompletableFuture
                .supplyAsync(() -> "done 1")
                .thenApplyAsync(x -> x + " 2")
                .thenApplyAsync(x -> x + " 3")
                .thenApplyAsync(x -> x + " 4")
                .thenApplyAsync(x -> x + " 5");
        System.out.println("result=" + t.get());

        // 4
        // еще пример
//        var cf1 = CompletableFuture.supplyAsync(() -> "cf 1");
//        var cf2 = CompletableFuture.supplyAsync(() -> "cf 2");
//
//        var cf3 = cf1.thenComposeAsync(x -> cf2);
//        System.out.println(cf3.get());
//
//        var cf4 = cf1.thenComposeAsync(
//                x -> CompletableFuture.supplyAsync(() -> x + " cf 2")
//        );
//        System.out.println(cf4.get());
    }
}
