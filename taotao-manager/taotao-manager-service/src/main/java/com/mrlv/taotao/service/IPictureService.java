package com.mrlv.taotao.service;


import org.springframework.web.multipart.MultipartFile;

import com.mrlv.taotao.common.pojo.PictureResult;


public interface IPictureService {
	
	//也可以使用Map
	public PictureResult uploadPicture(MultipartFile uploadFile);
}
