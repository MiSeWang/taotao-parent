package com.mrlv.taotao.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.common.utils.IDUtils;
import com.mrlv.taotao.mapper.ItemDescMapper;
import com.mrlv.taotao.mapper.ItemMapper;
import com.mrlv.taotao.pojo.Item;
import com.mrlv.taotao.pojo.ItemDesc;
import com.mrlv.taotao.pojo.ItemExample;
import com.mrlv.taotao.service.IItemService;

/**
 * 商品
 * @author Administrator
 *
 */
@Service("itemService")
public class ItemServiceImpl implements IItemService {

	@Resource
	private ItemMapper itemMapper;
	
	@Resource
	private ItemDescMapper itemDescMapper;
	
	@Override
	public Item getItemById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public EasyUIResult getItemList(Integer page, Integer rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		
		List<Item> items = itemMapper.selectByExample(new ItemExample());
		
		EasyUIResult eugr = new EasyUIResult();
		
		eugr.setRows(items);
		
		//取总记录数
		PageInfo<Item> info = new PageInfo<Item>(items);
		
		eugr.setTotal(info.getTotal());
		
		return eugr;
	}

	@Override
	public TaotaoResult createItem(Item item, String desc) throws Exception {
		//生成商品id
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态：1-正常，2-下架，3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());

		itemMapper.insert(item);
		TaotaoResult descResult = insertItemDesc(itemId, desc);
		if(descResult.getStatus() != 200) {
			throw new Exception();
		}
		return TaotaoResult.ok();
	}

	public ItemDesc getItemDesc(Long itemId){
		ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return itemDesc;
	}
	
	/**
	 * 添加商品描述
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
}
