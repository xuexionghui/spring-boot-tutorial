package com.junlaninfo.service;

import com.junlaninfo.common.ServerResponse;
import org.springframework.stereotype.Service;

/**
 * Created by 辉 on 2020/3/8.
 */
@Service
public class TestService {
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }
}
