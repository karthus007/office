package com.andin.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andin.service.OfficeService;
import com.andin.utils.ConstantUtil;
import com.andin.utils.StringUtil;

@Controller
@RequestMapping("/office")
public class OfficeController {
	
    private static Logger logger = LoggerFactory.getLogger(OfficeController.class);

	@Resource
	private OfficeService officeService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req, @RequestParam("file") Part part){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String path = StringUtil.getUploadFilePath();
			InputStream in = part.getInputStream();
			OutputStream os = new FileOutputStream(path + part.getSubmittedFileName());
			byte[] b = new byte[1024*4];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				os.write(b, 0, len);
			}
			in.close();
			os.close();
			map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DEFAULT_SUCCESS_CODE);
			map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DEFAULT_SUCCESS_MSG);
			logger.debug("TestController.getAppInfo method execute is successful...");
		} catch (Exception e) {
			map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DEFAULT_ERROR_CODE);
			map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DEFAULT_ERROR_MSG);
			logger.error("TestController.getAppInfo method execute is error: ", e);
		}
		return map;
	}
	
	
	@RequestMapping("/download")
	@ResponseBody
	public Map<String, Object> download(HttpServletRequest req){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String name = URLDecoder.decode(req.getParameter("name"), "UTF-8");
			System.out.println(name);
			map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DEFAULT_SUCCESS_CODE);
			map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DEFAULT_SUCCESS_MSG);
			logger.debug("TestController.getAppInfo method execute is successful...");
		} catch (Exception e) {
			map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DEFAULT_ERROR_CODE);
			map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DEFAULT_ERROR_MSG);
			logger.error("TestController.getAppInfo method execute is error: ", e.getMessage());
		}
		return map;
	}
	
	
}
