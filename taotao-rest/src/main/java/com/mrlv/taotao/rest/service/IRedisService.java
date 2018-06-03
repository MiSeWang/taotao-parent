package com.mrlv.taotao.rest.service;

import com.mrlv.taotao.common.pojo.TaotaoResult;

public interface IRedisService {
    public TaotaoResult syncContent(long contentId);
}