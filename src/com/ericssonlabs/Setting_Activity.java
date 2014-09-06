package com.ericssonlabs;

import java.util.ArrayList;
import java.util.HashMap;

import com.wfi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Setting_Activity extends Activity implements OnClickListener{
	
	//private Button btn_Normal_Setting;
	//private Button btn_MyQRCode;
	
	private CornerListView mListView = null;
	ArrayList<HashMap<String, String>> map_list1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout_n);
		
		getDataSource1();
		// getDataSource2();

		SimpleAdapter adapter1 = new SimpleAdapter(this, map_list1,
				R.layout.simple_list_item_1, new String[] { "item" },
				new int[] { R.id.item_title });
		mListView = (CornerListView) findViewById(R.id.list1);
		mListView.setAdapter(adapter1);
		mListView.setOnItemClickListener(new OnItemListSelectedListener());
		
		//initView();
	}
	
	public ArrayList<HashMap<String, String>> getDataSource1() {

		map_list1 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		HashMap<String, String> map4 = new HashMap<String, String>();

		map1.put("item", "通用设置");
		map2.put("item", "智能手环");
		map3.put("item", "智能家居");
		map4.put("item", "我的二维码");

		map_list1.add(map1);
		map_list1.add(map2);
		map_list1.add(map3);
		map_list1.add(map4);

		return map_list1;
	}
	
	class OnItemListSelectedListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch(arg2){
			case 0:
				Toast.makeText(getApplicationContext(), "正在开发中！", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "正在开发中！", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "正在开发中！", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), CreateBarCode_Activity.class);
				startActivity(intent);
				break;
			}
			/*
			if (arg2 == 0) {
				System.out.println("0");
				Toast.makeText(getApplicationContext(), "正在开发中！", Toast.LENGTH_SHORT).show();
			}else{
				System.out.println("1");
			}
			*/
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
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
			intent.setClass(getApplicationContext(), CreateBarCode_Activity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	*/
	
}
