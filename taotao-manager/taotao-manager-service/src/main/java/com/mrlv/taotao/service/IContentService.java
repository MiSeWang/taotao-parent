package com.mrlv.taotao.service;

import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.pojo.Content;

public interface IContentService {

	public EasyUIResult getContent(Integer page, Integer rows, long categoryId);
	
	public TaotaoResult insertContent(Content content);
}
