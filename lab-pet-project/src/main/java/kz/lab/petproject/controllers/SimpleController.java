package kz.lab.petproject.controllers;

import kz.lab.petproject.exceptions.NotFoundException;
import kz.lab.petproject.services.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SimpleController {

    public SimpleController(HelloService service) {
        this.service = service;
    }

    private HelloService service;

    @GetMapping("/hello")
    public String hello() {
        return "hi";
    }

    @GetMapping("/call/{id}")
    public String callService(@PathVariable("id") int id) {
        return service.callExternalService(id);
    }
}
