package com.wfi.activity;

public class MylistItemInfo {
	private String Myl_title;
	private String Myl_who_bor;
	private String Myl_who_bor_tel;
	private String Myl_who_bor_par;
	
	
	public MylistItemInfo(String tit,
			String who,
			String tel,
			String par){
		this.Myl_title = tit;
		this.Myl_who_bor = who;
		this.Myl_who_bor_tel = tel;
		this.Myl_who_bor_par = par;
	}
	
	public MylistItemInfo(){
		
	}
	
	public void setMylWhoBor(String str){
		this.Myl_who_bor = str;
	}
	
	public String getMylWhoBor(){
		return this.Myl_who_bor;
	}
	
	public void setMylTitle(String tit){
		this.Myl_title = tit;
	}
	
	public String getMylTitle(){
		return this.Myl_title;
	}
	
	public void setMylWhoBorTel(String tel){
		this.Myl_who_bor_tel = tel;
	}
	
	public String getMylWhoBorTel(){
		return this.Myl_who_bor_tel;
	}
	
	public void setMylWhoBorPar(String par){
		this.Myl_who_bor_par = par;
	}
	
	public String getMylWhoBorPar(){
		return this.Myl_who_bor_par;
	}
}
