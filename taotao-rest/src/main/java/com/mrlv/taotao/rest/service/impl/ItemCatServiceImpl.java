package com.mrlv.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mrlv.taotao.common.utils.JsonUtils;
import com.mrlv.taotao.rest.dao.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mrlv.taotao.mapper.ItemCatMapper;
import com.mrlv.taotao.pojo.ItemCat;
import com.mrlv.taotao.pojo.ItemCatExample;
import com.mrlv.taotao.pojo.ItemCatExample.Criteria;
import com.mrlv.taotao.rest.pojo.CatNode;
import com.mrlv.taotao.rest.pojo.CatResult;
import com.mrlv.taotao.rest.service.IItemCatService;

@Service("itemCatService")
public class ItemCatServiceImpl implements IItemCatService{

	@Resource
	private ItemCatMapper itemCatMapper;

	@Resource
	private JedisClient jedisClient;

	@Value("${ITEM_CAT__REDIS_KEY}")
	private String ITEM_CAT__REDIS_KEY;
	
	@Override
	public CatResult getItemCat() {
		//从缓存中获取数据
		try {
			String result = jedisClient.get(ITEM_CAT__REDIS_KEY);
			if (!StringUtils.isBlank(result)) {
                CatResult catResult = JsonUtils.jsonToPojo(result, CatResult.class);
                return catResult;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		CatResult catResult = new CatResult();
		//调用查询方法
		catResult.setData(getCatList(0));
		//将数据存到缓存中

		try {
			String cacheString = JsonUtils.objectToJson(catResult);
			jedisClient.set(ITEM_CAT__REDIS_KEY, cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catResult;
	}
	 
	/**
	 * 查询分类列表
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId){
		//创建查询条件
		ItemCatExample itemCatExample = new ItemCatExample();
		Criteria createCriteria = itemCatExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);	
		//执行查询
		List<ItemCat> list = itemCatMapper.selectByExample(itemCatExample);
		//返回list
		List resultList = new ArrayList<>();
		//添加计数器
		int count = 0;
		//向list中添加节点
		for(ItemCat itemCat: list) {
			//判断是否为父节点
			if(itemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if(0 == parentId) {
					catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
				} else {
					catNode.setName(itemCat.getName());
				}
				catNode.setUrl("/products/" + itemCat.getId() + ".html");
				catNode.setItem(getCatList(itemCat.getId()));
				resultList.add(catNode);
				
				count++;
				//只取14条首行记录
				if(count >= 14 && 0 == parentId) {
					break;
				}
			} else {
				//如果是叶子节点
				resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
			}
		}	
		return resultList;
	}

}
