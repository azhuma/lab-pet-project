package kz.lab.petflux.reactive;

import io.lettuce.core.RedisAsyncCommandsImpl;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisDemo {

    public static void main(String[] args) throws InterruptedException {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> sync = connection.sync();

        sync.set("foo", "bar");
        System.out.println(sync.get("foo"));

        RedisAsyncCommands<String, String> async = connection.async();
        async.set("hi", "hello");
        RedisFuture<String> future = async.get("hi");
        future.whenComplete((res, err) -> System.out.println(res));

        //Thread.sleep(3);
        //async.get("foo").thenAccept(System.out::println);

        connection.close();
        client.shutdown();
    }
}
