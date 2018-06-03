package com.mrlv.taotao.rest.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.mrlv.taotao.common.utils.JsonUtils;
import com.mrlv.taotao.rest.dao.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mrlv.taotao.mapper.ContentMapper;
import com.mrlv.taotao.pojo.Content;
import com.mrlv.taotao.pojo.ContentExample;
import com.mrlv.taotao.pojo.ContentExample.Criteria;
import com.mrlv.taotao.rest.service.IContentService;

@Service("contentService")
public class ContentServiceImpl implements IContentService {
	
	@Resource
	private ContentMapper contentMapper;

	@Resource
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	public List<Content> getContentList(long contentCid){
		//从缓存中取内容
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			//判断是否为空
			if (!StringUtils.isBlank(result)){
				//将字符转为list
				List<Content> contents = JsonUtils.jsonToList(result, Content.class);
				return contents;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		//根据内容分类id查询内容列表
		ContentExample contentExample = new ContentExample();
		Criteria criteria = contentExample.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<Content> list = contentMapper.selectByExample(contentExample);

		//向缓存中添加内容
		try {
			//将list转换为字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
