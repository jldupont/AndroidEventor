/**
 * @author jldupont
 */
package com.systemical.android.eventor;

import com.systemical.android.system.BaseReceiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BaseReceiver {

	String thisPhoneNumber=null;
	
	TelephonyManager mTelephonyMgr=null;
	
	public IncomingCallReceiver() {
		super();
		
 
	}
	
	final String TAG="Eventor.IncomingCallReceiver";
	
	public void onReceive(Context context, Intent intent) {
		//Log.e(TAG, "onReceive: START");
		
		Bundle bundle = intent.getExtras();
		
        if(null == bundle)
                return;
        
        if (mTelephonyMgr==null) {
            mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);    
            thisPhoneNumber=mTelephonyMgr.getLine1Number();
        }
        try {
	        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
	        String phoneNumber=null;
	        
	        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
	        	phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
	        }

	        sendMsg(context, "type", "incomingCall"
	        				,"state",  state
	        				,"number", phoneNumber
	        				,"phone", thisPhoneNumber
	        		);
	        
        } catch(Exception e) {
        	Log.e(TAG, "Exception<< "+e.toString());
        }
        
        //Log.e(TAG, "onReceive: END");
	}//
	
}//