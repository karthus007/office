package com.andin.utils;

/**
 * 常量类
 * @author Administrator
 *
 */
public class ConstantUtil {
	
	/********************* 请求响应常量 *********************/
	
	public static final String RESULT_CODE = "resultCode";
	
	public static final String RESULT_MSG = "resultMsg";
	
	public static final String CONTENT_TYPE = "content-type";
	
	public static final String UTF_8 = "utf-8";
	
	public static final String APPLICATION_JSON_UTF_8 = "application/json;charset=utf-8";
	
	public static final String APPLICATION_JSON = "application/json";
	
	public static final String TEXT_PLAIN = "text/plain";
	
	public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
	
	public static final String MULTIPART_FORM_DATA = "multipart/form-data";
	
	public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
	
	/********************* 请求响应头常量 *********************/
	
	public static final String HTTP_AC = "AC";
	
	public static final String UPLOAD = "upload";
	
	public static final String HTTP_ID = "ID";
	
	public static final String HTTP_MOD = "MOD";
	
	public static final String HTTP_MOD_VALUE = "ftranshandle";

	/********************* 响应状态码 *********************/
	
	public static final String DEFAULT_SUCCESS_CODE = "0000";
	
	public static final String DEFAULT_SUCCESS_MSG = "请求成功";
	
	public static final String DEFAULT_ERROR_CODE = "0001";
	
	public static final String DEFAULT_ERROR_MSG = "请求失败";
	
	public static final String UPLOAD_FILE_TYPE_ERROR_CODE = "0002";
	
	public static final String UPLOAD_FILE_TYPE_ERROR_MSG = "上传的文件类型不支持";
	
	public static final String DOWNLOAD_FILE_TYPE_ERROR_CODE = "0003";
	
	public static final String DOWNLOAD_FILE_TYPE_ERROR_MSG = "下载的文件类型不支持";
	
	public static final String OFFICE_FILE_CONVERSION_ERROR_CODE = "0004";
	
	public static final String OFFICE_FILE_CONVERSION_ERROR_MSG = "office文件转换失败";
	
	public static final String PARAM_NOT_EMPTY_ERROR_CODE = "0005";
	
	public static final String PARAM_NOT_EMPTY_ERROR_MSG = "必填参数为空";
	
	public static final String PDF_TO_WATER_ERROR_CODE = "0006";
	
	public static final String PDF_TO_WATER_ERROR_MSG = "PDF文件添加水印失败";
	
	
	/********************* 系统常量 *********************/
	
	public static final String CONFIG_PROPERTIES = "config";
	
	public static final String TRUE = "true";
	
	public static final String FALSE = "false";
	
	
	/********************* 上传文件路径常量 *********************/
	
	public static final String HTML = ".html";
	
	public static final String PDF = ".pdf";
	
	public static final String DOCX = ".docx";
	
	public static final String DOC = ".doc";
	
	public static final String XLSX = ".xlsx";
	
	public static final String XLS = ".xls";
	
	public static final String PPTX = ".pptx";
	
	public static final String PPT = ".ppt";
	
	public static final String DOCX_PATH = "docx/";
	
	public static final String XLSX_PATH = "xlsx/";
	
	public static final String PPTX_PATH = "pptx/";
	
	public static final String HTML_DOCX_PATH = "html/docx/";
	
	public static final String HTML_XLSX_PATH = "html/xlsx/";
	
	public static final String HTML_PPTX_PATH = "html/pptx/";
	
	public static final String PDF_DOCX_PATH = "pdf/docx/";
	
	public static final String PDF_XLSX_PATH = "pdf/xlsx/";
	
	public static final String PDF_PPTX_PATH = "pdf/pptx/";
	
	public static final String PDF_PDF_PATH = "pdf/pdf/";
	
	public static final String DOTNET_CMD_PATH = "cmd/officetohtml";
	
	public static final String MONO_CMD_PATH = "cmd/wordtohtml";
	
	public static final String WATER_PATH = "water/";
	
	public static final String WATER_FONT_PATH = "water/simhei.ttf";
	
	public static final String WATER_IMAGE_PATH = "water/test.jpg";
	
	
	
}
