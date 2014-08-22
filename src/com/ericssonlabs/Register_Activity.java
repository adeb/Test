package com.ericssonlabs;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.wfi.R;
import com.wfi.Client.Connect2Server;
import com.wfi.Client.ConnectServer;
import com.wfi.Client.ISocketResponse;
import com.wfi.Client.Packet;
import com.wfi.cmddata.Wficmd;

public class Register_Activity extends Activity{
	private final static String TAG = "Register";
	
	private EditText phoneNumber, passwd, passwd2,usrname;
	private Button register, reglogin;
	private ConnectServer RegisterUsr = null;
	private String ServerRegResp="";
	
	/*定时器用于计时关连接*/
	private Timer offTimer = null;
	private TimerTask timerTask = null;
	private static final int CLOSE_TIMEOUT = 30 * 1000;
	
	private TimerTask goToActivityOff(){
		timerTask = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//finish();
				Log.d(TAG, "Close");
				RegisterUsr.close();
			}
			
		};
		return timerTask;
	}
	
	private void StartTimer(){
		if(offTimer != null){
			offTimer.cancel();
			offTimer = null;
		}
		offTimer = new Timer();
		offTimer.schedule(goToActivityOff(), CLOSE_TIMEOUT);
	}
	
	private void ExitNotNow(){
		if(offTimer != null){
			offTimer.cancel();
			offTimer = null;
		}
		offTimer = new Timer();
		offTimer.schedule(goToActivityOff(), CLOSE_TIMEOUT);
	}

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
			ExitNotNow();
			switch(v.getId()){
			case R.id.newregister:
				Log.d("Client", "newregister");
				startRegister();
				break;
			case R.id.reglogin:
				RegisterUsr.close();
				break;
			default:
				break;
			}
		}
		
	};
	
	private void startRegister(){
		Log.d("Client","reg....");
		//RegisterUsr.open("172.28.146.1", 5129);
		Packet RegPack = new Packet();
		String str="";
		str=phoneNumber.getText().toString()+passwd.getText().toString()+usrname.getText().toString();
		RegPack.SetCmd((byte)0x1a);
		RegPack.pack(str.getBytes());
		/*
		RegPack.setHead((byte)0x55);
		RegPack.setCRC(234322);
		RegPack.setLength(str.length());
		RegPack.setCmd(Wficmd.WFI_CMD_REGISTER);
		RegPack.setDataPack(str.getBytes(), str.length());
		*/
		
		StartTimer();
		
		RegisterUsr.send(RegPack);
	
		//RegisterUsr.close();
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
		RegisterUsr.open("172.28.146.1", 5129);
	}

}
