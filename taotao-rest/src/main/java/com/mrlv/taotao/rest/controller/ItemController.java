package com.mrlv.taotao.rest.controller;


import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlv.taotao.common.utils.JsonUtils;
import com.mrlv.taotao.rest.pojo.CatResult;
import com.mrlv.taotao.rest.service.IItemCatService;

/**
 * 门户首页商品分类
 * @author Administrator
 *
 */
@Controller
public class ItemController {

	@Resource
	private IItemCatService itemCatService;
	//解决乱码办法一
	@RequestMapping(value="/itemcat/all",
			produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCat();
		//把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值,这里用的是jsonp。因为json无法跨域传递。但是js脚本却可以。这里是以脚本方式传递回去
		String result = callback + "(" + json + ");";
		return result;
	}
	/** 
	 * 办法二：
	 * 需要在spring4.1版本以上才能使用MappingJacksonValue
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}**/

	
}
