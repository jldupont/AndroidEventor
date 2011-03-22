/**
 * 
 * @author jldupont
 */
package com.systemical.android.eventor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

import com.systemical.android.system.BaseReceiver;

public class SMSReceiver extends BaseReceiver {

	final String TAG="Eventor.SMSReceiver";
	static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	
	TelephonyManager mTelephonyMgr=null;
	String thisPhoneNumber=null;
	
	public SMSReceiver() {
		super();
	}
	
	public void onReceive(Context context, Intent intent) {
		
		if (null==intent)
			return;
		
        if (mTelephonyMgr==null) {
            mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);    
            thisPhoneNumber=mTelephonyMgr.getLine1Number();
        }
		
		final String action = intent.getAction();
		Log.v(TAG, "action: "+action);
		
		if (action.equals(ACTION)) {
	        /* The SMS-Messages are 'hiding' within the extras of the Intent. */
	        Bundle bundle = intent.getExtras();
	        if (bundle != null) {
	        	Object[] pdus=(Object[]) bundle.get("pdus");
	        	SmsMessage msg=null;
	        	for(int i=0; i<pdus.length;i++) {
		        	msg=SmsMessage.createFromPdu((byte[])pdus[i]);	        		
	        		sendMsg(context, "type", "sms"
	        						,"from", msg.getOriginatingAddress()
	        						,"body", msg.getMessageBody().toString()
	        						,"phone", thisPhoneNumber
	        						);
	        	}
	        	
	        }
		}		
	}///

	
}///
