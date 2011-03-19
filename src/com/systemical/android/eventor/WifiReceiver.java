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
	
	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();
		Log.v(TAG, "action: "+action);
		
		if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
		  NetworkInfo info = (NetworkInfo)intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		  String state=info.getState().equals(NetworkInfo.State.CONNECTED) ? "connected":"disconnected";
		  Log.v(TAG, "sending msg, state:"+state);
		  sendMsg(context, "type", "wifi"
        				,"state",  state);		  
		}		
		
	}

}///
