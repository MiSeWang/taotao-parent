package com.mrlv.taotao.rest.controller;


import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.rest.service.IRedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    @Resource
    private IRedisService redisService;


    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TaotaoResult contentCacheSync(@PathVariable Long contentCId){
        TaotaoResult result =  redisService.syncContent(contentCId);
        return result;
    }
}
