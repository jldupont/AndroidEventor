/**
 * Main Service
 * 
 * @author jldupont
 */
package com.systemical.android.eventor;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import com.systemical.android.net.NetUtil;
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
		Log.v(TAG, "onCreate");
		
		BaseReceiver.setMainContext(THIS_SERVICE);
	
		ne=new NetworkEvent(this);
		no=new Notifier();
	}
	
	protected int processIntent(Intent intent, int flags, int startId) {
		Log.v(TAG, "processIntent: "+intent);
		return START_STICKY;
	}

	
	protected void preprocess(String type, Bundle b) {
		Log.v(TAG, "preprocess: START");
		
		String packetData=null;
		if (type!=null) {
			if (type.equals("incomingcall")) {
				try{
					packetData=no.prepare(b);
					
					Log.v(TAG, "sending packet!");
					ne.sendData(packetData);
				}catch(Exception e) {
					Log.e(TAG, "error sending packet: "+e.toString());
				}
			}
		}
		Log.v(TAG, "preprocess: END");
	}//
	
	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v(TAG, "onHandleIntent: "+arg0);
		
	}
	
}///
