package com.ericssonlabs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ericssonlabs.MyListAdapter.ViewHolder;
import com.wfi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MylistAdapter_new0 extends BaseAdapter{
	private LayoutInflater mInflater; 
	/**
	 * �����Ķ���
	 */
	private Context context = null;

	/**
	 * ���ݼ���
	 */
	private List<MyListItem_new0> datas = null;
	
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	public MylistAdapter_new0(Context context, List<MyListItem_new0> datas){
		this.context = context;
		this.datas = datas;
		this.mInflater = LayoutInflater.from(context);
		
		//configCheckMap(false);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		//ViewGroup layout = null;
		MyListItem_new0 bean = datas.get(position);
		
		if (convertView == null) {
			holder=new ViewHolder();
			convertView = mInflater.inflate(R.layout.mylist_item_layout_new0, null);
			
			holder.gTitle = (TextView) convertView.findViewById(R.id.g_title);
			holder.gTotalnumber = (TextView)convertView.findViewById(R.id.g_total);
			holder.gLoannumber = (TextView)convertView.findViewById(R.id.g_loannum);
			holder.cbClick = (ImageView) convertView.findViewById(R.id.g_click);		
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.gTitle.setText("物品："+bean.getTitle());
		holder.gTotalnumber.setText("总数："+Integer.toString(bean.getTotalNumber()));
		holder.gLoannumber.setText("借出："+Integer.toString(bean.getLoanNumber()));
		
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
	
	public void add(MyListItem_new0 bean) {
		this.datas.add(0, bean);

		// ��������Ŀ��Ϊ��ѡ��
		//configCheckMap(false);
	}
	
	public List<MyListItem_new0> getDatas() {
		return datas;
	}
	
	public static class ViewHolder {

		public ImageView img = null;
		public TextView gTitle = null;
		public TextView gTotalnumber = null;
		public TextView gLoannumber = null;
		public ImageView cbClick = null;
		
		public Object data = null;

	}
}
