/**
 * @author jldupont
 */
package com.systemical.android.eventor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver {

	final String TAG="Eventor.IncomingCallReceiver";
	
	final String MsgTemplate="{" +
			" 'type':    'incomingCall'"+
			",'state':   '%s'"+
			", 'number': '%s'"+
			"}";
	
	
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		
        if(null == bundle)
                return;
        try {
	        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
	        String phoneNumber=null;
	        
	        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
	        	phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
	        }

	        Intent i=prepareIntent(state, phoneNumber);
	        context.startService(i);
	        
        } catch(Exception e) {
        	Log.e(TAG, "Exception<< "+e.toString());
        }
	}//
	
	public Intent prepareIntent(String state, String number) {
		String msg=String.format(MsgTemplate, state, number);
		
        Intent icIntent=new Intent("com.systemical.android.eventor.MainService");
        icIntent.putExtra("type",   "incomingCall");
        icIntent.putExtra("state",  state);
        icIntent.putExtra("number", number);
        icIntent.putExtra("msg",    msg);
        return icIntent;
	}

}//