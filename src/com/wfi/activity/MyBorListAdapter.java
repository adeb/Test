package com.wfi.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wfi.R;
import com.wfi.activity.MyListAdapter.ViewHolder;

public class MyBorListAdapter extends BaseAdapter{
	/**
	 * �����Ķ���
	 */
	private Context context = null;
	private LayoutInflater mInflater;
	
	private Animation Anim_Alpha;

	/**
	 * ���ݼ���
	 */
	private List<MyBorListItem> datas = null;

	/**
	 * CheckBox �Ƿ�ѡ��Ĵ洢����,key �� position , value �Ǹ�position�Ƿ�ѡ��
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

	public MyBorListAdapter(Context context, List<MyBorListItem> datas) {
		this.datas = datas;
		this.context = context;
		this.mInflater = LayoutInflater.from(context); 

		// ��ʼ��,Ĭ�϶�û��ѡ��
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
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			
			convertView = mInflater.inflate(R.layout.myborlist_item_layout, null);
			holder.bor_number = (TextView) convertView.findViewById(R.id.bor_number);
			holder.bor_title = (TextView) convertView.findViewById(R.id.bor_title);
			holder.owner = (TextView) convertView.findViewById(R.id.owner);
			holder.owner_department = (TextView) convertView.findViewById(R.id.owner_department);
			holder.owner_phone = (TextView)convertView.findViewById(R.id.owner_phone);
			holder.bor_click_img = (ImageView) convertView.findViewById(R.id.bor_click_img);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		final MyBorListItem bean = datas.get(position);
		
		final boolean canClick = bean.isCanClink();
		
		holder.bor_number.setText(Integer.toString(bean.getNumber()));
		holder.bor_title.setText(bean.getTitle());
		holder.owner.setText("所有者："+bean.getOwner());
		holder.owner_department.setText("部门："+bean.getOwnerDepartment());
		holder.owner_phone.setText("电话："+bean.getOwnerPhone());
		
		holder.bor_click_img.setTag(position);
		holder.bor_click_img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(canClick == true){
					Anim_Alpha = AnimationUtils.loadAnimation(context, R.anim.alpha_action); 
			        v.startAnimation(Anim_Alpha); 
					Toast.makeText(context, bean.getTitle()+" click", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		return convertView;
	}
	
	public final class ViewHolder {

		public TextView bor_number;
		public TextView bor_title;
		public TextView owner;
		public TextView owner_department;
		public TextView owner_phone;
		public boolean canClick;
		
		public ImageView bor_click_img = null;
		public Object data = null;

	}

	public List<MyBorListItem> getDatas() {
		return datas;
	}
}
