/**
 * @author jldupont
 */
package com.systemical.android.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public abstract class BaseReceiver extends BroadcastReceiver {

	public BaseReceiver() {
		super();
	}
	
	/**
	 * Must be provisioned at startup
	 */
	static String mainContext=null;
	
	public static void setMainContext(String context) {
		mainContext=context;
	}
	
	protected void sendMsg(Context context, String ...params) {
        Intent icIntent=new Intent(mainContext);
        Bundle b=new Bundle();
        
        int index=0;
        String key=null;
        for (String param : params) {
        	if (index++ % 2==0)
        		key=param;
        	else
        		b.putString(key, param);
        }
        icIntent.putExtra("msg", b);
        context.startService(icIntent);		
	}
	
	
}///
