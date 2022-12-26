package com.campus.myapp.vo;

import java.util.Arrays;
import java.util.List;

public class DataVO {
	private String userid,subject,content,writedate,filename1,filename2;
	private int no,hit;
	private List<String> delFile;

	@Override
	public String toString() {
		return "DataVO [userid=" + userid + ", subject=" + subject + ", content=" + content + ", writedate=" + writedate
				+ ", filename1=" + filename1 + ", filename2=" + filename2 + ", no=" + no + ", hit=" + hit + ", delFile="
				+ delFile + "]";
	}

	public List<String> getDelFile() {
		return delFile;
	}

	public void setDelFile(List<String> delFile) {
		this.delFile = delFile;
	}

	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWritedate() {
		return writedate;
	}
	
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	public String getFilename1() {
		return filename1;
	}
	
	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}
	
	public String getFilename2() {
		return filename2;
	}
	
	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
}
