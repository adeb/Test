package com.wfi.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;

public class Connect2Server {
	
	private final int STATE_OPEN=1;
	private final int STATE_CLOSE=1<<1;
	private final int STATE_CONNECT_START=1<<2;
	private final int STATE_CONNECT_SUCCESS=1<<3;
	private final int STATE_CONNECT_FAILED=1<<4;
	private final int STATE_CONNECT_WAIT=1<<5;
	
	private String IP = "192.168.1.14";
	private int PORT = 5129;
	private final String TAG = "client";
	
	private int state = STATE_CONNECT_START;
	private boolean connectstate = false;
	
	private Socket socket = null;
	private DataOutputStream outStream = null;
	private DataInputStream inStream = null;
	
	private Thread conn = null;
	private Thread send = null;
	private Thread recv = null;
	
	private Context context;
	private ISocketResponse respListener;
	
	byte[] sendstr= new byte[1024];
	
	
	private Timer offTimer = null;
	private TimerTask timerTask = null;
	private static final int CLOSE_TIMEOUT = 8 * 1000;
	
	private TimerTask goToActivityOff(){
		timerTask = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//finish();
				Log.d(TAG, "Close");
				close();
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
	
	public Connect2Server(Context applicationContext,
			ISocketResponse regsocketListener) {
		// TODO Auto-generated constructor stub
		this.context = applicationContext;
		this.respListener=regsocketListener;
	}

	public void open(){
		reconn();
	}
	
	public void open(String ip, int port){
		this.IP = ip;
		this.PORT = port;
		reconn();
	}
	
	private long lastConnTime = 0;
	public synchronized void reconn()
	{
		if(System.currentTimeMillis()-lastConnTime < 2000)
		{
			return;
		}
		
		lastConnTime=System.currentTimeMillis();
		
		//close();
		state = STATE_OPEN;
		conn = new Thread(new Conn());
		conn.start();
	}
	
	private class Conn implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG, "Conn start: IP="+IP+" Port="+PORT);
			
			try{
				state = STATE_CONNECT_START;
				socket = new Socket();
				socket.connect(new InetSocketAddress(IP,PORT), 15*1000);
				if(socket.isConnected()){
					state = STATE_CONNECT_SUCCESS;
					Log.d(TAG, "Conn:success");
					try{
						outStream=new DataOutputStream(socket.getOutputStream());
						inStream = new DataInputStream(socket.getInputStream());
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				Log.d(TAG, "Conn-->Exception:"+e.getMessage());
				e.printStackTrace();
				state=STATE_CONNECT_FAILED;
			}
		}
		
	}
	
	public void sendPacket(byte[] msg){
		this.sendstr = msg;
		
		send = new Thread(new Send());
		recv = new Thread(new Recv());
		send.start();
		recv.start();
		StartTimer();
	}
	
	private class Send implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG, "Send:start");
			try{
				
					Log.d(TAG, "Send:"+sendstr);
					outStream.write(sendstr, 0, sendstr.length);
					outStream.flush();
					sendstr = null;
				
			}catch(SocketException e1){
				Log.d(TAG, "Send SocketException--->"+e1.getMessage());
				e1.printStackTrace();
				//这里添加发消息通知需要重连
				//reconn();
			}catch(Exception e){
				Log.d(TAG, "Send Exception--->"+e.getMessage());
				e.printStackTrace();
			}
			Log.d(TAG, "Send:End!");
		}
		
	}
	
	private class Recv implements Runnable
	{
		public void run(){
			Log.d(TAG,"Rec:start");
			try{
				
					byte[] bodyBytes = new byte[1024];
					int offset = 0;
					int length = 20;
					int read = 0;
					String msg=null;
					Log.d(TAG, "Rec: wait...");
					//while(true){
						read = inStream.read(bodyBytes);
						//if(new String(bodyBytes, 0, read).equals("end"))
						//if(bodyBytes[read-1]==0x01)
							//break;
					//}
					
					Log.d(TAG, "Rec: rec="+bodyBytes.toString());
					if(null != respListener){
						respListener.onSocketResponse(new String(bodyBytes, 0, read, "utf-8"));
					}
					
					/*
					while((read=inStream.readUTF(bodyBytes, offset, length))>0)
					{
						Log.d(TAG, "Rec:++++++ length="+length+"  buf="+bodyBytes);
						if(length-read == 0)
						{
							if(null !=respListener)
							{
								respListener.onSocketResponse(new String(bodyBytes));
							}
							offset = 0;
							length = 20;
							read = 0;
							continue;
						}
						offset += read;
						length=20-offset;
					}
					*/
					if(!socket.isConnected()){
						Log.d(TAG, "Need to reconn");
						reconn(); // �ߵ���һ��˵��������socket����
						//break;
					}
					//break;
				
			}catch(SocketException e1){
				e1.printStackTrace();	//�ͻ�������socket.close()���������java.net.SocketException: socket closed
			}catch(Exception e2){
				Log.d(TAG, "Rec:Exception");
				e2.printStackTrace();
			}
			Log.d(TAG, "Rec:End");
		}
	}
	
	public boolean getConnect(){
		if(state == STATE_CONNECT_SUCCESS){
			Log.d(TAG, "yes connect");
			connectstate = true;
		}else{
			Log.d(TAG, "not connect");
			connectstate = false;
		}
		return connectstate;
	}
	
	public synchronized void close()
	{
		try{
			if(true)
			{
				try{
					if(null != socket)
					{
						socket.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					//socket = null;
				}
			
			
				try{
					if(null != outStream){
						outStream.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					outStream = null;
				}
				
				try{
					if(null != inStream){
						inStream.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					inStream = null;
				}
				
				try{
					if(null != conn&&conn.isAlive()){
						conn.interrupt();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					conn=null;
				}
				
				try{
					if(null != send&&send.isAlive()){
						send.interrupt();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					send=null;
				}
				
				try{
					if(null != recv&&recv.isAlive()){
						recv.interrupt();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					recv=null;
				}
				
				state=STATE_CLOSE;
			}
			//requestQueen.clear();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
