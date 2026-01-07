package kz.lab.petproject.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("ru")
@Service
public class HelloServiceRuImpl implements HelloService {
    @Override
    public String hello() {
        return "Привет из русской версии сервиса!";
    }
}
