package com.kaspi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "index.html";
    }


}
