package com.ic.param;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vo.PhotoVo;

@Controller
public class FileUploadController {
	
		@Autowired
		ServletContext application;

		@Autowired 
		HttpSession session;
		
		  @Autowired
		  HttpServletRequest request;
	
	
	//낱개로 받기	
@RequestMapping("/fileupload1.do")
public String fileupload1(String title, @RequestParam MultipartFile photo, Model model) throws Exception, IOException {	
	
	//MultpartFile 처리과정
	//전처리작업 : 업로드된 파일을 임시저장소에 보관후
	//			   MultpartFile형식으로 저장파일정보를 통보
	//	 		   @RequestParam은 반드시 붙여야한다.
	
	String web_path = "/resources/upload/";
	String abs_path = application.getRealPath(web_path);
	System.out.println(abs_path);
	
	String filename = "no_file";
	if(!photo.isEmpty()) {
	filename = photo.getOriginalFilename();
	File f = new File(abs_path, filename);
	
	//동일파일 체크
	if(f.exists()) {
		long tm = System.currentTimeMillis();//현재시스템시간을 1/0000초단위로 구한다
		filename = String.format("%d_%s", tm,filename);
		f = new File(abs_path, filename);
	}
	
	//임시파일정보를 File f로 복사	
	photo.transferTo(f);
	}
	model.addAttribute("title",title);
	model.addAttribute("filename",filename);
	
	return "result_photo";
	}

//객체로 받기
@RequestMapping("/fileupload2.do")
public String fileupload2(PhotoVo vo, Model model) throws Exception, IOException {	
	
	//MultpartFile 처리과정
	//전처리작업 : 업로드된 파일을 임시저장소에 보관후
	//			   MultpartFile형식으로 저장파일정보를 통보
	//	 		   @RequestParam은 반드시 붙여야한다.
	
	String web_path = "/resources/upload/";
	String abs_path = application.getRealPath(web_path);
	System.out.println(abs_path);
	
	String filename = "no_file";
	MultipartFile photo = vo.getPhoto();
	if(!photo.isEmpty()) {
	filename = photo.getOriginalFilename();
	File f = new File(abs_path, filename);
	
	//동일파일 체크
	if(f.exists()) {
		long tm = System.currentTimeMillis();//현재시스템시간을 1/0000초단위로 구한다
		filename = String.format("%d_%s", tm,filename);
		f = new File(abs_path, filename);
	}
	
	//임시파일정보를 File f로 복사	
	photo.transferTo(f);
	}
	vo.setFilename(filename);
	model.addAttribute("vo",vo);
	
	
	return "result_photo2";
	}


@RequestMapping("/multi_fileupload1.do")
public String mult_fileupload1(String title, 
							   @RequestParam("photo") MultipartFile [] photo_array, 
							   Model model) throws Exception, IOException{
	String web_path = "/resources/upload/";
	String abs_path = application.getRealPath(web_path);
	
	String filename1 = "no_file";
	String filename2 = "no_file";
	
	
	for(int i=0; i<photo_array.length;i++) {
		MultipartFile photo = photo_array[i];
		
		if(!photo_array[i].isEmpty()) {
		String filename = photo_array[i].getOriginalFilename();
			File f = new File(abs_path, filename);	
			//동일파일 체크
		if(f.exists()) {
			long tm = System.currentTimeMillis();//현재시스템시간을 1/0000초단위로 구한다
			filename1 = String.format("%d_%s", tm,filename);
			f = new File(abs_path, filename);
		}
		photo.transferTo(f);
		
		if(i==0)filename1 = filename;
		if(i==1)filename2 = filename;
		}//end isEmpty
	}//end for
	//임시파일정보를 File f로 복사	
	model.addAttribute("filename1",filename1);
	model.addAttribute("filename2",filename2);
	model.addAttribute("title",title);
	
	
	return "result_multi_photo";
}

@RequestMapping("/multi_fileupload2.do")
public String mult_fileupload2(String title, 
							   @RequestParam("photo") List<MultipartFile> photo_list, 
							   Model model) throws Exception, IOException{
	String web_path = "/resources/upload/";
	String abs_path = application.getRealPath(web_path);
	
	List<String> file_list = new ArrayList<String>();
	
	for(MultipartFile photo : photo_list) {
		if(!photo.isEmpty()) {
			String filename = photo.getOriginalFilename();
			File f = new File(abs_path, filename);	
			//동일파일 체크
			if(f.exists()) {
			long tm = System.currentTimeMillis();//현재시스템시간을 1/0000초단위로 구한다
			filename = String.format("%d_%s", tm,filename);
			f = new File(abs_path, filename);
			}
			photo.transferTo(f);
			file_list.add(filename);
		}
	}
	model.addAttribute("title",title);
	model.addAttribute("file_list",file_list);

	return "result_multi_photo2";

}
}

