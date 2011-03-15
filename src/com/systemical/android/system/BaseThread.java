/**
 * BaseThread class
 * 
 * @author jldupont
 */
package com.systemical.android.system;

import com.systemical.android.system.IFactory;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public abstract class BaseThread extends Thread implements IAgent {

	static IFactory f=null;
	static IMsgSwitch ms=null;
	
	Handler h=new Handler();
	protected MsgTypesList mtInterests=null;
	
	public BaseThread() {
		super();
	}

	public void setMsgSwitch(IMsgSwitch _ms){
		ms=_ms;
	}
	
	public void setFactory(IFactory factory) {
		f=factory;
	}
	
	/**
	 * Convenience method
	 * 
	 * @param msg
	 */
	protected void send(int what, Object obj) {
		Message m=Message.obtain(ms.handler, what, obj);
		h.sendMessage(m);
	}
	
	/**
	 * Dispatch
	 * 
	 * Checks if the Agent is interested in the message before
	 *  passing on to "handleMsg"
	 */
	public boolean dispatch(Message msg) {
		boolean interest=false;
		if (mtInterests==null) {
			Log.d("BaseThread","agent("+this.getAgentName()+") mtInterests is null...");
		}
		if (mtInterests.contains(msg.what)) {
			handleMsg(msg);
			interest=true;
		}
		return interest;
	}

	public Handler getHandler() {
		return h;
	}

	/**
	 * Implemented by derived classes 
	 * Real dispatch point
	 * 
	 * @param msg
	 */
	protected abstract void handleMsg(Message msg);
	
	
}///
