package kz.lab.petproject.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("en")
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Hello World from service!";
    }
}
