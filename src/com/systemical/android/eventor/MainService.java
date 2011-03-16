package com.systemical.android.eventor;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import com.systemical.android.net.NetUtil;

import android.app.IntentService;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

public class MainService extends IntentService {

	final static String TAG="Eventor.MainService";
	
    private static final byte[] EVENTOR_ADDR =
        new byte[] {(byte) 239,(byte) 0,(byte) 0,(byte) 1};
    private static final int EVENTOR_PORT = 6666;
	
	NetUtil net=null;
	NetworkInterface ni=null;
	private MulticastSocket multicastSocket;
	private InetAddress groupAddress;
	
	public MainService() {
		super(TAG);
	}
	
	public void onCreate() {
		Log.v(TAG, "onCreate");
		
		net=new NetUtil(this);
		
		// highly unlikely to fail!
		try {
			groupAddress = InetAddress.getByAddress(EVENTOR_ADDR);
		}catch(Exception e) {
			Log.e(TAG, "getByAddress: "+e.toString());
		}		
		refreshNetworkInterface();
		try{ 
			openSocket();
		}catch(Exception e) {
			Log.e(TAG, "socket open: "+e.toString());
		}
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "MainService.onStartCommand: start - intent: "+ intent);
		
		Bundle b=intent.getExtras();
		if (b!=null) {
			process(b);
		}
		
        return START_STICKY;
	}//

	private void process(Bundle b) {
		String type=b.getString("type");
		if (type!=null) {
			if (type.toLowerCase().equals("incomingcall")) {
				try{
					String packetData=b.getString("msg");
					Log.v(TAG, "sending packet!");
					sendIncomingCallNotification(packetData);
				}catch(Exception e) {
					Log.e(TAG, "error sending packet: "+e.toString());
				}
			}
		}		
	}
	
	protected void refreshNetworkInterface() {
		if (ni==null) {
			ni=net.getFirstWifiInterface();
		}
		
	}//


	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v(TAG, "onHandleIntent: "+arg0);
		
	}
	
    private void openSocket() throws IOException {
        multicastSocket = new MulticastSocket(EVENTOR_PORT);
        multicastSocket.setTimeToLive(2);
        multicastSocket.setReuseAddress(true);
        multicastSocket.setNetworkInterface(ni);
        multicastSocket.joinGroup(groupAddress);
    }
	
    
    private DatagramPacket prepareIncomingCallNotificationPacket(String packetData) {
    	DatagramPacket notif;
    	byte[] requestData=packetData.getBytes();
		notif=new DatagramPacket(requestData, requestData.length, groupAddress, EVENTOR_PORT);
    	return notif; 
    }
    
    private void sendIncomingCallNotification(String packetData) throws IOException {
    	 multicastSocket.send(prepareIncomingCallNotificationPacket(packetData));
    }
    
}///
