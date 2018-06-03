package com.mrlv.taotao.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.common.utils.ExceptionUtil;
import com.mrlv.taotao.pojo.Content;
import com.mrlv.taotao.rest.service.IContentService;

@Controller
public class ContentController {
	
	@Resource
	private IContentService contentService;
	
	@RequestMapping(value="/content/list/{contentCategoryId}"
			,produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<Content> contentList = contentService.getContentList(contentCategoryId);
			return TaotaoResult.ok(contentList);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
