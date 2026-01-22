package kz.lab.petflux.reactive;

import com.github.javafaker.Faker;
import kz.lab.petflux.reactive.pubsub.GenericSubscriber;
import org.reactivestreams.Subscriber;

import java.time.Duration;


public class Util {
    private static final Faker faker = Faker.instance();


    public static <T> Subscriber<T> subscriber() {
        return new GenericSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new GenericSubscriber<>(name);
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}