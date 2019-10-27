package com.andin.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * 文件操作工具类
 * @author Administrator
 *
 */
public class FileUtil {
	
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	  * 通过文件夹路径和匹配到的文件名删除文件
	 * @param fileDirPath
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFileByName(String fileDirPath, String fileName) {
		logger.debug("FileUtil.deleteFileByName method params is: [fileDirPath=" + fileDirPath + "], [fileName=" + fileName + "]");
		boolean result = false;
		try {
			File dir = new File(fileDirPath);
			File[] files = dir.listFiles();
			if(files != null) {
				for (File file : files) {
					String name = file.getName();
					if(name.startsWith(fileName)) {
						FileUtils.forceDelete(file);
						logger.debug("file delete is successfuled, file name is: " + name);
					}
				}
			}
			logger.debug("FileUtil.deleteFileByName method executed is successful...");
		} catch (Exception e) {
			logger.error("FileUtil.deleteFileByName method executed is error: ", e);
		}
		return result;
	}

}
