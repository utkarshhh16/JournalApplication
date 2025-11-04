package com.example.myFirstProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClass {

    @GetMapping("udit")
    public String hello(){
        return "What the fuck!!!!";
    }

}
