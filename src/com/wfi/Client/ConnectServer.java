package com.wfi.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import android.content.Context;
import android.util.Log;

public class ConnectServer {
	private final int STATE_OPEN=1;
	private final int STATE_CLOSE=1<<1;
	private final int STATE_CONNECT_START=1<<2;
	private final int STATE_CONNECT_SUCCESS=1<<3;
	private final int STATE_CONNECT_FAILED=1<<4;
	private final int STATE_CONNECT_WAIT=1<<5;
	
	/*https://github.com/zz7zz7zz/android-socket-client*/
	
	private String IP = "192.168.1.14";
	private int PORT = 5129;
	private final String TAG = "Client";
	
	private int state = STATE_CONNECT_START;
	
	private Socket socket=null;
	private DataOutputStream outStream = null;
	private DataInputStream inStream = null;
	
	private Thread conn = null;
	private Thread send = null;
	private Thread rec = null;
	
	private Context context;
	private ISocketResponse respListener;
	private LinkedBlockingQueue<Packet> requestQueen = new LinkedBlockingQueue<Packet>();
	private final Object lock=new Object();
	
	public int send(Packet in)
	{
		requestQueen.add(in);
		synchronized(lock){
			lock.notifyAll();
		}
		return in.getId();
	}
	
	public void sendUTF(String str){
		try{
			outStream.write(str.getBytes(), 0, str.length());
			outStream.flush();
		}catch(SocketException e1){
			Log.d(TAG, "SocketException:"+e1.getMessage());
			e1.printStackTrace();
			reconn();
		}catch(Exception e){
			Log.d(TAG,"Send:Exception="+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void cancel(int reqId)
	{
		Iterator<Packet> mIterator = requestQueen.iterator();
		while(mIterator.hasNext()){
			Packet packet=mIterator.next();
			if(packet.getId()==reqId){
				mIterator.remove();
			}
		}
		
	}
	
	public ConnectServer(Context context, ISocketResponse respListener)
	{
		this.context = context;
		this.respListener=respListener;
	}
	
	public boolean isNeedConn()
	{
		return !((state==STATE_CONNECT_SUCCESS)&&(null != send&&send.isAlive())&&(null!=rec&rec.isAlive()));
	}
	
	public void open()
	{
		reconn();
	}
	
	public void open(String host, int port)
	{
		this.IP = host;
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
		
		close();
		state = STATE_OPEN;
		conn = new Thread(new Conn());
		conn.start();
	}
	
	public synchronized void close()
	{
		try{
			if(state != STATE_CLOSE)
			{
				try{
					if(null != socket)
					{
						socket.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					socket = null;
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
					if(null != rec&&rec.isAlive()){
						rec.interrupt();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					rec=null;
				}
				state=STATE_CLOSE;
			}
			requestQueen.clear();
	}catch(Exception e){
		e.printStackTrace();
	}
}

	
	private class Conn implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG, "Conn:star. IP="+IP+" Port="+PORT);
			try{
			while(state != STATE_CLOSE){
				try{
					state = STATE_CONNECT_START;
					socket = new Socket();
					socket.connect(new InetSocketAddress(IP, PORT),15*1000);
					state = STATE_CONNECT_SUCCESS;
					Log.d(TAG, "Conn:success");
				}catch(Exception e){
					Log.e(TAG, "Conn-->Exception: "+Log.getStackTraceString(e));
					e.printStackTrace();
					state=STATE_CONNECT_FAILED;
				}
				
				if(state==STATE_CONNECT_SUCCESS)
				{
					try{
						outStream=new DataOutputStream(socket.getOutputStream());
						inStream = new DataInputStream(socket.getInputStream());
					}catch(IOException e){
						e.printStackTrace();
					}
					
					send = new Thread(new Send());
					rec = new Thread(new Rec());
					send.start();
					rec.start();
					break;
				}
				else
				{
					state = STATE_CONNECT_WAIT;
					
					if(NetworkUtil.inNetworkAvailable(context)){
						try{
							Thread.sleep(15*1000);
						}catch(InterruptedException e){
							e.printStackTrace();
							break;
						}
					}else{
						break;
					}
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}	
			Log.d(TAG, "Conn:End");
		}
		
	}
	
	private class Send implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG, "Send:start");
			try{
				while(state!=STATE_CLOSE&&state==STATE_CONNECT_SUCCESS&&null!=outStream)
				{
					Packet item;
					while(null!=(item=requestQueen.poll()))
					{
						Log.d(TAG, "Send:"+item.getPacket().toString());
						outStream.write(item.getPacket(), 0, item.getPackLen());
						outStream.flush();
						item=null;
					}
					Log.d(TAG,"Send:woken up 1");
					synchronized (lock)
					{
						lock.wait();
					}
					Log.d(TAG,"Send:woken up 2");
				}
			}catch(SocketException e1){
				e1.printStackTrace();
				reconn();
			}catch(Exception e){
				Log.d(TAG,"Send:Exception");
				e.printStackTrace();
			}
			Log.d(TAG,"Send:End");
		}
		
	}
	
	
	private class Rec implements Runnable
	{
		public void run(){
			Log.d(TAG,"Rec:start");
			try{
				while(state!=STATE_CLOSE&&state==STATE_CONNECT_SUCCESS&&null!=inStream)
				{
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
						
						break;
					}
					//break;
				}
				reconn(); // �ߵ���һ��˵��������socket����
			}catch(SocketException e1){
				e1.printStackTrace();	//�ͻ�������socket.close()���������java.net.SocketException: socket closed
			}catch(Exception e2){
				Log.d(TAG, "Rec:Exception");
				e2.printStackTrace();
			}
			Log.d(TAG, "Rec:End");
		}
	}
}
