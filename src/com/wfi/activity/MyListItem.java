package com.wfi.activity;

public class MyListItem {
	private int number;
	private String title;
	private int totalnumber;
	private int curnumber;
	private int loannumber;
	private boolean canClick;
	
	public MyListItem(int num, 
			String title, 
			int totnum,
			int curnum,
			int lannum,
			boolean canClick) {
		this.number = num;
		this.totalnumber = totnum;
		this.curnumber = curnum;
		this.loannumber = lannum;
		this.title = title;
		this.canClick = canClick;
	}

	public MyListItem() {
	}
	
	public void setNumber(int num){
		this.number = num;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setTitle(String str){
		this.title = str;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTotalNumber(int totnum){
		this.totalnumber = totnum;
	}
	
	public int getTotalNumber(){
		return totalnumber;
	}
	
	public void setCurNumber(int curnum){
		this.curnumber = curnum;
	}
	
	public int getCurNumber(){
		return curnumber;
	}
	
	public void setLoanNumber(int lannum){
		this.loannumber = lannum;
	}
	
	public int getLoanNumber(){
		return loannumber;
	}
	
	public boolean isCanClick() {
		return canClick;
	}

	public void setCanClick(boolean canClick) {
		this.canClick = canClick;
	}

}
