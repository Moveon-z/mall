package com.moveon.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/19 23:31
 * @Version 1.0
 **/

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "hello world!";
    }
}
