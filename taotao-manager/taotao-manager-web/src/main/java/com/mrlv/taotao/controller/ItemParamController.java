package com.mrlv.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.pojo.ItemParam;
import com.mrlv.taotao.service.IItemParamService;

/**
 * 商品规格参数
 * @author Administrator
 *
 */
@Controller
public class ItemParamController {
	
	@Resource
	private IItemParamService itemParamService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIResult getItemParamList(Integer page, Integer rows) {
		EasyUIResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	/**
	 * 根据商品分类id查询规格参数
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	/**
	 * 添加规格参数
	 * <p>Title: insertItemParamItem</p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping("/item/param/save/{itemCatId}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long itemCatId, String paramData) {
		ItemParam itemParam = new ItemParam();
		itemParam.setItemCatId(itemCatId);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
}
