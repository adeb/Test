package com.ericssonlabs;

import com.wfi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Setting_Activity extends Activity implements OnClickListener{
	
	private Button btn_Normal_Setting;
	private Button btn_MyQRCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
		
		initView();
	}
	
	private void initView(){
		btn_Normal_Setting = (Button)findViewById(R.id.btn_normal_setting);
		btn_MyQRCode = (Button)findViewById(R.id.btn_myqrcode);
		
		btn_Normal_Setting.setOnClickListener(this);
		btn_MyQRCode.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int checkedId = v.getId();
		
		switch(checkedId){
		case R.id.btn_normal_setting:
			break;
		case R.id.btn_myqrcode:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), BarCodeTestActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
}
