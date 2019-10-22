package com.andin.utils;

import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class WaterToPdfUtil {

    private static Logger logger = LoggerFactory.getLogger(WaterToPdfUtil.class);
    
    private static final String WATER_AGENT = PropertiesUtil.getProperties("water.agent", null);
    
    private static final String WATER_HANDLER = PropertiesUtil.getProperties("water.handler", null);
    
    private static final String WATER_ID = PropertiesUtil.getProperties("water.id", null);
    
    private static final String WATER_COM = PropertiesUtil.getProperties("water.com", null);
    
    private static final String WATER_PASS = PropertiesUtil.getProperties("water.pass", null);
    
    private static final String SIZE_TOP_TEXT = "合同编号:" + WATER_ID;
    
    private static final String SIZE_BOTTOM_TEXT = "承办人:" + WATER_AGENT + "  " + "部门负责人:" + WATER_HANDLER;
	
	public static final String WATER_FONT_PATH = StringUtil.getUploadFilePath() + ConstantUtil.WATER_FONT_PATH;
	
	public static final String WATER_IMAGE_PATH = StringUtil.getUploadFilePath() + ConstantUtil.WATER_IMAGE_PATH;
	
	public static final String WATERMARK = "Watermark";
	
    /**
          * 对PDF文件添加水印
     * @param strSrcPath /app/file/1.pdf
     * @param strDesPath /app/file/2.pdf
     * @return
     * @throws Exception
     */
    public static boolean pdfToWater(String inputFilePath, String outputFilePath){
    	boolean result = false;
    	try{
        	PdfReader pdfReader = new PdfReader(inputFilePath);
        	int numberOfPages = pdfReader.getNumberOfPages();
        	FileOutputStream outputStream = new FileOutputStream(outputFilePath, true);
        	PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
        	PdfContentByte waterMarkContent;
            BaseFont bf = BaseFont.createFont(WATER_FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            pdfStamper.setEncryption(WATER_PASS.getBytes(), WATER_PASS.getBytes(), PdfWriter.ALLOW_MODIFY_CONTENTS, false);

            PdfLayer layer = new PdfLayer(WATERMARK, pdfStamper.getWriter());
            for (int i = 1; i <= numberOfPages; i++){
                waterMarkContent = pdfStamper.getOverContent(i);
                Rectangle rect = pdfReader.getPageSize(i);
                waterMarkContent.beginLayer(layer);
                PdfName pdfname = new PdfName(WATERMARK);
                waterMarkContent.saveState();
                waterMarkContent.beginMarkedContentSequence(pdfname);
                int nLen = WATER_COM.length();
                float nFontsize = 0;
                if(nLen <= 10){
                    nFontsize = 20;
                    nLen = 5 * nLen;
                }else if (nLen > 10 && nLen <= 15){
                    nFontsize = 18;
                    nLen = 4 * nLen;
                }else if (nLen > 15 && nLen <= 20){
                    nFontsize = 16;
                    nLen = 4 * nLen;
                }else{
                    nFontsize = 12;
                    nLen = 3 * nLen;
                }
                //字体路径需要传入
                waterMarkContent.setFontAndSize(bf, nFontsize);
                PdfGState gState = new PdfGState();
                gState.setFillOpacity(0.4f);
                waterMarkContent.setGState(gState);
                waterMarkContent.setColorFill(BaseColor.BLUE);
                waterMarkContent.beginText();
                Image img = Image.getInstance(WATER_IMAGE_PATH);
                float ftWidth, ftHeight;
                if (rect.getWidth()> rect.getHeight()){
                    ftWidth = rect.getHeight();
                    ftHeight = rect.getWidth();
                }else{
                    ftWidth = rect.getWidth();
                    ftHeight = rect.getHeight();
                }
                img.scalePercent(100f);
                img.scaleAbsolute(400, 400);
                img.setAbsolutePosition(ftWidth / 2 - 200, ftHeight / 2 - 200);
                waterMarkContent.addImage(img);
                waterMarkContent.showTextAligned(PdfContentByte.ALIGN_LEFT, WATER_COM, 10, 50, 52);
                // waterMarkContent.showTextAligned(PdfContentByte.ALIGN_LEFT, szCompanyName, 100, 500, 0);
                waterMarkContent.showTextAligned(PdfContentByte.ALIGN_LEFT, WATER_COM, ftWidth - 190, ftHeight - 260, 52);
                waterMarkContent.showTextAligned(PdfContentByte.ALIGN_LEFT, WATER_COM, 10, ftHeight - 260, 52);
                waterMarkContent.showTextAligned(PdfContentByte.ALIGN_LEFT, WATER_COM, ftWidth - 190, 50, 52);
                if (nFontsize-4>12){
                    nFontsize = 12;
                }
                waterMarkContent.showTextAligned(PdfContentByte.ALIGN_RIGHT, SIZE_BOTTOM_TEXT, rect.getWidth() - 30, 10, 0);
                waterMarkContent.showTextAligned(PdfContentByte.ALIGN_RIGHT, SIZE_TOP_TEXT, rect.getWidth() - 30, rect.getHeight() - 20, 0);
           
                waterMarkContent.endText();
                waterMarkContent.endLayer();
            }
            pdfStamper.close();
            pdfReader.close();
            outputStream.close();
            result = true;
			logger.debug("WaterToPdfUtil.pdfToWater method executed is successful... "); 
        }catch (Exception e){
			logger.error("WaterToPdfUtil.pdfToWater method executed is error: ", e); 
        }
        return result;
    }
    
}