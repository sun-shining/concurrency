package com.juddar.concurrency.example.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("test")
    @ResponseBody
    public Object threadLocalTest(){
        return RequestHolder.getId();
    }
}
