package kz.lab.petproject.controllers;

import kz.lab.petproject.exceptions.NotFoundException;
import kz.lab.petproject.services.HelloService;
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
        throw new NotFoundException();
        //return result;
    }
}
