package com.wfi.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wfi.R;
import com.wfi.activity.MyBorListItem;
import com.wfi.activity.MyListItem;
import com.wfi.activity.MylistAdapter_new0;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Mylist_Activity extends Activity implements OnClickListener, OnItemClickListener {

	/**
	 * ���ذ�ť
	 */
	private ViewGroup btnMylist = null;
	
	/*
	 * �����б�*/
	private ViewGroup btnBorList = null;

	/**
	 * ȷ����ť
	 */
	private ViewGroup btnAdd = null;

	/**
	 * ѡ������
	 */
	private Button btnSelectAll = null;

	/**
	 * �������
	 */
	private Button btnDelete = null;

	/**
	 * ListView�б�
	 */
	private ListView lvListView = null;
	
	private boolean clickflag = false;

	/**
	 * �������
	 */
	private MyListAdapter adpAdapter = null;
	private MylistAdapter_new0 adpAdapter_new0 = null;
	private MyBorListAdapter boradpAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylist);

		// ��ʼ����ͼ
		initView();

		// ��ʼ���ؼ�
		//initData();
		initData_new0();

	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {

		btnMylist = (ViewGroup) findViewById(R.id.mylist);
		btnMylist.setOnClickListener(this);
		
		btnBorList = (ViewGroup) findViewById(R.id.borlist);
		btnBorList.setOnClickListener(this);

		btnAdd = (ViewGroup) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		lvListView = (ListView) findViewById(R.id.lvListView);
		lvListView.setOnItemClickListener(this);

	}

	/**
	 * ��ʼ����ͼ
	 */
	private void initData() {

		// ģ�������
		
		List<MyListItem> demoDatas = new ArrayList<MyListItem>();

		demoDatas.add(new MyListItem(1, "张三", 5, 3, 2, true));
		demoDatas.add(new MyListItem(2, "李四", 5, 3, 2, true));
		demoDatas.add(new MyListItem(3, "王五", 5, 3, 2, false));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));
		demoDatas.add(new MyListItem(4, "赵六", 5, 3, 2, true));

		adpAdapter = new MyListAdapter(this, demoDatas);

		lvListView.setAdapter(adpAdapter);
		
		clickflag = true;
	}
	
	private void initData_new0() {

		// ģ�������
		
		List<MyListItem_new0> demoDatas = new ArrayList<MyListItem_new0>();

		demoDatas.add(new MyListItem_new0("张三", 5, 3, true));
		demoDatas.add(new MyListItem_new0("李四", 5, 3, true));
		demoDatas.add(new MyListItem_new0("王五", 5, 3, false));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));
		demoDatas.add(new MyListItem_new0("赵六", 5, 3, true));

		adpAdapter_new0 = new MylistAdapter_new0(this, demoDatas);

		lvListView.setAdapter(adpAdapter_new0);
		
		clickflag = true;
	}

	/**
	 * ��ť����¼�
	 */
	@Override
	public void onClick(View v) {

		/*
		 * ��������ص�ʱ��
		 */
		if (v == btnMylist) {
			//initData();
			initData_new0();
		}

		
		if(v == btnBorList){
			// ģ�������
			List<MyBorListItem> demoDatas = new ArrayList<MyBorListItem>();

			demoDatas.add(new MyBorListItem(1, "摇控器", "张三", "软件", "123456789", true));
			demoDatas.add(new MyBorListItem(2, "U盘","李四", "软件", "123456789", true));
			demoDatas.add(new MyBorListItem(3, "电源","王五", "软件", "123456789", false));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));
			demoDatas.add(new MyBorListItem(4, "排插","赵六", "软件", "012345678", true));


			boradpAdapter = new MyBorListAdapter(getApplicationContext(), demoDatas);

			lvListView.setAdapter(boradpAdapter);
			
			clickflag = false;
			//lvListView.setCacheColorHint(Color.parseColor("#00000000"));
			//lvListView.setSelector(Color.parseColor("#00000000"));
		}

		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		//Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
		if(clickflag == true){
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MylistInfo_Activity.class);
			startActivity(intent);
		}
	}

	/**
	 * ��ListView ��������ʱ��
	 */
	/*
	@Override
	public void onItemClick(AdapterView<?> listView, View itemLayout,
			int position, long id) {

		if (itemLayout.getTag() instanceof ViewHolder) {

			ViewHolder holder = (ViewHolder) itemLayout.getTag();

			// ���Զ�����CheckBox��checked�¼�
			holder.cbCheck.toggle();

		}

	}*/
}
