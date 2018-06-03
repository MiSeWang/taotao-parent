package com.mrlv.taotao.service;

import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.pojo.Item;
import com.mrlv.taotao.pojo.ItemDesc;

public interface IItemService {
	public Item getItemById(Long id);
	
	public EasyUIResult getItemList(Integer page, Integer rows);
	
	public TaotaoResult createItem(Item item, String desc) throws Exception;

	public ItemDesc getItemDesc(Long itemId);
}
