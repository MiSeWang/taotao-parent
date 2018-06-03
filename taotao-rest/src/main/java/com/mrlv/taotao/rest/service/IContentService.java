package com.mrlv.taotao.rest.service;

import java.util.List;

import com.mrlv.taotao.pojo.Content;

public interface IContentService {
	public List<Content> getContentList(long contentCid);
}
