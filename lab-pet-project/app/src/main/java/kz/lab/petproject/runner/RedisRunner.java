package kz.lab.petproject.runner;

import kz.lab.petproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisRunner implements CommandLineRunner {
    private final RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RedisRunner...");
        var user = redisService.readData("user:100");
        System.out.println("get1: " + user);
        var user2 = redisService.readHashField("user:104", "name");
        System.out.println("get2: " + user2);
    }
}
