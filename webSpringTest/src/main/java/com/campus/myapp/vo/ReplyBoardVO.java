package com.campus.myapp.vo;

public class ReplyBoardVO {
	private int replyno, no;
	private String userid, coment, writedate;
	
	@Override
	public String toString() {
		return "ReplyBoardVO [replyno=" + replyno + ", no=" + no + ", userid=" + userid + ", coment=" + coment
				+ ", writedate=" + writedate + "]";
	}

	public int getReplyno() {
		return replyno;
	}

	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	
}
