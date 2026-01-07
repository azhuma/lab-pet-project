package kz.lab.petproject.controllers;

import kz.lab.petproject.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SimpleController {

    public SimpleController(HelloService service) {
        this.service = service;
    }

    private HelloService service;

    @RequestMapping("/hello")
    public String hello() {
        String result = service.hello();
        System.out.println(result);
        return result;
    }
}
