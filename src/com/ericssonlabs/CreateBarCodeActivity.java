package com.ericssonlabs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.wfi.R.id;
import com.google.zxing.WriterException;
import com.wfi.R;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateBarCodeActivity extends Activity {
    /** Called when the activity is first created. */
	private final static String TAG = "WFi";
	private TextView resultTextView, SelectCP, backTosetting;
	private EditText nameEditText, telEditText, addrEditText, goodsEditText, NumbersEditText;
	private ImageView qrImgImageView;
	private Button SelectC, SelectP;
	private static String selectcp = "部门："; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_myqr_layout);
        //resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        //qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        
        backTosetting = (TextView)this.findViewById(R.id.back_to_setting);
        
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        nameEditText = (EditText)this.findViewById(R.id.usrname);
        telEditText = (EditText)this.findViewById(R.id.telephone);
        addrEditText = (EditText)this.findViewById(R.id.selectedit);
        goodsEditText = (EditText)this.findViewById(R.id.goods);
        NumbersEditText = (EditText)this.findViewById(R.id.numbers);
        SelectCP = (TextView)this.findViewById(R.id.selectCP);
        SelectC = (Button)this.findViewById(R.id.companet);
        SelectP = (Button)this.findViewById(R.id.personal);
        
        backTosetting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				backTosetting.setTextColor(Color.parseColor("#2ecc71"));
				finish();
			}
		});
        
        SelectC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SelectCP.setText("部门：");
				selectcp="部门：";
			}
		});
        
        SelectP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SelectCP.setText("地址：");
				selectcp="地址：";
			}
		});
        
        //Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        /*
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//
				Intent openCameraIntent = new Intent(BarCodeTestActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});*/
        
        final Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String contentString = "姓名："+nameEditText.getText().toString()+";"+"电话："+telEditText.getText().toString()+";"+selectcp+addrEditText.getText().toString()
							+";"+"物品:"+goodsEditText.getText().toString()+";"+"数量："+NumbersEditText.getText().toString();
					if (nameEditText.getText().toString().equals("")
							||telEditText.getText().toString().equals("")
							||addrEditText.getText().toString().equals("")
							||goodsEditText.getText().toString().equals("")
							||NumbersEditText.getText().toString().equals("")) {
						
						Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
						//qrImgImageView.setImageBitmap(qrCodeBitmap);
						MyBitmap.setMyBitmap(qrCodeBitmap);
						saveMyBitmap("MyBarCode", qrCodeBitmap);
						byte[] bitmaparray =  getBitmapByte(qrCodeBitmap);
						generateQRCodeButton.setText("Bitmap size = "+bitmaparray.length);
						
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), ShowQR.class);
						startActivity(intent);
						finish();
						//Toast.makeText(getApplicationContext(), "Bitmap size = "+bitmaparray.length, Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(CreateBarCodeActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
					}
					
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/*
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
		}
		*/
	}
	
	public void saveMyBitmap(String bitName,Bitmap mBitmap){
	  File f = new File("/sdcard/" + bitName + ".png");
	  try {
		  f.createNewFile();
	  } catch (IOException e) {
		  // TODO Auto-generated catch block
		  Log.d(TAG,"Save pictures Error："+e.toString());
		  Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
	  }
	  FileOutputStream fOut = null;
	  try {
		  fOut = new FileOutputStream(f);
	  } catch (FileNotFoundException e) {
		  Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
		  e.printStackTrace();
	  }
	  mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
	  try {
		  fOut.flush();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  try {
		  fOut.close();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	}
	
	public byte[] getBitmapByte(Bitmap bitmap){   
	    ByteArrayOutputStream out = new ByteArrayOutputStream();   
	    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);   
	    try {   
	        out.flush();   
	        out.close();   
	    } catch (IOException e) {   
	        e.printStackTrace();   
	    }   
	    return out.toByteArray();   
	}   
	
	public Bitmap getBitmapFromByte(byte[] temp){   
	    if(temp != null){   
	        Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);   
	        return bitmap;   
	    }else{   
	        return null;   
	    }   
	} 
	
	public static Bitmap drawableToBitmap(Drawable drawable){  
        int width = drawable.getIntrinsicWidth();  
        int height = drawable.getIntrinsicHeight();  
        Bitmap bitmap = Bitmap.createBitmap(width, height,  
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
                        : Bitmap.Config.RGB_565);  
        Canvas canvas = new Canvas(bitmap);  
        drawable.setBounds(0,0,width,height);  
        drawable.draw(canvas);  
        return bitmap;  
    }   

}