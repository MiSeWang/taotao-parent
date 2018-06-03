package com.mrlv.taotao.controller;

import javax.annotation.Resource;

import com.mrlv.taotao.pojo.ItemDesc;
import com.mrlv.taotao.service.IItemParamItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.pojo.Item;
import com.mrlv.taotao.service.IItemService;

/**
 * 商品
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	
	@Resource
	private IItemService itemService;

	@Resource
	private IItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public Item getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIResult getItemList(Integer page, Integer rows) {
		EasyUIResult itemList = itemService.getItemList(page, rows);
		return itemList;
	}
	//method：请求方式，可不添加
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(Item item, String desc) throws Exception {
		TaotaoResult createItem = itemService.createItem(item, desc);
		return createItem;
	}

	//method：请求方式，可不添加
	@RequestMapping(value="/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItem(String[] ids) throws Exception {
		for (String t:
				ids) {		System.out.println(t);

		}

		return null;
	}

	@RequestMapping(value="/rest/page/item-edit")
	@ResponseBody
	public Item getItem(Long id){
		return itemService.getItemById(id);
	}

	@RequestMapping(value="/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public ItemDesc getItemDesc(@PathVariable Long id){
		ItemDesc itemDesc = itemService.getItemDesc(id);
		return itemDesc;
	}

	@RequestMapping(value="/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public String getItemParam(@PathVariable Long id){
		String itemParamByItemId = itemParamItemService.getItemParamByItemId(id);
		return itemParamByItemId;
	}

}
