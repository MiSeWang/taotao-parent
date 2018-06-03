package com.mrlv.taotao.service;

import java.util.List;

import com.mrlv.taotao.common.pojo.EasyUITreeNode;
import com.mrlv.taotao.common.pojo.TaotaoResult;

public interface IContentCategroyService {

	public List<EasyUITreeNode> getCategoryList(long parentId);
	
	public TaotaoResult insertContentCategory(long parentId, String name);
	
	public TaotaoResult delectContentCategory(long id);
}
