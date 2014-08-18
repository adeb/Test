package com.ericssonlabs;

public class MyBorListItem {
	private int bor_number;
	private String bor_title;
	private String owner;
	private String owner_department;
	private String owner_phone;
	private boolean canClick;
	
	public MyBorListItem(int num, 
			String title,
			String owner,
			String owner_department,
			String owner_phone,
			boolean canClick) {
		this.bor_number = num;
		this.bor_title = title;
		this.owner = owner;
		this.owner_department = owner_department;
		this.owner_phone = owner_phone;
		this.canClick = canClick;
	}

	public MyBorListItem() {
	}
	
	public void setNumber(int num){
		this.bor_number = num;
	}
	
	public int getNumber(){
		return bor_number;
	}
	
	public void setTitle(String str){
		this.bor_title = str;
	}
	
	public String getTitle(){
		return bor_title;
	}
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void setOwnerDepartment(String department){
		this.owner_department = department;
	}
	
	public String getOwnerDepartment(){
		return owner_department;
	}
	
	public void setOwnerPhone(String phone){
		this.owner_phone = phone;
	}
	
	public String getOwnerPhone(){
		return owner_phone;
	}
	
	public boolean isCanClink() {
		return canClick;
	}

	public void setCanClink(boolean canClick) {
		this.canClick = canClick;
	}
}
