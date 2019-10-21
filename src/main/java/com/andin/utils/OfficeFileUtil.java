package com.andin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficeFileUtil {

	private static Logger logger = LoggerFactory.getLogger(OfficeFileUtil.class);
	
	private final static String DOCX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.DOCX_PATH;
	
	private final static String XLSX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.XLSX_PATH;
	
	private final static String PPTX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.PPTX_PATH;
	
	private final static String HTML_DOCX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.HTML_DOCX_PATH;
	
	private final static String HTML_XLSX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.HTML_XLSX_PATH;
	
	private final static String HTML_PPTX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.HTML_PPTX_PATH;
	
	private final static String PDF_DOCX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.PDF_DOCX_PATH;
	
	private final static String PDF_XLSX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.PDF_XLSX_PATH;
	
	private final static String PDF_PPTX_PATH = StringUtil.getUploadFilePath() + ConstantUtil.PDF_PPTX_PATH;

	/**
	 * XLSX转HTML
	 * @param inputFileName /app/file/1.xlsx
	 * @return
	 * @throws Exception
	 */
	public static boolean excelToHtml(String inputFileName) throws Exception {
		boolean result = false;
		String fileName = inputFileName.substring(0, inputFileName.lastIndexOf("."));
		result = OfficeCmdUtil.excelToHtml(XLSX_PATH + inputFileName, HTML_XLSX_PATH + fileName + ConstantUtil.HTML);
		logger.debug("输入文件为" + inputFileName + "xlsx转html的结果为：" + result);
		return result;
	}
	
	/**
	 * HTML转换PDF
	 * @param fileInfo /app/file/filename (不包含后缀.xlsx)
	 * @param inputPath 
	 * @param outputPath
	 * @return
	 * @throws Exception
	 */
	public static boolean htmlToPdf(String fileInfo, String inputPath, String outputPath) throws Exception {
		String htmlFileNamePath = inputPath + fileInfo + ConstantUtil.HTML;	
		String pdfFileNamePath = outputPath + fileInfo + ConstantUtil.PDF;
		return OfficeCmdUtil.htmlToPdf(htmlFileNamePath, pdfFileNamePath);
	}
	
	public static boolean officeToPdf(String inputFileName) {
		boolean result = false;
		try {
			logger.debug("OfficeFileUtil.officeToPdf 转换的文件名为： " + inputFileName);
			int index = inputFileName.lastIndexOf(".");
			String fileName = inputFileName.substring(0, index);
			String fileType = inputFileName.substring(index);
			if(ConstantUtil.DOCX.equals(fileType) || ConstantUtil.DOC.equals(fileType)) {
				//将DOCX文件转换为PDF
				result = OfficeCmdUtil.wordToHtml(DOCX_PATH + inputFileName, HTML_DOCX_PATH);
				logger.debug("输入文件为" + inputFileName + "docx转html的结果为：" + result);
				if(result) {
					result = htmlToPdf(fileName, HTML_DOCX_PATH, PDF_DOCX_PATH);
					logger.debug("输入文件为" + inputFileName + "html转pdf的结果为：" + result);
				}
				
			}else if(ConstantUtil.XLSX.equals(fileType) || ConstantUtil.XLS.equals(fileType)) {
				//将XLSX文件转换为PDF
				result = OfficeCmdUtil.excelToHtml(XLSX_PATH + inputFileName, HTML_XLSX_PATH + fileName + ConstantUtil.HTML);
				logger.debug("输入文件为" + inputFileName + "xlsx转html的结果为：" + result);
				if(result) {
					//获取excel每个sheet转成的html文件名列表
					List<String> list = getXlsxHtmlFileNameList(fileName);
					int size = list.size();
					//将每个html转换成pdf
					for (int i = 0; i < size; i++) {
						String name = list.get(i);
						result = htmlToPdf(name, HTML_XLSX_PATH, PDF_XLSX_PATH);
					}
					logger.debug("输入文件为" + inputFileName + "html转pdf的结果为：" + result);
				}
			
			}else if(ConstantUtil.PPTX.equals(fileType) || ConstantUtil.PPT.equals(fileType)) {
				//将PPTX文件转换为PDF
				result = OfficeCmdUtil.pptToHtml(PPTX_PATH + inputFileName, HTML_PPTX_PATH  + fileName + ConstantUtil.HTML);
				logger.debug("输入文件为" + inputFileName + "pptx转html的结果为：" + result);
				if(result) {
					result = htmlToPdf(fileName, HTML_PPTX_PATH, PDF_PPTX_PATH);
					logger.debug("输入文件为" + inputFileName + "html转pdf的结果为：" + result);
				}
			}else {
				logger.error("OfficeFileUtil.officeToPdf 需转换的文件格式不符合规范：" + inputFileName);
				return false;
			}
			logger.debug("OfficeFileUtil.officeToPdf 转换执行成功！ 文件名为：" + inputFileName);
		} catch (Exception e) {
			result = false;
			logger.error("OfficeFileUtil.officeToPdf method executed is failed : ", e);
		}
		return result;
	}
	
	/**
	 * 获取包含文件名的不带后缀的文件名列表
	 * @param fileName
	 * @return
	 */
	private static List<String> getXlsxHtmlFileNameList(String fileName){
		logger.debug("OfficeFileUtil.getXlsxHtmlFileNameList 需匹配的文件名为：" + fileName);
		List<String> list = new ArrayList<String>();
		//获取html/xlsx/文件夹下的文件名列表
		File dir = new File(HTML_XLSX_PATH);
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if(file.isFile()) {
				String name = file.getName();
				if(name.startsWith(fileName)) {
					list.add(name.substring(0, name.lastIndexOf(".")));
				}	
			}
		}
		logger.debug("OfficeFileUtil.getXlsxHtmlFileNameList 匹配到的文件名列表为：" + list.toString());
		return list;
	}
	
}