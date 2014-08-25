package com.ericssonlabs;

import java.util.List;

import com.ericssonlabs.MyListAdapter.ViewHolder;
import com.wfi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListItemInfoAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater; 
	/**
	 * �����Ķ���
	 */
	private Context context = null;

	/**
	 * ���ݼ���
	 */
	private List<MylistItemInfo> datas = null;
	
	public MyListItemInfoAdapter(Context context, List<MylistItemInfo> datas){
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
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		//ViewGroup layout = null;
		MylistItemInfo bean = datas.get(position);
		
		if (convertView == null) {
			holder=new ViewHolder();
			convertView = mInflater.inflate(R.layout.mylist_item_info_item, null);
			
			holder.gMylTitle = (TextView)convertView.findViewById(R.id.myl_title);
			holder.gMylWho = (TextView)convertView.findViewById(R.id.myl_who_bor);
			holder.gMylTel = (TextView) convertView.findViewById(R.id.myl_who_bor_tel);
			holder.gMylPar = (TextView)convertView.findViewById(R.id.myl_who_bor_par);
			holder.gMylMsg = (Button)convertView.findViewById(R.id.btn_myl_msg);
			holder.gMylPhe = (Button)convertView.findViewById(R.id.btn_myl_phone);
	
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.gMylTitle.setText(bean.getMylTitle());
		holder.gMylWho.setText("借用者："+bean.getMylWhoBor());
		holder.gMylTel.setText("电   话："+bean.getMylWhoBorTel());
		holder.gMylPar.setText("部   门："+bean.getMylWhoBorPar());
		
		return convertView;
	}
	
	public void add(MylistItemInfo bean) {
		this.datas.add(0, bean);
	}
	
	public List<MylistItemInfo> getDatas() {
		return datas;
	}
	
	public static class ViewHolder {

		public TextView gMylTitle = null;
		public TextView gMylWho = null;
		public TextView gMylTel = null;
		public TextView gMylPar = null;
		public Button gMylMsg = null;
		public Button gMylPhe = null;
		
		public Object data = null;

	}

}
