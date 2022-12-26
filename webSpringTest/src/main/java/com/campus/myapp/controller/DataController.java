package com.campus.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.DataService;
import com.campus.myapp.vo.DataVO;

@Controller
public class DataController {
	@Autowired
	DataService service;
	
	// 자료실 목록
	@RequestMapping("/data/dataList")
	public String dataList(Model model,DataVO vo) {
		// 자료실 record 선택
		model.addAttribute("list",service.dataSelect(vo));
		return "data/dataList";
	}
	
	// 자료 등록
	@GetMapping("/data/dataForm")
	public String dataForm() {
		return "data/dataForm";
	}
	
	// 자료실 글/파일 업로드
	@PostMapping("/data/dataFormOk")						  // 제목, 글내용이 setter
	public ModelAndView dataFormOk(HttpServletRequest request,DataVO vo) {
		// 폼의 값과 파일 객체를 서버로 가져오기
		// DataVO vo=new DataVO();
		// vo.setSubject(request.getParameter("subject"));
		// vo.setContent(request.getParameter("content"));
		
		// 글쓴이 vo에 담기
		vo.setUserid((String)request.getSession().getAttribute("logid"));
		// 파일 업로드 - 업로드할 위치의 절대주소 필요(upload 파일의 절대주소), 이클립스 상 주소는 절대주소가 아니다
		String path = request.getSession().getServletContext().getRealPath("/upload");
		// request 객체를 이용하여 MultipartHttpServletRequest 객체 생성(타입캐스팅) 후 파일 업로드 처리
		// MultiPartServletRequest 객체에는 파일 수만큼 MultiPartFile 객체가 존재
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		List<MultipartFile> files = mr.getFiles("filename");    // 이걸 위해 첨부파일의 name을 모두 filename으로 했다
		
		// 업로드한 파일명을 보관할 변수
		List<String> filenameList=new ArrayList<String>();
		// 첨부파일이 있을 때
		if(files!=null) {
			// 첨부파일 개수만큼 반복
			for(int i=0;i<files.size();i++) {
				MultipartFile mf=files.get(i);
				// 업로드 시 선택한 원래 파일명 얻어오기
				String fName=mf.getOriginalFilename();
				
				// 서버에 fName이 없으면 fName으로 업로드하고 fName이 있으면 새로운 파일명 만들기
				if(fName!=null&&!fName.equals("")) {    // 원래 파일명이 존재하면
					File f=new File(path,fName);
					if(f.exists()) {
						//파일명 변경
						for(int renameNum=1;;renameNum++) {
							int dot = fName.lastIndexOf(".");                // 맨 마지막 점
							String fileNameNoExt = fName.substring(0,dot);   // 파일명 분리
							String extend = fName.substring(dot);            // 확장자(맨 마지막 점 뒤에) 분리
							
							// Zoomit(1).exe
							String newFileName = fileNameNoExt+"("+renameNum+")"+extend;    // 새로운 파일명
							
							f = new File(path,newFileName);
							// 고친 파일명이 서버에 없으면 반복문 종료, 있으면 다시 반복
							if(!f.exists()) {
								fName = newFileName;
								break;
							}
						}
					}
					// 파일 업로드
					try {
						mf.transferTo(new File(path,fName));    // MultipartFile의 transferTO 메소드 : 파일 업로드
					}catch (IOException e) {
						System.out.println("파일 업로드 예외 발생");
					}
					// 업로드한 파일명 보관(원래 파일명 or rename한 파일명) -> 꼭 파일 업로드 이후에 실시
					filenameList.add(fName);
				}
			}
			// 업로드한 파일명을 vo에 담기
			vo.setFilename1(filenameList.get(0));
			if(filenameList.size()==2) vo.setFilename2(filenameList.get(1));
		}
		// DB에 레코드 추가
		ModelAndView mav=new ModelAndView();
		int result=service.dataInsert(vo);
		
		// 레코드 추가 실패 시 이미 업로드된 파일 삭제
		if(result==0) {
			for(int i=0;i<filenameList.size();i++) {
				File delFile=new File(path,filenameList.get(i));
				delFile.delete();
			}
			mav.addObject("vo", vo);
			mav.setViewName("data/dataFormResult");
		}else {
			mav.setViewName("redirect:dataList");
		}
		return mav;
	}
	// 자료실 글 내용 보기
	@GetMapping("/data/dataView")
	public ModelAndView dataView(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("vo", service.dataView(no));
		mav.setViewName("data/dataView");
		return mav;
	}
	
