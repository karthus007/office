package com.andin.utils;

public class StringUtil {
	
	private final static String UPLOAD_PATH = "/upload/";
	
	/**
	  * 判断字符串是否为空
	 * @param name
	 * @return
	 */
	public static boolean isEmpty(String name){
		boolean result = false;
		if(name == null || name == "" || name == "null" || name.trim().length() == 0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 获取项目的根路径
	 * @return
	 */
	public static String getUploadFilePath(){
		 return System.getProperty("user.dir").replace("\\", "/") + UPLOAD_PATH;
	}

}
