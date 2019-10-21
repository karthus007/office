package com.andin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andin.service.OfficeService;
import com.andin.utils.ConstantUtil;
import com.andin.utils.OfficeFileUtil;
import com.andin.utils.StringUtil;

@Controller
@RequestMapping("/office")
public class OfficeController {
	
    private static Logger logger = LoggerFactory.getLogger(OfficeController.class);

	@Resource
	private OfficeService officeService;
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req, @RequestParam("file") Part part){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			StringBuffer path = new StringBuffer();
			path.append(StringUtil.getUploadFilePath());
			String fileName = part.getSubmittedFileName();
			if(fileName.endsWith(ConstantUtil.DOC) || fileName.endsWith(ConstantUtil.DOCX)) {
				path.append(ConstantUtil.DOCX_PATH);
			}else if(fileName.endsWith(ConstantUtil.XLS) || fileName.endsWith(ConstantUtil.XLSX)) {
				path.append(ConstantUtil.XLSX_PATH);
			}else if(fileName.endsWith(ConstantUtil.PPT) || fileName.endsWith(ConstantUtil.PPTX)) {
				path.append(ConstantUtil.PPTX_PATH);
			}else {
				map.put(ConstantUtil.RESULT_CODE, ConstantUtil.UPLOAD_FILE_TYPE_ERROR_CODE);
				map.put(ConstantUtil.RESULT_MSG, ConstantUtil.UPLOAD_FILE_TYPE_ERROR_MSG);
				return map;
			}
			path.append(fileName);
			InputStream in = part.getInputStream();
			OutputStream os = new FileOutputStream(path.toString());
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
	
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> download(HttpServletRequest req, HttpServletResponse resp){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String fileName = URLDecoder.decode(req.getParameter("name"), "UTF-8");
			String type = "." + URLDecoder.decode(req.getParameter("type"), "UTF-8");
			StringBuffer path = new StringBuffer();
			path.append(StringUtil.getUploadFilePath());
			if(type.equals(ConstantUtil.DOC) || type.equals(ConstantUtil.DOCX)) {
				path.append(ConstantUtil.PDF_DOCX_PATH);
			}else if(type.equals(ConstantUtil.XLS) || type.equals(ConstantUtil.XLSX)) {
				path.append(ConstantUtil.PDF_XLSX_PATH);
			}else if(type.equals(ConstantUtil.PPT) || type.equals(ConstantUtil.PPTX)) {
				path.append(ConstantUtil.PDF_PPTX_PATH);
			}else {
				map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DOWNLOAD_FILE_TYPE_ERROR_CODE);
				map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DOWNLOAD_FILE_TYPE_ERROR_MSG);
				return map;
			}
			path.append(ConstantUtil.WATER_PATH);
			path.append(fileName);
	        File file = new File(path.toString());
	        
	        //设置响应头
	        resp.setContentLength((int) file.length());
	        resp.setCharacterEncoding(ConstantUtil.UTF_8);
	        resp.setContentType(ConstantUtil.APPLICATION_OCTET_STREAM);
	        //resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	        resp.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes(ConstantUtil.UTF_8), "ISO-8859-1"));  
	        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	        OutputStream os = resp.getOutputStream();
	        byte[] buff = new byte[1024*4];
	        int len = 0;
	        while ((len = bis.read(buff)) != -1) {
	        	os.write(buff, 0, len);
	        	os.flush();
	        }
	        bis.close();
	        os.close();
	        
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
	
	@RequestMapping(value="/officeToPdf", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> officeToPdf(@RequestParam("name") String name){
		logger.debug("TestController.officeToPdf method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Boolean result = OfficeFileUtil.officeToPdf(name);
			if(result) {
				map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DEFAULT_SUCCESS_CODE);
				map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DEFAULT_SUCCESS_MSG);
			}else {
				map.put(ConstantUtil.RESULT_CODE, ConstantUtil.OFFICE_FILE_CONVERSION_ERROR_CODE);
				map.put(ConstantUtil.RESULT_MSG, ConstantUtil.OFFICE_FILE_CONVERSION_ERROR_MSG);
			}
			logger.debug("TestController.officeToPdf method execute is successful...");
		} catch (Exception e) {
			map.put(ConstantUtil.RESULT_CODE, ConstantUtil.DEFAULT_ERROR_CODE);
			map.put(ConstantUtil.RESULT_MSG, ConstantUtil.DEFAULT_ERROR_MSG);
			logger.error("TestController.officeToPdf method execute is error: ", e.getMessage());
		}
		return map;
	}
	
	
}
