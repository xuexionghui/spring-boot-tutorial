package com.junlaninfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 辉 on 2020/3/7.
 */
@RestController
public class testController {
    @RequestMapping(value = "/")
    public   String   getString(){
        return   "xuexionghui";
    }
}
