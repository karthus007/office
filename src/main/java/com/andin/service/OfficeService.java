package com.andin.service;

public interface OfficeService {

	public boolean wordToPdf(String inputFileName, String outputDirPath);
	
	public boolean excelToHtml(String inputFileName, String outputFileName);
	
	public boolean pptToPdf(String inputFileName, String outputFileName);
	
}
