package com.mrlv.taotao.rest.service.impl;

import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.common.utils.ExceptionUtil;
import com.mrlv.taotao.rest.dao.JedisClient;
import com.mrlv.taotao.rest.service.IRedisService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class RedisServiceImpl implements IRedisService{

    @Resource
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public TaotaoResult syncContent(long contentId) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentId+"");
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
