package com.campus.myapp.vo;

public class PagingVO {
	//페이징
	private int nowPage=1;    // 현재페이지
	private int onePageRecord=5;    // 한 페이지에 표시할 레코드 수
	private int totalRecord;    // 총 레코드 수:DB 조회 결과에 따라 다름
	private int totalPage;    // 총 페이지 수

	//페이지 번호 매길 때
	private int onePageCount=5;    // 한 번에 표시할 페이지 수
	private int startPage=1;       //시작 페이지
	private int restRecord=onePageRecord;   // 마지막 페이지에 선택할 레코드 수
	
	//검색
	private String searchKey;    // 검색할 필드명
	private String searchWord;   // 검색할 단어
	
	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", onePageRecord=" + onePageRecord + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage + ", onePageCount=" + onePageCount + ", startPage=" + startPage
				+ ", restRecord=" + restRecord + ", searchKey=" + searchKey + ", searchWord=" + searchWord + "]";
	}
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		// 시작 페이지가 현재페이가 되도록 계산
		//(현재 페이지-1)/한 번에 표시할 페이지 수*한번에 표시할 페이지수+1
		startPage=((nowPage-1)/onePageCount*onePageCount)+1;
		
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 총페이지수 구하기
		totalPage=(int)Math.ceil((double)totalRecord/onePageRecord);
		// 마지막 페이지에 남아있는 레코드 수
		restRecord=totalRecord%onePageRecord;
		if(restRecord==0) {
			restRecord=onePageRecord;
		}
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getRestRecord() {
		return restRecord;
	}

	public void setRestRecord(int restRecord) {
		this.restRecord = restRecord;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	
}
