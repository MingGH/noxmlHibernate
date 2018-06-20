package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/testDo")
    public void testDo(){
        System.out.println("testDo()");
    }

}
