package com.andin.utils;

public class StringUtil {
	
    private static final String FILE_PATH = PropertiesUtil.getProperties("file.path", null);
    
    private static final String FILE_STATUS = PropertiesUtil.getProperties("file.status", null);
	
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
		if(ConstantUtil.TRUE.equals(FILE_STATUS)) {
			return FILE_PATH + UPLOAD_PATH;
		}else {
			return System.getProperty("user.dir").replace("\\", "/") + UPLOAD_PATH;
		}
	}
	
	/**
	 * 通过文件名获取文件的存储路径
	 * @param fileName
	 * @return
	 */
	public static String getFilePathByFileName(String fileName) {
		StringBuffer path = new StringBuffer();
		path.append(getUploadFilePath());
		if(fileName.endsWith(ConstantUtil.DOC) || fileName.endsWith(ConstantUtil.DOCX)) {
			path.append(ConstantUtil.DOCX_PATH);
		}else if(fileName.endsWith(ConstantUtil.XLS) || fileName.endsWith(ConstantUtil.XLSX)) {
			path.append(ConstantUtil.XLSX_PATH);
		}else if(fileName.endsWith(ConstantUtil.PPT) || fileName.endsWith(ConstantUtil.PPTX)) {
			path.append(ConstantUtil.PPTX_PATH);
		}else if(fileName.endsWith(ConstantUtil.PDF)) {
			path.append(ConstantUtil.PDF_PDF_PATH);
		}
		path.append(fileName);
		return path.toString();
	}
	
	/**
	 * 通过文件名获取转换好后PDF的文件路径
	 * @param fileName
	 * @return
	 */
	public static String getPdfFilePathByFileName(String fileName, String name) {
		StringBuffer path = new StringBuffer();
		path.append(getUploadFilePath());
		if(fileName.endsWith(ConstantUtil.DOC) || fileName.endsWith(ConstantUtil.DOCX)) {
			path.append(ConstantUtil.PDF_DOCX_PATH);
		}else if(fileName.endsWith(ConstantUtil.XLS) || fileName.endsWith(ConstantUtil.XLSX)) {
			path.append(ConstantUtil.PDF_XLSX_PATH);
		}else if(fileName.endsWith(ConstantUtil.PPT) || fileName.endsWith(ConstantUtil.PPTX)) {
			path.append(ConstantUtil.PDF_PPTX_PATH);
		}else if(fileName.endsWith(ConstantUtil.PDF)) {
			path.append(ConstantUtil.PDF_PDF_PATH);
		}
		path.append(name);
		path.append(ConstantUtil.PDF);
		return path.toString();
	}
	
	/**
	 * 通过类型获取文件名的后缀
	 * @param type
	 * @return
	 */
	public static String getFileTypeByType(Integer type) {
		String fileType = "";
		switch (type) {
		case 82:
			fileType = ConstantUtil.PPT;
			break;
		case 83:
			fileType = ConstantUtil.DOC;
			break;
		case 84:
			fileType = ConstantUtil.PDF;
			break;
		case 85:
			fileType = ConstantUtil.XLS;
			break;
		default:
			fileType = ConstantUtil.PDF;
			break;
		}
		return fileType;
	}

}
