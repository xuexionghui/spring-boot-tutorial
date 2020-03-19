package com.junlaninfo.controller;

import com.junlaninfo.annotation.ApiIdempotent;
import com.junlaninfo.common.ServerResponse;
import com.junlaninfo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 辉 on 2020/3/8.
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence(String  token) {
        return testService.testIdempotence();
    }

}