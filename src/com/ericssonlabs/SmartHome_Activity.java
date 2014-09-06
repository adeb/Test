package com.ericssonlabs;

import android.app.Activity;
import android.os.Bundle;

import com.ericssonlabs.WiperSwitch.OnChangedListener;
import com.wfi.R;

public class SmartHome_Activity extends Activity implements OnChangedListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
		
		//实例化WiperSwitch  
        WiperSwitch wiperSwitch = (WiperSwitch)findViewById(R.id.wiperSwitch1);  
          
        //设置初始状态为false  
        wiperSwitch.setChecked(false);  
          
        //设置监听  
        wiperSwitch.setOnChangedListener(this); 
	}

	@Override
	public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
		// TODO Auto-generated method stub
		
	}

}
