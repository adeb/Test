package com.wfi.activity;

import android.graphics.Bitmap;

public class MyBitmap {
	
	private static Bitmap myBitmap;
	private static byte[] name;
	private static byte[] telephone;
	private static byte[] where;
	
	public static void setMyBitmap(Bitmap btm){
		myBitmap = btm;
	}
	
	public static Bitmap getMyBitmap(){
		return myBitmap;
	}
}
