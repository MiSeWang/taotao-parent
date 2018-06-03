package com.mrlv.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.PictureResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.pojo.Content;
import com.mrlv.taotao.service.IContentService;

@Controller
public class ContentController {
	
	@Resource
	private IContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIResult list(Integer page, Integer rows, long categoryId) {
		EasyUIResult result = contentService.getContent(page, rows, categoryId);
		return result;
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertContent(Content content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
}
