package com.mrlv.taotao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.pojo.EasyUITreeNode;
import com.mrlv.taotao.service.IItemCatService;

/**
 * 商品分类
 * @author Administrator
 *
 */
@Controller
public class ItemCatController {

	@Resource
	private IItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	//defaultValue为默认值，value为name
	public List<EasyUITreeNode> categoryList(@RequestParam(value="id",defaultValue="0") Long parentId) {
		List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(parentId);
		return itemCatList;
	}
}
