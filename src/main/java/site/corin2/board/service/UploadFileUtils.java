/**
    파일명: UploadFileUtils.java
    설   명: S3 업로드 파일 유틸
    작성일: 2018. 6. 27.
    작성자: 강 성 훈
*/

package site.corin2.board.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String projectNum, String originalName, byte[] byteData) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "corin2.site";
		
		// savedName : 1529888132496_image.jpg 같은 형식으로 만들어준다.
		String savedName = "/" + System.currentTimeMillis() + "_" + originalName; //현재날짜_파일명.확장자
		
		logger.info("업로드 경로: " + uploadPath);
		
		// '/project11/20180628'
		String savedPath = calcPath(uploadPath, projectNum);
		// '/project11/20180628/1529888132496_image.jpg'
		String uploadedFileName = (savedPath + savedName).replace(File.separatorChar, '/');
		// '/resources/upload/project11/20180628/1529888132496_image.jpg'
		String finalFilePath = (uploadPath + uploadedFileName).replace(File.separatorChar, '/');
		
		// S3Util의 fileUpload 메서드로 파일을 업로드한다.
		s3.fileUpload(bucketName, finalFilePath, byteData);
		
		logger.info(uploadedFileName);
		
		return savedName;
	}
	
	private static String calcPath(String uploadPath, String projectNum) {
		Calendar cal = Calendar.getInstance();

		/*String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));*/

		int yearPath = cal.get(Calendar.YEAR);
		String monthPath = yearPath + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		// '/project11/20180628' 식으로 경로 설정 
		String sortedPath = "/project" + projectNum + "/" + datePath;
		makeDir(uploadPath, projectNum, sortedPath);

		return sortedPath;
	}
	
	private static void makeDir(String uploadPath, String projectNum, String sortedPath) {
		if (new File(sortedPath).exists()) {
			return;
		}

		File dirPath = new File(uploadPath + sortedPath);

		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
	}
}