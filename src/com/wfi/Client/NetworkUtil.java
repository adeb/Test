package com.wfi.Client;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	/*����Ƿ�������*/
	public static boolean inNetworkAvailable(Context context){
		NetworkInfo info = getNetworkInfo(context);
		if(info != null)
		{
			return info.isAvailable();
		}
		return false;
	}
	
	/*����Ƿ���WIFI*/
	public static boolean isWifi(Context context){
		NetworkInfo info = getNetworkInfo(context);
		if(info != null){
			if(info.getType()==ConnectivityManager.TYPE_WIFI){
				return true;
			}
		}
		return false;
	}

	private static NetworkInfo getNetworkInfo(Context context) {
		// TODO Auto-generated method stub
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}
}
