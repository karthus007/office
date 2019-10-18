package com.andin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andin.utils.ConstantUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/app")
	@ResponseBody
	public Map<String, Object> getAppInfo(@RequestParam("name") String name){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
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
	
	@RequestMapping("/file")
	@ResponseBody
	public Map<String, Object> getAppInfo(@RequestParam("file") Part part){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
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
