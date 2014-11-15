package com.wfi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.wfi.R;

public class MylistInfo_Activity extends Activity implements OnClickListener{
	
	private ListView myListView = null;
	private MyListItemInfoAdapter myadpAdapter = null;
	private TextView BacToMylist = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mylist_item_info_layout);
		
		initView();
		
		initData();
	}
	
	private void initView(){
		myListView = (ListView)findViewById(R.id.mylistinfo);
		BacToMylist = (TextView)findViewById(R.id.back_to_mylist);
		BacToMylist.setOnClickListener(this);
	}
	
	private void initData(){
		List<MylistItemInfo> demoDatas = new ArrayList<MylistItemInfo>();

		demoDatas.add(new MylistItemInfo("摇控器", "张三", "12345678901", "软件"));
		demoDatas.add(new MylistItemInfo("U盘", "李四", "12345678987", "硬件"));
		demoDatas.add(new MylistItemInfo("硬盘", "王五", "12345654345", "采购"));
		demoDatas.add(new MylistItemInfo("高清碟", "赵六", "82748586938", "SQA"));
		demoDatas.add(new MylistItemInfo("SONY DVD", "赵六", "84758683758", "SQA"));
		demoDatas.add(new MylistItemInfo("SONY DVD", "赵六", "84758683758", "SQA"));
		demoDatas.add(new MylistItemInfo("SONY DVD", "赵六", "84758683758", "SQA"));
		demoDatas.add(new MylistItemInfo("SONY DVD", "赵六", "84758683758", "SQA"));
		demoDatas.add(new MylistItemInfo("SONY DVD", "赵六", "84758683758", "SQA"));
		demoDatas.add(new MylistItemInfo("SONY DVD", "赵六", "84758683758", "SQA"));

		myadpAdapter = new MyListItemInfoAdapter(this, demoDatas);

		myListView.setAdapter(myadpAdapter);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == BacToMylist){
			finish();
		}
	}

}
