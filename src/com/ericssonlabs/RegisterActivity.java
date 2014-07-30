package com.ericssonlabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.wfi.R;
import com.wfi.Client.ConnectServer;
import com.wfi.Client.ISocketResponse;
import com.wfi.Client.Packet;
import com.wfi.cmddata.Wficmd;

public class RegisterActivity extends Activity{
	private final static String TAG = "Register";
	
	private EditText phoneNumber, passwd, passwd2,usrname;
	private Button register, reglogin;
	private ConnectServer RegisterUsr = null;
	private String ServerRegResp="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		
		setView();
		
		RegisterUsr = new ConnectServer(this.getApplicationContext(), regsocketListener);
	}
	
	private ISocketResponse regsocketListener = new ISocketResponse(){

		@Override
		public void onSocketResponse(String txt) {
			// TODO Auto-generated method stub
			ServerRegResp=txt;
			Log.d(TAG, "RegRec:"+ServerRegResp);
		}};
	
	private void setView()
	{
		phoneNumber = (EditText)this.findViewById(R.id.phonenumber);
		passwd = (EditText)this.findViewById(R.id.regpasswd);
		passwd2 = (EditText)this.findViewById(R.id.regpasswd2);
		usrname = (EditText)this.findViewById(R.id.regname);
		register = (Button)this.findViewById(R.id.newregister);
		reglogin = (Button)this.findViewById(R.id.reglogin);
		
		register.setOnClickListener(listener);
		reglogin.setOnClickListener(listener);
	}
	
	private OnClickListener listener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.newregister:
				Log.d("Client", "newregister");
				startRegister();
				break;
			case R.id.reglogin:
				break;
			default:
				break;
			}
		}
		
	};
	
	private void startRegister(){
		Log.d("Client","reg....");
		RegisterUsr.open("10.118.54.39", 5124);
		Packet RegPack = new Packet();
		String str="";
		str=phoneNumber.getText().toString()+passwd.getText().toString()+usrname.getText().toString();
		RegPack.pack(str);
		/*
		RegPack.setHead((byte)0x55);
		RegPack.setCRC(234322);
		RegPack.setLength(str.length());
		RegPack.setCmd(Wficmd.WFI_CMD_REGISTER);
		RegPack.setDataPack(str.getBytes(), str.length());
		*/
		RegisterUsr.send(RegPack);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
