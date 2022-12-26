package com.campus.myapp.vo;

public class ZipcodeVO {
	private String zipcode,sido,doroName,buildName,dong;
	private int build1,build2,num1,num2;
	
	@Override
	public String toString() {
		return "ZipcodeVO [zipcode=" + zipcode + ", sido=" + sido + ", doroname=" + doroName + ", buildname="
				+ buildName + ", dong=" + dong + ", build1=" + build1 + ", build2=" + build2 + ", num1=" + num1
				+ ", num2=" + num2 + "]";
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getDoroName() {
		return doroName;
	}

	public void setDoroName(String doroName) {
		this.doroName = doroName;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public int getBuild1() {
		return build1;
	}

	public void setBuild1(int build1) {
		this.build1 = build1;
	}

	public int getBuild2() {
		return build2;
	}

	public void setBuild2(int build2) {
		this.build2 = build2;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	
}
