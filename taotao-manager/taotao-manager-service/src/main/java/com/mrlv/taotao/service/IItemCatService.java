package com.mrlv.taotao.service;


import java.util.List;

import com.mrlv.taotao.common.pojo.EasyUITreeNode;

public interface IItemCatService {
	
	public List<EasyUITreeNode> getItemCatList(Long parentId);
}
