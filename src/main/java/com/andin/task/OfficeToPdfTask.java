package com.andin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.andin.model.TaskModel;
import com.andin.utils.HttpClientUtil;
import com.andin.utils.OfficeFileUtil;
import com.andin.utils.StringUtil;

/**
 * OFFICE转PDF文件的定时任务
 * @author Administrator
 *
 */
@Component
@EnableScheduling
public class OfficeToPdfTask {
	
    private static Logger logger = LoggerFactory.getLogger(OfficeToPdfTask.class);

	@Scheduled(cron = "*/5 * * * * ?")/** 每五秒触发一次 **/
	public void getOfficeTaskListToPdf() throws Exception{
		logger.debug("OfficeToPdfTask.getOfficeTaskListToPdf method executed is start...");
		//获取任务列表
		TaskModel task = HttpClientUtil.getTask();
		if(task != null) {
			Boolean downloadResult = false;
			Boolean officeToPdfResult = false;
			Boolean uploadResult = false;
			Boolean updateResult = false;
			logger.debug("OfficeToPdfTask.getOfficeTaskListToPdf method task params is: " + task.toString());
			String taskId = task.getId();
			String name = task.getFilename();
			String fileType = StringUtil.getFileTypeByType(task.getFiletype());
			String fileName = name + fileType;
			//通过文件ID从PHP下载文件
			downloadResult = HttpClientUtil.downloadFile(taskId, fileName);
			if(downloadResult) {
				//开始OFFICE转换PDF
				officeToPdfResult = OfficeFileUtil.officeToPdf(fileName);
				if(officeToPdfResult) {
					//通过文件名获取转换好的PDF文件的路径
					String filePath = StringUtil.getPdfFilePathByFileName(fileName, name);
					//上传文件到PHP
					uploadResult = HttpClientUtil.uploadFile(taskId, filePath);
					if(uploadResult) {
						//更新任务的转换状态						
						updateResult = HttpClientUtil.updateTaskStatus(taskId);
					}
				}
			}
			String result = "[downloadResult=" + downloadResult + "], [officeToPdfResult=" + officeToPdfResult + "], [uploadResult=" + uploadResult + "], [updateResult=" + updateResult + "]";
			logger.debug("OfficeToPdfTask.getOfficeTaskListToPdf method executed task result is: " + result);
		}
		logger.debug("OfficeToPdfTask.getOfficeTaskListToPdf method executed is end...");
	}
	
}
