/**
 * Main Service
 * 
 * @author jldupont
 */
package com.systemical.android.eventor;


import com.systemical.android.system.BaseReceiver;
import com.systemical.android.system.BaseService;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

public class MainService extends BaseService {

	final static String THIS_SERVICE="com.systemical.android.eventor.MainService";
	final static String TAG="Eventor.MainService";
	
	NetworkEvent ne=null;
	Notifier     no=null;
	
	public MainService() {
		super(TAG);
	}
	
	public void onCreate() {
		//Log.v(TAG, "onCreate");
		BaseReceiver.setMainContext(THIS_SERVICE);
	
		ne=new NetworkEvent(this);
		no=new Notifier();		
	}
	
	protected int processIntent(Intent intent, int flags, int startId) {
		//Log.v(TAG, "processIntent: "+intent);
		return START_STICKY;
	}

	
	protected void preprocess(String type, Bundle b) {
		//Log.v(TAG, "preprocess: START");
		
		if (type!=null) {
			if (type.equals("incomingcall")) {
				h_call(b);
			}
			if (type.equals("wifi")) {
				h_wifi(b);
			}			
		}
		//Log.v(TAG, "preprocess: END");
	}//
	
	protected void h_wifi(Bundle b) {
		String state=b.getString("state");
		
		if (state.equals("connected"))
			ne.refresh();
	}
	
	protected void h_call(Bundle b) {
		
		String packetData=null;		
		try{	
			packetData=no.prepare(b);
			ne.sendData(packetData);
			Log.v(TAG, "packet sent: "+packetData);			
		}catch(Exception e) {
			Log.e(TAG, "error sending packet: "+e.toString());
		}		
	}//
	
	protected void onHandleIntent(Intent arg0) {
		//Log.v(TAG, "onHandleIntent: "+arg0);
		
	}
	
	public void onDestroy () {
		Log.v(TAG, "destroyed...");
	}
}///
