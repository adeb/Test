package com.wfi.Client;

public class Packet {
	private int id=AtomicIntegerUtil.getIncrementID();
	//private String sendstr;
	private byte[] PackData = new byte[4096];
	private int PackLength=0;
	
	public int getId()
	{
		return id;
	}
	
	public void SetCmd(byte cmd){
		PackData[0] = cmd;
	}
	
	public void pack(byte[] txt){
		//data = new byte[1024];
		//temp = txt.getBytes();
		//curlen += len;
		//System.arraycopy(temp, 0, data, len, temp.length);
		//data = txt.getBytes();
		PackLength = 0;
		int len = txt.length;
		PackData[1] = (byte)(len>>24);
		PackData[2] = (byte)(len>>16);
		PackData[3] = (byte)(len>>8);
		PackData[4] = (byte)len;
		
		System.arraycopy(txt, 0, PackData, 5, len);
		PackLength = 5+len;
		//sendstr = txt;
	}
	
	public byte[] getPacket(){
		return PackData;
	}
	
	public int getPackLen(){
		return PackLength;
	}
}
