package com.wfi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wfi.R;
import com.zxing.activity.CaptureActivity;

public class CameraScan_Activity extends Activity implements OnClickListener{
	private TextView QRresult;
	private ImageView Borrow_img;
	private ImageView QRscan_img;
	private ImageView GetPic_img;
	private ImageView Note_img;
	private ImageView Add_img;
	private ImageView More_img;
	
	private Animation Anim_Alpha;
	
	TextView mText1,mText2,mText3,mText4,mText5, mText6;
	Activity pActivity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.qrscan);
		pActivity = this;
		
		prepareView();
	}
	
	private void prepareView() {
		QRresult = (TextView) findViewById(R.id.qrscanresult);
		Borrow_img = (ImageView) findViewById(R.id.borrow_bar_icon);
		QRscan_img = (ImageView) findViewById(R.id.qrscan_bar_icon);
		GetPic_img = (ImageView) findViewById(R.id.getpic_bar_icon);
		Note_img = (ImageView) findViewById(R.id.note_bar_icon);
		Add_img = (ImageView) findViewById(R.id.add_bar_icon);
		More_img = (ImageView) findViewById(R.id.more_bar_icon);
		
		mText1 = (TextView)findViewById(R.id.icon_text1);
		mText2 = (TextView)findViewById(R.id.icon_text2);
		mText3 = (TextView)findViewById(R.id.icon_text3);
		mText4 = (TextView)findViewById(R.id.icon_text4);
		mText5 = (TextView)findViewById(R.id.icon_text5);
		mText6 = (TextView)findViewById(R.id.icon_text6);
		
		findViewById(R.id.icon_ch1).setOnClickListener(this);
		findViewById(R.id.icon_ch2).setOnClickListener(this);
		findViewById(R.id.icon_ch3).setOnClickListener(this);
		findViewById(R.id.icon_ch4).setOnClickListener(this);
		findViewById(R.id.icon_ch5).setOnClickListener(this);
		findViewById(R.id.icon_ch6).setOnClickListener(this);
		//获取文本暂时不需要,需要更改文本颜色时定义ID获取
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int checkedId = v.getId();
		
		switch (checkedId) {
		case R.id.icon_ch1:
			//Borrow_img.setImageResource(R.drawable.borrow_icon_b);
			Anim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_action);  
	        v.startAnimation(Anim_Alpha); 
			Toast.makeText(getApplicationContext(), "功能正在开发中", Toast.LENGTH_SHORT).show();
			break;
		case R.id.icon_ch2:
			//QRscan_img.setImageResource(R.drawable.qrscan_icon_b);
			Anim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_action);  
	        v.startAnimation(Anim_Alpha); 
			Intent openCameraIntent = new Intent(CameraScan_Activity.this,CaptureActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;
		case R.id.icon_ch3:
			
			Anim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_action);  
	        v.startAnimation(Anim_Alpha); 
			Toast.makeText(getApplicationContext(), "功能正在开发中", Toast.LENGTH_SHORT).show();
			break;
		case R.id.icon_ch4:
	
			Anim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_action);  
	        v.startAnimation(Anim_Alpha); 
			Toast.makeText(getApplicationContext(), "功能正在开发中", Toast.LENGTH_SHORT).show();
			break;
		case R.id.icon_ch5:
			Anim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_action);  
	        v.startAnimation(Anim_Alpha); 
			Toast.makeText(getApplicationContext(), "功能正在开发中", Toast.LENGTH_SHORT).show();
			break;
		case R.id.icon_ch6:
			Anim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_action);  
	        v.startAnimation(Anim_Alpha); 
			Toast.makeText(getApplicationContext(), "功能正在开发中", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			QRresult.setText(scanResult);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