	// 글 수정 폼
	@GetMapping("/data/dataEdit")
	public ModelAndView dataEdit(@RequestParam("no") int no) {
		ModelAndView mav= new ModelAndView();
		DataVO vo = service.dataView(no);
		// 첨부파일 수
		int cnt=0;
		if(vo.getFilename1()!=null) cnt++;
		if(vo.getFilename2()!=null) cnt++;
		
		mav.addObject("vo", vo);
		mav.addObject("cnt", cnt);
		mav.setViewName("data/dataEditForm");
		return mav;
	}
	// 글 수정(DB)
	@PostMapping("/data/dataEditOk")    // 제목,글내용,삭제파일명
	public ModelAndView dataEditOk(DataVO vo,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
//											 파일 업로드, 새션 객체 필요
		vo.setUserid((String)request.getSession().getAttribute("logid"));
		String path=request.getSession().getServletContext().getRealPath("/upload");
		
		MultipartHttpServletRequest ms=(MultipartHttpServletRequest)request;
		
		// DB에 있는 수정 전 파일명
		DataVO dbVO=service.selectFilename(vo.getNo());
		
		// DB파일명, 삭제파일명, 새로 업로드한 파일명을 관리하기 위한 객체
		List<String> dbFile = new ArrayList<String>();
		
		// DB에서 읽어온 수정 전 파일명 추가
		dbFile.add(dbVO.getFilename1());
		if(dbVO.getFilename2()!=null) dbFile.add(dbVO.getFilename2());
		
		// 수정 화면에서 삭제한 파일을 dbFile에서 삭제 (파일명 삭제)
		if(vo.getDelFile()!=null) {
			for(String delFilename:vo.getDelFile()) {
				System.out.println("delFilename->"+delFilename);
				dbFile.remove(delFilename);
			}
		}
		
		// 파일 업로드
		List<MultipartFile> mfFiles = ms.getFiles("filename");
		List<String> filenameList = new ArrayList<String>();
		if(mfFiles!=null) {
			// 파일 수만큼 반복
			for(int i=0;i<mfFiles.size();i++) {
				MultipartFile mf = mfFiles.get(i);
				// 업로드한 원래 파일명
				String fName=mf.getOriginalFilename();
				// 원래 파일명이 있으면 rename
				if(fName!=null && !fName.equals("")) {
					File f=new File(path,fName);
					if(f.exists()) {
						for(int fNum=1;;fNum++) {
							int dot=fName.lastIndexOf(".");
							String filenameNoExt = fName.substring(0, dot);
							String ext = fName.substring(dot);
							
							String newFilename=filenameNoExt+"("+fNum+")"+ext;
							
							// 새로 만들어진 파일명이 존재하는지 확인
							f=new File(newFilename,path);
							if(!f.exists()) {
								fName=newFilename;
								break;
							}
						}
					}
				}
				// 실제 업로드
				try {
					mf.transferTo(new File(path,fName));
				} catch (IOException e) {
					System.out.println("파일 업로드 오류 발생");
				}
				
				filenameList.add(fName);    
				dbFile.add(fName);          // dbFile 데이터베이스 업데이트 시 필요
			}
		}
		
		// dbFile의 내용을 vo에 세팅
		for(int i=0;i<dbFile.size();i++) {
			System.out.println("dbFile->"+dbFile.get(i));
			if(i==0) vo.setFilename1(dbFile.get(i));
			if(i==1) vo.setFilename2(dbFile.get(i));
		}
		
		// DB 업데이트
		int result=service.dataUpdate(vo);
		// 업데이트 성공 -> upload 파일에서 수정한 파일 삭제
		// 업데이트 실패 -> 새로 업로드된 파일 삭제
		if(result>0) {
			fileDelete(vo.getDelFile(),path);
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:dataView");
		}else {
			fileDelete(filenameList,path);
			mav.setViewName("data/dataEditResult");
		}
		return mav;
	}
	// 자료실 글 삭제
	@GetMapping("/data/dataDel/{no}")    // {변수} 
	public ModelAndView dataDel(@PathVariable("no") int no,HttpSession session) {    // 주소에 있는 변수를 받아오기
		String path=session.getServletContext().getRealPath("/upload");
		String userid=(String)session.getAttribute("logid");
		// 삭제할 레코드의 파일명 보관하기
		DataVO dbFile = service.selectFilename(no);
		// 레코드 삭제
		int result=service.dataDelete(no, userid);
		
		// 삭제 성공 : 파일 삭제 후 글 목록으로 이동
		// 삭제 실패 : 글 내용보기로 이동
		ModelAndView mav=new ModelAndView();
		if(result>0) {
			File f=new File(path,dbFile.getFilename1());
			f.delete();
			if(dbFile.getFilename2()!=null) {
				f=new File(path,dbFile.getFilename2());
				f.delete();
			}
			mav.setViewName("redirect:/data/dataList");
		}else {
			mav.addObject("no",no);
			mav.setViewName("redirect:/data/dataView");    // history.back()은 이전 내용을 유지하기 위함, 내용 유지가 필요없다면 redirect가 좋음
		}
		return mav;
	}
	
	// 파일 삭제
	public void fileDelete(List<String> delFileList,String path) {
		if(delFileList!=null) {
			for(String file:delFileList) {
				File f=new File(path,file);
				f.delete();
			}
		}
	}
}

/*
 	1. 파일 업로드 구현을 위해 필요한 프레임워크
 	commons-io
 	commons-fileupload를 pom.xml에 dependency
 	
 	2. root-context.xml에
 	multiPartResolver 객체 생성
*/