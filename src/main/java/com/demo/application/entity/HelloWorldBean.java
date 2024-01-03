package com.demo.application.entity;

public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}

/*
    In Spring, the @Component annotation is used to mark a Java class as a Spring-managed bean
    When a class is annotated with @Component, it is registered with the Spring container and can be injected into other classes or components
    However, it is not necessary to annotate every class with @Component if the class is not being used as a bean or if it does not have any dependencies to be injected
    In the case of the HelloWorldBean class, it is a simple plain old Java object (POJO) that does not have any dependencies or other Spring-related functionality, so there is no need to annotate it with @Component
    In general, you only need to annotate a class with @Component if you want to use it as a Spring bean or if it has dependencies that need to be injected by the Spring container
    If a class does not require any of these functionalities, there is no need to annotate it with @Component
*/