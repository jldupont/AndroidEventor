/**
 * 
 * @author jldupont
 */
package com.systemical.android.eventor;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.systemical.android.system.BaseReceiver;

public class WifiReceiver extends BaseReceiver {

	final String TAG="Eventor.WifiReceiver";
	
	public WifiReceiver() {
		super();
	}
	
	public void onReceive(Context context, Intent intent) {
		
		if (null==intent)
			return;
		
		final String action = intent.getAction();
		Log.v(TAG, "action: "+action);
		
		if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
		  String state=_getState(intent);
		  Log.v(TAG, "sending msg, state:"+state);
		  sendMsg(context, "type", "wifi"
        				,"state",  state);		  
		}		
		if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
			  String state=intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false) ? "connected":"disconnected";
			  Log.v(TAG, "sending msg, state:"+state);
			  sendMsg(context, "type", "wifi"
	        				,"state",  state);			
		}
	}///

	protected String _getState(Intent intent) {
	  NetworkInfo info = (NetworkInfo)intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
	  String state=info.getState().equals(NetworkInfo.State.CONNECTED) ? "connected":"disconnected";
	  return state;
	}
	
}///
