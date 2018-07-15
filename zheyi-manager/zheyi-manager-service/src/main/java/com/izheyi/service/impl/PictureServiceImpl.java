package com.izheyi.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.izheyi.common.utils.FtpUtil;
import com.izheyi.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	@Value("${FTP_IP}")
	private String FTP_IP;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_PATH}")
	private String IMAGE_BASE_PATH;
	

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		
		try {
			// 生成新文件名
			String oldname = uploadFile.getOriginalFilename();
			String newname = UUID.randomUUID().toString();
			newname = newname + oldname.substring(oldname.lastIndexOf("."));
			
			//上传图片
			String filepath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_IP, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, 
					filepath, newname, uploadFile.getInputStream());
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "File upload failed");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_PATH + filepath + "/" + newname);
			return resultMap;
			
		} catch (Exception e) {
			resultMap.put("error", 1);
			resultMap.put("message", "File upload failed");
			return resultMap;
		}
		
	}

}
