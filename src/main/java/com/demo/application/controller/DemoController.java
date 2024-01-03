package com.demo.application.controller;

import com.demo.application.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController      // @RestController = @Controller + @ResponseBody
public class DemoController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello");
    }

    /*
        Path Variable
        /users/{id}/todos/{id}  --->  /users/2/todos/10
    */

    // NOTE: @PathVariable String name should match with the path variable {name}
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello " + name);
    }
}
