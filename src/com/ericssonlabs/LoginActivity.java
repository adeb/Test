package com.ericssonlabs;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wfi.R;
import com.wfi.Client.ConnectServer;
import com.wfi.Client.ISocketResponse;
import com.wfi.Client.Packet;

public class LoginActivity extends Activity {
	private final String TAG = "Login";
	private EditText Id;
	private EditText Passwd;
	private Button Login;
	private TextView newUsr;
	private ConnectServer Loginusr = null;
	Activity pActivity = null;
	
	private String ServerResp="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		pActivity = this;
		
		Id = (EditText)this.findViewById(R.id.id);
		Passwd = (EditText)this.findViewById(R.id.passwd);
		Login = (Button)this.findViewById(R.id.login);
		newUsr = (TextView)this.findViewById(R.id.newusr);
		
		Loginusr = new ConnectServer(this.getApplicationContext(), socketListener);
		
		Login.setOnClickListener(listener);
		newUsr.setOnClickListener(listener);
	}
	
	private ISocketResponse socketListener = new ISocketResponse(){

		@Override
		public void onSocketResponse(String txt) {
			// TODO Auto-generated method stub
			ServerResp = txt;
			Log.d(TAG, "ServerRsp:"+ServerResp);
			}
	};
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.login:
				if(Id.getText().toString() !="" && Passwd.getText().toString() != ""){
					Log.d(TAG, "Now start login");
					StartLogin();
					/*
					Intent intent = new Intent();
					intent.setClass(pActivity, CameraScan.class);
					pActivity.startActivity(intent);
					finish();
					*/
				}else if(Id.getText().toString()==""){
					Toast.makeText(getApplicationContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
					Id.setFocusable(true);
					Id.requestFocus();
				}else if(Passwd.getText().toString() == ""){
					Toast.makeText(getApplicationContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
					Passwd.setFocusable(true);
					Passwd.requestFocus();
				}
				break;
			case R.id.newusr:
				Intent intent = new Intent();
				intent.setClass(pActivity, RegisterActivity.class);
				pActivity.startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
	
	private void StartLogin() {
		// TODO Auto-generated method stub
		String loginstr="login";
		byte[] logindata = new byte[50];
		Packet packet = new Packet();
		Loginusr.open("10.118.54.39", 5124);
		//logindata = loginstr.getBytes("utf-8");
		//logindata[15] = "qqq".getBytes("utf-8");
		packet.pack(loginstr);
		//packet.pack(Id.getText().toString(), 15);
		//packet.pack(Passwd.getText().toString(), 29);
		Loginusr.send(packet);
		//packet.cleanpack();
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Loginusr.close();
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		return super.onKeyDown(keyCode, event);
	}

}
