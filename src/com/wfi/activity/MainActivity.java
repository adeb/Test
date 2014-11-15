package com.wfi.activity;

import com.wfi.R;
import com.zxing.activity.CaptureActivity;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnClickListener {

	public static String TAG_HOME = "home";
	public static String TAG_TWO = "two";
	public static String TAG_MAIN = "main";
	public static String TAG_FOUR = "four";
	public static String TAG_FIVE ="five" ;
	public static TabHost mTabHost;
	//字体颜色变化 备用
	static final int COLOR1 = Color.parseColor("#A6D4AE");
	static final int COLOR2 = Color.parseColor("#FFFFFF");
	ImageView mBut1,mBut2,mBut3,mBut4,mBut5;
	TextView mText1,mText2,mText3,mText4,mText5;
	Intent mHome,mTwo,mMain,mFour,mFive;
	
	int mCurTabId = R.id.tt_tab_guide_bar;//当前选中ID
	
	//Animation
	private Animation left_in,left_out;
	private Animation right_in,right_out;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_tab);
		prepareAnim();
		prepareIntent();
		setupIntent();
		prepareView();
		
		View v = new View(this);
		v.setId(R.id.channel3);
		onClick(v);
	}
	
	private void prepareAnim() {
		left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
		left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);
		right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
		right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
	}
	
	private void prepareIntent() {
		mHome = new Intent(this, CameraScan_Activity.class);
		mTwo = new Intent(this, Search_Activity.class);
		mMain = new Intent(this, Mylist_Activity.class);
		mFour = new Intent(this, Message_Activity.class);
		mFive = new Intent(this, Setting_Activity.class);
	}
	
	private void setupIntent() {
		mTabHost = getTabHost();
		mTabHost.addTab(buildTabSpec(TAG_HOME, R.string.tt_tab_bar_scan, R.drawable.tt_tab_bar_scan_grey, mHome));
		mTabHost.addTab(buildTabSpec(TAG_TWO, R.string.tt_tab_bar_search, R.drawable.tt_tab_bar_search_grey, mTwo));
		mTabHost.addTab(buildTabSpec(TAG_MAIN, R.string.tt_tab_bar_mylist, R.drawable.tt_tab_bar_mylist_grey, mMain));
		mTabHost.addTab(buildTabSpec(TAG_FOUR, R.string.tt_tab_bar_message, R.drawable.tt_tab_bar_message_grey, mFour));
		mTabHost.addTab(buildTabSpec(TAG_FIVE, R.string.tt_tab_bar_setting, R.drawable.tt_tab_bar_owner_grey, mFive));
	}
	
	private TabHost.TabSpec buildTabSpec(String tag,int resLabel,int resIcon,final Intent intent) {
		return mTabHost.newTabSpec(tag).
				setIndicator(getString(resLabel),getResources().getDrawable(resIcon)).
				setContent(intent);
	}
	
	private void prepareView() {
		mBut1 = (ImageView) findViewById(R.id.tt_tab_recommend_bar);
		mBut2 = (ImageView) findViewById(R.id.tt_tab_best_bar);
		mBut3 = (ImageView) findViewById(R.id.tt_tab_guide_bar);
		mBut4 = (ImageView) findViewById(R.id.tt_tab_community_bar);
		mBut5 = (ImageView) findViewById(R.id.tt_tab_more_bar);
		
		mText1 = (TextView)findViewById(R.id.text1);
		mText2 = (TextView)findViewById(R.id.text2);
		mText3 = (TextView)findViewById(R.id.text3);
		mText4 = (TextView)findViewById(R.id.text4);
		mText5 = (TextView)findViewById(R.id.text5);
		
		findViewById(R.id.channel1).setOnClickListener(this);
		findViewById(R.id.channel2).setOnClickListener(this);
		findViewById(R.id.channel3).setOnClickListener(this);
		findViewById(R.id.channel4).setOnClickListener(this);
		findViewById(R.id.channel5).setOnClickListener(this);
		
		//获取文本暂时不需要,需要更改文本颜色时定义ID获取
	}
	
	public static void setCurrTabByTag(String tab) {
		mTabHost.setCurrentTabByTag(tab);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (mCurTabId == v.getId()) {
			return;
		}
		
		mBut1.setImageResource(R.drawable.tt_tab_bar_scan_grey);
		mBut2.setImageResource(R.drawable.tt_tab_bar_search_grey);
		mBut3.setImageResource(R.drawable.tt_tab_bar_mylist_grey);
		mBut4.setImageResource(R.drawable.tt_tab_bar_message_grey);
		mBut5.setImageResource(R.drawable.tt_tab_bar_owner_grey);
		
		RelativeLayout c1 = (RelativeLayout) findViewById(R.id.channel1);
		RelativeLayout c2 = (RelativeLayout) findViewById(R.id.channel2);
		RelativeLayout c3 = (RelativeLayout) findViewById(R.id.channel3);
		RelativeLayout c4 = (RelativeLayout) findViewById(R.id.channel4);
		RelativeLayout c5 = (RelativeLayout) findViewById(R.id.channel5);
		
		//mText1.setTextColor(COLOR1);
		//mText2.setTextColor(COLOR1);
		//mText3.setTextColor(COLOR1);
		//mText4.setTextColor(COLOR1);
		//mText5.setTextColor(COLOR1);

		int checkedId = v.getId();
		final boolean o;
		if (mCurTabId < checkedId)
			o = true;
		else
			o = false;
		if (o)
			mTabHost.getCurrentView().startAnimation(left_out);
		else
			mTabHost.getCurrentView().startAnimation(right_out);
		switch (checkedId) {
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAG_HOME);
			mBut1.setImageResource(R.drawable.tt_tab_bar_scan_bright);
			setTextColor(1);
			//c1.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			c2.setBackgroundResource(0);
			//c3.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			c4.setBackgroundResource(0);
			c5.setBackgroundResource(0);
			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAG_TWO);
			mBut2.setImageResource(R.drawable.tt_tab_bar_search_bright);
			setTextColor(2);
			c1.setBackgroundResource(0);
			//c2.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			//c3.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			c4.setBackgroundResource(0);
			c5.setBackgroundResource(0);
			break;
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAG_MAIN);
			mBut3.setImageResource(R.drawable.tt_tab_bar_mylist_bright);
			setTextColor(3);
			c1.setBackgroundResource(0);
			c2.setBackgroundResource(0);
			//c3.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			c4.setBackgroundResource(0);
			c5.setBackgroundResource(0);
			break;
		case R.id.channel4:
			mTabHost.setCurrentTabByTag(TAG_FOUR);
			mBut4.setImageResource(R.drawable.tt_tab_bar_message_bright);
			setTextColor(4);
			c1.setBackgroundResource(0);
			c2.setBackgroundResource(0);
			//c3.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			//c4.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			c5.setBackgroundResource(0);
			break;
		case R.id.channel5:
			mTabHost.setCurrentTabByTag(TAG_FIVE);
			mBut5.setImageResource(R.drawable.tt_tab_bar_owner_bright);
			setTextColor(5);
			c1.setBackgroundResource(0);
			c2.setBackgroundResource(0);
			//c3.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			c4.setBackgroundResource(0);
			//c5.setBackgroundResource(R.drawable.tt_tab_bar_selected_bg_s);
			break;
		default:
			break;
		}
		if (o)
			mTabHost.getCurrentView().startAnimation(left_in);
		else
			mTabHost.getCurrentView().startAnimation(right_in);
		mCurTabId = checkedId;
	}
	
	public void setTextColor(int i){
		switch(i){
			case 1:
				mText1.setTextColor(COLOR2);
				mText2.setTextColor(COLOR1);
				mText3.setTextColor(COLOR1);
				mText4.setTextColor(COLOR1);
				mText5.setTextColor(COLOR1);
				break;
			case 2:
				mText1.setTextColor(COLOR1);
				mText2.setTextColor(COLOR2);
				mText3.setTextColor(COLOR1);
				mText4.setTextColor(COLOR1);
				mText5.setTextColor(COLOR1);
				break;
			case 3:
				mText1.setTextColor(COLOR1);
				mText2.setTextColor(COLOR1);
				mText3.setTextColor(COLOR2);
				mText4.setTextColor(COLOR1);
				mText5.setTextColor(COLOR1);
				break;
			case 4:
				mText1.setTextColor(COLOR1);
				mText2.setTextColor(COLOR1);
				mText3.setTextColor(COLOR1);
				mText4.setTextColor(COLOR2);
				mText5.setTextColor(COLOR1);
				break;
			case 5:
				mText1.setTextColor(COLOR1);
				mText2.setTextColor(COLOR1);
				mText3.setTextColor(COLOR1);
				mText4.setTextColor(COLOR1);
				mText5.setTextColor(COLOR2);
				break;
		}
	}

}
