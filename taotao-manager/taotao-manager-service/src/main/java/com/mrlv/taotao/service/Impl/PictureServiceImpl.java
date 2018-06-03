package com.mrlv.taotao.service.Impl;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mrlv.taotao.common.pojo.PictureResult;
import com.mrlv.taotao.common.utils.FtpUtil;
import com.mrlv.taotao.common.utils.IDUtils;
import com.mrlv.taotao.service.IPictureService;
/**
 * 图片上传
 * @author Administrator
 *
 */
@Service("pictureService")
public class PictureServiceImpl implements IPictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile) {
		try {
			//生成一个新的文件名，也可以用UUID
			String newName = IDUtils.genImageName();
			//取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			newName =  newName + oldName.substring(oldName.lastIndexOf("."));
			//获取日期路径名
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			System.out.println(imagePath + "/" + newName);
			//图片上传
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
			if(!result) {
				PictureResult pictureResult = new PictureResult(1, null, "文件上传失败");
				return pictureResult;
			} else {
				PictureResult pictureResult = new PictureResult(0, IMAGE_BASE_URL + imagePath + "/" + newName, null);
				return pictureResult;
			}
			
		} catch (IOException e) {
			PictureResult pictureResult = new PictureResult(1, null, "文件上传发生异常");
			return pictureResult;
		}
	}

}
