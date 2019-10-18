package com.andin.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andin.service.OfficeService;
import com.andin.utils.ConstantUtil;

@Controller
@RequestMapping("/office")
public class OfficeController {
	
    private static Logger logger = LoggerFactory.getLogger(OfficeController.class);

	@Resource
	private OfficeService officeService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String path = req.getServletContext().getRealPath("/") + "upload/";
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
	
	
	@RequestMapping("/download")
	@ResponseBody
	public Map<String, Object> download(HttpServletRequest req){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String name = URLDecoder.decode(req.getParameter("name"), "UTF-8");
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
