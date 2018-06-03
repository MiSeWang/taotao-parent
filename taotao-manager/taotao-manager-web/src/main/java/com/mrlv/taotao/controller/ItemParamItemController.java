package com.mrlv.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrlv.taotao.service.IItemParamItemService;

@Controller
public class ItemParamItemController {

	
	@Resource
	private IItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/1111/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}


}
