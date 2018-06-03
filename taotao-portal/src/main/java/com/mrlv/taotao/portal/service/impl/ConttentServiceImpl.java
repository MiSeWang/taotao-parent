package com.mrlv.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.common.utils.HttpClientUtil;
import com.mrlv.taotao.common.utils.JsonUtils;
import com.mrlv.taotao.pojo.Content;
import com.mrlv.taotao.portal.service.IContentService;

@Service("contentService")
public class ConttentServiceImpl implements IContentService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		//调用服务层的服务
		System.out.println(REST_BASE_URL + REST_INDEX_AD_URL);
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		System.out.println(result);
		//把字符串转换成TaotaoResult
		try {
			//把得到的字符串转成TaotaoResult
			TaotaoResult taotaoResult = TaotaoResult.formatToList(result, Content.class);
			//列表内容
			List<Content> list = (List<Content>)taotaoResult.getData();
			
			List<Map> resultList = new ArrayList<>();
			//创建一个jsp页面要求的pojo列表
			for (Content content : list) {
				Map map = new HashMap<>();
				map.put("src", content.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", content.getPic2());
				map.put("widthB", 550);
				map.put("heigthB", 240);
				map.put("alt", content.getSubTitle());
				map.put("href", content.getUrl());
				resultList.add(map);
			}
			System.out.println(resultList);
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
