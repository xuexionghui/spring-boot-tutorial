package com.junlaninfo.service;


import com.junlaninfo.common.ResponseCode;
import com.junlaninfo.common.ServerResponse;
import com.junlaninfo.util.RandomUtil;
import com.junlaninfo.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 辉 on 2020/3/8.
 */
@Service
public class TokenService {

    private static final String TOKEN_NAME = "token";

    @Autowired
    RedisUtil  redisUtil;



    public ServerResponse createToken() {
        String token = RandomUtil.UUID32();
        /*StrBuilder token = new StrBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);*/

        Jedis jedis = redisUtil.getJedis();
        jedis.set(TOKEN_NAME,token.toString());
        //jedisUtil.set(TOKEN_NAME,token.toString());

        return ServerResponse.success(token.toString());
    }


    public void checkToken(HttpServletRequest request) {
        Jedis jedis = redisUtil.getJedis();
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        if (!jedis.exists(token)) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Long del = jedis.del(token);
        if (del <= 0) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }

}
