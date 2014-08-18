package com.ericssonlabs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wfi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListAdapter extends BaseAdapter{
	private LayoutInflater mInflater; 
	/**
	 * �����Ķ���
	 */
	private Context context = null;

	/**
	 * ���ݼ���
	 */
	private List<MyListItem> datas = null;
	
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	public MyListAdapter(Context context, List<MyListItem> datas){
		this.context = context;
		this.datas = datas;
		this.mInflater = LayoutInflater.from(context);
		
		//configCheckMap(false);
	}
	
	/**
	 * ����,Ĭ�������,������Ŀ����û��ѡ�е�.������г�ʼ��
	 */
	public void configCheckMap(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			isCheckMap.put(i, bool);
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//return datas.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		//ViewGroup layout = null;
		MyListItem bean = datas.get(position);
		/**
		 * ����ListView ���Ż�
		 */
		if (convertView == null) {
			holder=new ViewHolder();
			convertView = mInflater.inflate(R.layout.mylist_item_layout, null);
			/*
			 * ����ÿһ��item���ı�
			 */
			holder.gNumber = (TextView)convertView.findViewById(R.id.number);
			holder.gTitle = (TextView) convertView.findViewById(R.id.title);
			holder.gTotalnumber = (TextView)convertView.findViewById(R.id.totalnumber);
			holder.gCurnumber = (TextView)convertView.findViewById(R.id.curnumber);
			holder.gLoannumber = (TextView)convertView.findViewById(R.id.loannumber);
			holder.cbClick = (ImageView) convertView.findViewById(R.id.onclick);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.gNumber.setText(Integer.toString(bean.getNumber()));
		holder.gTitle.setText(bean.getTitle());
		holder.gTotalnumber.setText("总数："+Integer.toString(bean.getTotalNumber()));
		holder.gCurnumber.setText("在库："+Integer.toString(bean.getCurNumber()));
		holder.gLoannumber.setText("借出："+Integer.toString(bean.getLoanNumber()));
		/*
		 * ��ø�item �Ƿ�������
		 */
		final boolean canClick = bean.isCanClick();
		final String str = bean.getTitle();
		

		/*
		 * ��ð�ť
		 */
		holder.cbClick.setTag(position);
		holder.cbClick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(canClick == true){
					Toast.makeText(context, str+"click", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return convertView;
	}
	
	/**
	 * ����һ���ʱ��
	 */
	public void add(MyListItem bean) {
		this.datas.add(0, bean);

		// ��������Ŀ��Ϊ��ѡ��
		//configCheckMap(false);
	}
	
	public List<MyListItem> getDatas() {
		return datas;
	}
	
	public static class ViewHolder {

		public TextView gNumber = null;
		public TextView gTitle = null;
		public TextView gTotalnumber = null;
		public TextView gCurnumber = null;
		public TextView gLoannumber = null;
		public ImageView cbClick = null;
		
		public Object data = null;

	}

}
