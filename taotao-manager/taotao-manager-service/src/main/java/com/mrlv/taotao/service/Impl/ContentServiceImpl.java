package com.mrlv.taotao.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mrlv.taotao.common.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.mapper.ContentMapper;
import com.mrlv.taotao.pojo.Content;
import com.mrlv.taotao.pojo.ContentExample;
import com.mrlv.taotao.pojo.ContentExample.Criteria;
import com.mrlv.taotao.service.IContentService;

@Service("contentService")
public class ContentServiceImpl implements IContentService {

	@Resource
	private ContentMapper contentMapper;

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("$REST_CONTENT_SYNC_URL")
	private String REST_CONTENT_SYNC_URL;
	
	@Override
	public EasyUIResult getContent(Integer page, Integer rows, long categoryId) {
		PageHelper.startPage(page, rows);
		ContentExample contentExample = new ContentExample();
		Criteria criteria = contentExample.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		List<Content> contents = contentMapper.selectByExample(contentExample);
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(contents);
		
		PageInfo<Content> pageInfo = new PageInfo<>(contents);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	
	public TaotaoResult insertContent(Content content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);

		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}
}
