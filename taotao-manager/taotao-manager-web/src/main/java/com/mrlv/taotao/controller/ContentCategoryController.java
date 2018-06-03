package com.mrlv.taotao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.pojo.EasyUITreeNode;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.service.IContentCategroyService;

@Controller
public class ContentCategoryController {

	@Resource
	private IContentCategroyService contentCategroyService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
		return contentCategroyService.getCategoryList(parentId);
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		return contentCategroyService.insertContentCategory(parentId, name);
	}
	
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public TaotaoResult delectContentCategory(Long parentId, Long id) {
		return contentCategroyService.delectContentCategory(id);
	}
}
