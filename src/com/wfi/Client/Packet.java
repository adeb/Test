package com.wfi.Client;

public class Packet {
	private int id=AtomicIntegerUtil.getIncrementID();
	private byte[] data = new byte[1024];
	private int curlen=0;
	private byte[] temp;
	
	public int getId()
	{
		return id;
	}
	
	public void pack(String txt){
		//data = new byte[1024];
		//temp = txt.getBytes();
		//curlen += len;
		//System.arraycopy(temp, 0, data, len, temp.length);
		data = txt.getBytes();
	}
	
	public void setHead(byte head)
	{
		data[0] = head;
	}
	
	public void setCRC(int crc)
	{
		data[1] = (byte)(crc>>24);
		data[2]	= (byte)(crc>>16);
		data[3] = (byte)(crc>>8);
		data[4] = (byte)(crc);
	}
	
	public void setLength(int len)
	{
		data[5] = (byte)(len>>24);
		data[6] = (byte)(len>>16);
		data[7] = (byte)(len>>8);
		data[8] = (byte)(len);
	}
	
	public void setCmd(int cmd)
	{
		data[9] = (byte)(cmd>>24);
		data[10] = (byte)(cmd>>16);
		data[11] = (byte)(cmd>>8);
		data[12] = (byte)(cmd);
	}
	
	public void setDataPack(byte[] buf, int len)
	{
		System.arraycopy(buf, 0, data, 13, len);
	}
	
	public byte[] getPacket()
	{
		return data;
	}
	
	public void cleanpack(){
		data=null;
		curlen=0;
	}
}
