package com.sharding.jdbc.sharding.data.controller;

import com.sharding.jdbc.sharding.data.service.AspectLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class AspectLogController {
    @Autowired
    private AspectLogService aspectLogService;

    @RequestMapping("/echo/{str}")
    public String echo(@PathVariable("str") String str) {
        aspectLogService.echo(str);
        return "success";
    }

    @RequestMapping("/action/{str}")
    public String action(@PathVariable("str") String str) {
        return aspectLogService.action(str);
    }
}
