package com.wfi.clientclass;

import com.wfi.common.tran.*;

/**
 * 消息监听接口
 * 
 * @author way
 * 
 */
public interface MessageListener {
	public void Message(TranObject msg);
}
