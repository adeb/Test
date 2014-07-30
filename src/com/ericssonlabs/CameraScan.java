package com.ericssonlabs;

import com.zxing.activity.CaptureActivity;
import com.wfi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CameraScan extends Activity{
	private Button CameraScan;
	private TextView ScanResult;
	private Button Borrow;
	private Button Note;
	private Button My2DCode;
	Activity pActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camerascan);
		pActivity = this;
		
		CameraScan = (Button)this.findViewById(R.id.scan);
		ScanResult = (TextView)this.findViewById(R.id.scanresult);
		Borrow = (Button)this.findViewById(R.id.register);
		Note = (Button)this.findViewById(R.id.note);
		My2DCode = (Button)this.findViewById(R.id.mybarcode);

		CameraScan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openCameraIntent = new Intent(CameraScan.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
		
		My2DCode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(pActivity, BarCodeTestActivity.class);
				pActivity.startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			ScanResult.setText(scanResult);
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
