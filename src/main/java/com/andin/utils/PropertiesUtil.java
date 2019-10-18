package com.andin.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static String configFilePath = "config.properties";
	private static Properties configPros;
	
	static {
		configPros = getProps(configFilePath);
		logger.debug("***PropertiesUtils.init method executed is successful...");
	}
	
	public static Properties getProps(String configpath) {
		Properties props = new Properties();		
		InputStream stream = null;
		try {
			stream = PropertiesUtil.class.getClassLoader().getResourceAsStream(configpath);
			props.load(stream);
			logger.debug("***PropertiesUtils load properties is successful, file name is: " + configpath);
		} catch (Exception e) {
			logger.error("***PropertiesUtils.init method is execute fail: ", e);
		}
		return props;
	}
	
	/**
	 * 根据key从指定文件中配置文件中读取配置,默认获取config文件中的文件
	 * @param key
	 * @param file
	 * @return
	 */
	public static String getProperties(String key, String file) {
		String result = null;
		if(StringUtil.isEmpty(file)) {
			result = configPros.getProperty(key);			
		}else {
			if(ConstantUtil.CONFIG_PROPERTIES.equals(file)) {
				result = configPros.getProperty(key);	
			}
		}
		if(!StringUtil.isEmpty(result)) {
			return result.trim();			
		}else {
			return result;			
		}
	}

	public static void main(String[] args) {
		
		  String key = PropertiesUtil.getProperties("test", null);
		  System.out.println(key);
		 
	}

}
