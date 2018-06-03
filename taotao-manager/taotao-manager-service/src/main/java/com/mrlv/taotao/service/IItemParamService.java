package com.mrlv.taotao.service;


import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.pojo.ItemParam;

public interface IItemParamService {

	public EasyUIResult getItemParamList(Integer page, Integer rows);
	
	public TaotaoResult getItemParamByCid(Long cid);
	
	public TaotaoResult insertItemParam(ItemParam itemParam);
}
