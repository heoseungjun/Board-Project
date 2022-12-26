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
	
	// �ڷ�� ���
	@RequestMapping("/data/dataList")
	public String dataList(Model model,DataVO vo) {
		// �ڷ�� record ����
		model.addAttribute("list",service.dataSelect(vo));
		return "data/dataList";
	}
	
	// �ڷ� ���
	@GetMapping("/data/dataForm")
	public String dataForm() {
		return "data/dataForm";
	}
	
	// �ڷ�� ��/���� ���ε�
	@PostMapping("/data/dataFormOk")						  // ����, �۳����� setter
	public ModelAndView dataFormOk(HttpServletRequest request,DataVO vo) {
		// ���� ���� ���� ��ü�� ������ ��������
		// DataVO vo=new DataVO();
		// vo.setSubject(request.getParameter("subject"));
		// vo.setContent(request.getParameter("content"));
		
		// �۾��� vo�� ���
		vo.setUserid((String)request.getSession().getAttribute("logid"));
		// ���� ���ε� - ���ε��� ��ġ�� �����ּ� �ʿ�(upload ������ �����ּ�), ��Ŭ���� �� �ּҴ� �����ּҰ� �ƴϴ�
		String path = request.getSession().getServletContext().getRealPath("/upload");
		// request ��ü�� �̿��Ͽ� MultipartHttpServletRequest ��ü ����(Ÿ��ĳ����) �� ���� ���ε� ó��
		// MultiPartServletRequest ��ü���� ���� ����ŭ MultiPartFile ��ü�� ����
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		List<MultipartFile> files = mr.getFiles("filename");    // �̰� ���� ÷�������� name�� ��� filename���� �ߴ�
		
		// ���ε��� ���ϸ��� ������ ����
		List<String> filenameList=new ArrayList<String>();
		// ÷�������� ���� ��
		if(files!=null) {
			// ÷������ ������ŭ �ݺ�
			for(int i=0;i<files.size();i++) {
				MultipartFile mf=files.get(i);
				// ���ε� �� ������ ���� ���ϸ� ������
				String fName=mf.getOriginalFilename();
				
				// ������ fName�� ������ fName���� ���ε��ϰ� fName�� ������ ���ο� ���ϸ� �����
				if(fName!=null&&!fName.equals("")) {    // ���� ���ϸ��� �����ϸ�
					File f=new File(path,fName);
					if(f.exists()) {
						//���ϸ� ����
						for(int renameNum=1;;renameNum++) {
							int dot = fName.lastIndexOf(".");                // �� ������ ��
							String fileNameNoExt = fName.substring(0,dot);   // ���ϸ� �и�
							String extend = fName.substring(dot);            // Ȯ����(�� ������ �� �ڿ�) �и�
							
							// Zoomit(1).exe
							String newFileName = fileNameNoExt+"("+renameNum+")"+extend;    // ���ο� ���ϸ�
							
							f = new File(path,newFileName);
							// ��ģ ���ϸ��� ������ ������ �ݺ��� ����, ������ �ٽ� �ݺ�
							if(!f.exists()) {
								fName = newFileName;
								break;
							}
						}
					}
					// ���� ���ε�
					try {
						mf.transferTo(new File(path,fName));    // MultipartFile�� transferTO �޼ҵ� : ���� ���ε�
					}catch (IOException e) {
						System.out.println("���� ���ε� ���� �߻�");
					}
					// ���ε��� ���ϸ� ����(���� ���ϸ� or rename�� ���ϸ�) -> �� ���� ���ε� ���Ŀ� �ǽ�
					filenameList.add(fName);
				}
			}
			// ���ε��� ���ϸ��� vo�� ���
			vo.setFilename1(filenameList.get(0));
			if(filenameList.size()==2) vo.setFilename2(filenameList.get(1));
		}
		// DB�� ���ڵ� �߰�
		ModelAndView mav=new ModelAndView();
		int result=service.dataInsert(vo);
		
		// ���ڵ� �߰� ���� �� �̹� ���ε�� ���� ����
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
	// �ڷ�� �� ���� ����
	@GetMapping("/data/dataView")
	public ModelAndView dataView(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("vo", service.dataView(no));
		mav.setViewName("data/dataView");
		return mav;
	}
	
	// �� ���� ��
	@GetMapping("/data/dataEdit")
	public ModelAndView dataEdit(@RequestParam("no") int no) {
		ModelAndView mav= new ModelAndView();
		DataVO vo = service.dataView(no);
		// ÷������ ��
		int cnt=0;
		if(vo.getFilename1()!=null) cnt++;
		if(vo.getFilename2()!=null) cnt++;
		
		mav.addObject("vo", vo);
		mav.addObject("cnt", cnt);
		mav.setViewName("data/dataEditForm");
		return mav;
	}
	// �� ����(DB)
	@PostMapping("/data/dataEditOk")    // ����,�۳���,�������ϸ�
	public ModelAndView dataEditOk(DataVO vo,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
//											 ���� ���ε�, ���� ��ü �ʿ�
		vo.setUserid((String)request.getSession().getAttribute("logid"));
		String path=request.getSession().getServletContext().getRealPath("/upload");
		
		MultipartHttpServletRequest ms=(MultipartHttpServletRequest)request;
		
		// DB�� �ִ� ���� �� ���ϸ�
		DataVO dbVO=service.selectFilename(vo.getNo());
		
		// DB���ϸ�, �������ϸ�, ���� ���ε��� ���ϸ��� �����ϱ� ���� ��ü
		List<String> dbFile = new ArrayList<String>();
		
		// DB���� �о�� ���� �� ���ϸ� �߰�
		dbFile.add(dbVO.getFilename1());
		if(dbVO.getFilename2()!=null) dbFile.add(dbVO.getFilename2());
		
		// ���� ȭ�鿡�� ������ ������ dbFile���� ���� (���ϸ� ����)
		if(vo.getDelFile()!=null) {
			for(String delFilename:vo.getDelFile()) {
				System.out.println("delFilename->"+delFilename);
				dbFile.remove(delFilename);
			}
		}
		
		// ���� ���ε�
		List<MultipartFile> mfFiles = ms.getFiles("filename");
		List<String> filenameList = new ArrayList<String>();
		if(mfFiles!=null) {
			// ���� ����ŭ �ݺ�
			for(int i=0;i<mfFiles.size();i++) {
				MultipartFile mf = mfFiles.get(i);
				// ���ε��� ���� ���ϸ�
				String fName=mf.getOriginalFilename();
				// ���� ���ϸ��� ������ rename
				if(fName!=null && !fName.equals("")) {
					File f=new File(path,fName);
					if(f.exists()) {
						for(int fNum=1;;fNum++) {
							int dot=fName.lastIndexOf(".");
							String filenameNoExt = fName.substring(0, dot);
							String ext = fName.substring(dot);
							
							String newFilename=filenameNoExt+"("+fNum+")"+ext;
							
							// ���� ������� ���ϸ��� �����ϴ��� Ȯ��
							f=new File(newFilename,path);
							if(!f.exists()) {
								fName=newFilename;
								break;
							}
						}
					}
				}
				// ���� ���ε�
				try {
					mf.transferTo(new File(path,fName));
				} catch (IOException e) {
					System.out.println("���� ���ε� ���� �߻�");
				}
				
				filenameList.add(fName);    
				dbFile.add(fName);          // dbFile �����ͺ��̽� ������Ʈ �� �ʿ�
			}
		}
		
		// dbFile�� ������ vo�� ����
		for(int i=0;i<dbFile.size();i++) {
			System.out.println("dbFile->"+dbFile.get(i));
			if(i==0) vo.setFilename1(dbFile.get(i));
			if(i==1) vo.setFilename2(dbFile.get(i));
		}
		
		// DB ������Ʈ
		int result=service.dataUpdate(vo);
		// ������Ʈ ���� -> upload ���Ͽ��� ������ ���� ����
		// ������Ʈ ���� -> ���� ���ε�� ���� ����
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
	// �ڷ�� �� ����
	@GetMapping("/data/dataDel/{no}")    // {����} 
	public ModelAndView dataDel(@PathVariable("no") int no,HttpSession session) {    // �ּҿ� �ִ� ������ �޾ƿ���
		String path=session.getServletContext().getRealPath("/upload");
		String userid=(String)session.getAttribute("logid");
		// ������ ���ڵ��� ���ϸ� �����ϱ�
		DataVO dbFile = service.selectFilename(no);
		// ���ڵ� ����
		int result=service.dataDelete(no, userid);
		
		// ���� ���� : ���� ���� �� �� ������� �̵�
		// ���� ���� : �� ���뺸��� �̵�
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
			mav.setViewName("redirect:/data/dataView");    // history.back()�� ���� ������ �����ϱ� ����, ���� ������ �ʿ���ٸ� redirect�� ����
		}
		return mav;
	}
	
	// ���� ����
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
 	1. ���� ���ε� ������ ���� �ʿ��� �����ӿ�ũ
 	commons-io
 	commons-fileupload�� pom.xml�� dependency
 	
 	2. root-context.xml��
 	multiPartResolver ��ü ����
*/