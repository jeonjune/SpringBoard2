package com.itwillbs.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiOptionPaneUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;


@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private static final String FAKE_PATH="/upload";
	
	@RequestMapping(value = "/form",method = RequestMethod.GET)
	public void uploadFormGET() throws Exception{
		logger.debug(" uploadFormGET() 호출 ");
		
	}
	
	
	// 파일 업로드 처리
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public String fileUploadPOST(MultipartHttpServletRequest multiRequest, Model model ) throws Exception{
		
		// 파일 업로드 처리 -> 전달한 정보를 저장
		
		// 한글처리 인코딩
		multiRequest.setCharacterEncoding("UTF-8");
		
		// 파라메터 정보 저장 -> Map(K,V)
		Map paramMap = new HashMap();
		
		// input-file의 정보를 제외한 모든 정보 가져오기
		Enumeration enu = multiRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			// 파라메터명
			String name = (String)enu.nextElement();
//			logger.debug("name : "+name);
			String value = multiRequest.getParameter(name);
//			logger.debug("value : "+value);
			// 전달받은 파라메터 정보를 Map에 저장
			paramMap.put(name, value);
		}
		
		logger.debug(" paramMap : {}",paramMap);
		// 파일의 정보를 제외한 모든 파라메터 정보를 저장완료!
		//////////////////////////////////////////////////////////////////
		// 파일 업로드 -> 정보를 Map 추가 저장
		List<String> fileNameList = fileProcess(multiRequest);
		
		// map 파일의 이름 정보를 저장
		paramMap.put("fileNameList",fileNameList);
		
		logger.debug(" paramMap : {}",paramMap);
		
		// model 객체에 정보저장
		model.addAttribute("paramMap",paramMap);
		
		return "/result";
	} //upload-post
	
	// 파일업로드 모듈(메서드)
	public List<String> fileProcess(MultipartHttpServletRequest multiRequest) throws Exception{
		logger.debug(" fileProcess() : 파일 업로드 처리 시작! ");
		
		// 업로드된 파일정보를 저장하는 List
		List<String> fileNameList = new ArrayList<String>();
		
		// 전달된 파일의 정보를 저장
		Iterator<String> fileNames = multiRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			logger.debug(" fileName : "+fileName);
			// 파일 파라메터 이름을 사용해서 파일 임시저장
			MultipartFile mFile = multiRequest.getFile(fileName);
			
			// 파일의 원본 이름을 리스트에 저장
			String oFileName = mFile.getOriginalFilename();
			fileNameList.add(oFileName);
			
			// 파일 업로드
			// 파일 생성
//			File file = new File("C:\\upload+"+"\\"+fileName);
//			File file = new File("C:\\upload+"+"\\"+oFileName);
			
			
//					File file = new File(multiRequest.getRealPath("/upload")+"\\"+oFileName);
			File file = new File(multiRequest.getRealPath(FAKE_PATH)+"\\"+oFileName);
			
			if(mFile.getSize() != 0 ) { // 첨부파일이 있을 때
				if(!file.exists()) {
					// -> file 해당 경로에 파일이 없으면 디렉토리 생성 후 파일 진행
					if(file.getParentFile().mkdirs()) {
						// 파일을 생성
						file.createNewFile();
					} 
				}
				// 임시 생성파일을 실제 파일의 정보로 전달
				mFile.transferTo(file);
			}
			// 파일 업로드 오나료!
			
		}
		logger.debug(" fileNameList: {}",fileNameList);
		logger.debug(" 파일 업로드 완료!, 파일 이름 저장 오나료!");
		// 파일이름 저장 오나료!
		
		
		return fileNameList;
	} // fileProcess
		
	// 파일 다운로드
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,
						 HttpServletResponse resp,
						 HttpServletRequest req ) throws Exception {
		logger.debug(" downloadGET() 실행 ");
		
		// 외부(브라우저)로 통신가능한 통로
		OutputStream out = resp.getOutputStream();
		
		// 다운로드할 파일의 정보(위치)
		String downFile = req.getRealPath(FAKE_PATH)+"\\"+fileName;
		
		// 다운로드할 파일 생성
		File file = new File(downFile);
		
		// ----------------------- 썸네일 생성 -----------------------
		
		int lastIdx = fileName.lastIndexOf(".");
		String thumbName = fileName.substring(0,lastIdx);
		
		File thumbnail = new File(req.getRealPath(FAKE_PATH)+"\\"+"thumbnail"+"\\"+thumbName+".png");
		
		if(file.exists()) {
			
			// 썸네일 폴더 생성-> 썸네일 이미지 파일 생성
//			thumbnail.getParentFile().mkdirs();
//			Thumbnails.of(file).size(50, 50).outputFormat("png").toFile(thumbnail);
			
			// 썸네일을 바로 출력
			Thumbnails.of(file).size(120, 120).outputFormat("gif").toOutputStream(out);
//			Thumbnails.of(file).size(120, 120).outputFormat("png").toOutputStream(out);
		}
		
		// ----------------------- 썸네일 생성 -----------------------
		
		// 다운로드에 필요한 설정
		resp.setHeader("Cache-Control", "no-cache");
//		resp.addHeader("Content-disposition", "attachment; fileNames="+fileName);
		resp.addHeader("Content-disposition", "attachment; fileNames="+URLEncoder.encode(fileName,"UTF-8"));
		
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buffer = new byte [1024 * 8];
		while(true) {
			int data = fis.read(buffer);
			if(data==-1) break; // 파일의 끝
			
			out.write(buffer,0,data);
		}
		fis.close();
		out.close();
	}
	
	
	

}//controller
