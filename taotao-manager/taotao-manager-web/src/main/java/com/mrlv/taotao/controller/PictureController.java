package com.mrlv.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mrlv.taotao.common.pojo.PictureResult;
import com.mrlv.taotao.common.utils.JsonUtils;
import com.mrlv.taotao.service.IPictureService;

/**
 * 图片
 * @author Administrator
 *
 */
@Controller
public class PictureController {
	
	@Resource
	private IPictureService pictureService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile) {
		PictureResult pictureResult = pictureService.uploadPicture(uploadFile);
		return JsonUtils.objectToJson(pictureResult);
	}
}
