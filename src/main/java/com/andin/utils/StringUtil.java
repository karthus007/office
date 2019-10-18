package com.andin.utils;

public class StringUtil {
	
	/**
	  * 判断字符串是否为空
	 * @param name
	 * @return
	 */
	public static boolean isEmpty(String name) {
		boolean result = false;
		if(name == null || name == "" || name == "null" || name.trim().length() == 0) {
			result = true;
		}
		return result;
	}

}
