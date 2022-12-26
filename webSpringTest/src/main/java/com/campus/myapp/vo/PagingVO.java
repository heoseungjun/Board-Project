package com.campus.myapp.vo;

public class PagingVO {
	//����¡
	private int nowPage=1;    // ����������
	private int onePageRecord=5;    // �� �������� ǥ���� ���ڵ� ��
	private int totalRecord;    // �� ���ڵ� ��:DB ��ȸ ����� ���� �ٸ�
	private int totalPage;    // �� ������ ��

	//������ ��ȣ �ű� ��
	private int onePageCount=5;    // �� ���� ǥ���� ������ ��
	private int startPage=1;       //���� ������
	private int restRecord=onePageRecord;   // ������ �������� ������ ���ڵ� ��
	
	//�˻�
	private String searchKey;    // �˻��� �ʵ��
	private String searchWord;   // �˻��� �ܾ�
	
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
		// ���� �������� �������̰� �ǵ��� ���
		//(���� ������-1)/�� ���� ǥ���� ������ ��*�ѹ��� ǥ���� ��������+1
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
		// ���������� ���ϱ�
		totalPage=(int)Math.ceil((double)totalRecord/onePageRecord);
		// ������ �������� �����ִ� ���ڵ� ��
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
