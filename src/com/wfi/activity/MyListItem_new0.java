package com.wfi.activity;

import java.util.List;

import android.widget.ImageView;

public class MyListItem_new0 {
	private ImageView img;
	private String title;
	private int totalnumber;
	private int loannumber;
	private boolean canClick;
	
	/*
	public MyListItem_new0(ImageView img, 
			String title, 
			int totnum,
			int lannum,
			boolean canClick) {
		this.img = img;
		this.totalnumber = totnum;
		this.loannumber = lannum;
		this.title = title;
		this.canClick = canClick;
	}
	*/
	public MyListItem_new0(String title, 
			int totnum,
			int lannum,
			boolean canClick) {
		this.totalnumber = totnum;
		this.loannumber = lannum;
		this.title = title;
		this.canClick = canClick;
	}

	public MyListItem_new0() {
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
