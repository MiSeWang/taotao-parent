package com.mrlv.taotao.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlv.taotao.common.pojo.EasyUITreeNode;
import com.mrlv.taotao.mapper.ItemCatMapper;
import com.mrlv.taotao.pojo.ItemCat;
import com.mrlv.taotao.pojo.ItemCatExample;
import com.mrlv.taotao.pojo.ItemCatExample.Criteria;
import com.mrlv.taotao.service.IItemCatService;

/**
 * 商品分类
 * @author Administrator
 *
 */
@Service("itemCatService")
public class ItemCatServiceImpl implements IItemCatService {

	@Resource
	private ItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		
		ItemCatExample ice = new ItemCatExample();
		
		Criteria iceCriteria = ice.createCriteria();
		
		//设置查询条件，根据parentid查询子节点
		iceCriteria.andParentIdEqualTo(parentId);
		
		//返回子节点列表
		List<ItemCat> itemCats = itemCatMapper.selectByExample(ice);
		
		List<EasyUITreeNode> list = new ArrayList<EasyUITreeNode>();
		
		for (ItemCat itemCat : itemCats) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			//如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
			node.setState(itemCat.getIsParent()?"closed":"open");
			list.add(node);
		}
		return list;
	}

}
