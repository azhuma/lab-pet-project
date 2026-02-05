package kz.lab.petproject.runner;

import lombok.SneakyThrows;

import java.time.Duration;

public class Deadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        var t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println( "t1 lock1" );
                sleep(Duration.ofSeconds(1));

                synchronized (lock2) {
                    System.out.println( "t1 lock2" );
                }
            }
        });

        var t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println( "t2 lock2" );
                sleep(Duration.ofSeconds(1));

                synchronized (lock1) {
                    System.out.println( "t2 lock1" );
                }
            }
        });

        t1.start();
        t2.start();
    }

    @SneakyThrows
    private static void sleep(long millis) {
        Thread.sleep(millis);
    }

    @SneakyThrows
    private static void sleep(Duration duration) {
        sleep(duration.toMillis());
    }
}
