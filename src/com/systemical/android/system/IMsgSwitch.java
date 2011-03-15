/**
 * @author jldupont
 */
package com.systemical.android.system;

import android.os.Handler;
import android.os.Message;

public interface IMsgSwitch {

	/**
	 * Used by to register an Agent with the MsgSwitch
	 * 
	 * @param agent
	 */
	public void registerAgent(IAgent agent);
	
	/**
	 * Sends a message to interested recipients
	 * 
	 * @param msg
	 */
	public void send(Message msg);
	
	/**
	 * Used to dispatch messages 
	 * 
	 * @return
	 */
	public Handler getHandler();
	
}//
