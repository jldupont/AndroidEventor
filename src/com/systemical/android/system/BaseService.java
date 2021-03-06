package com.systemical.android.system;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public abstract class BaseService extends IntentService {

	protected static String TAG;
	
	public BaseService(String tag) {
		super(tag);
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "onStartCommand: START - intent: "+ intent);
		
		if (null==intent) {
			Log.v(TAG, "onStartCommand: received NULL intent...");
		} else {
			Bundle b=intent.getBundleExtra("msg");
			if (b!=null) {
				String type=b.getString("type").toLowerCase();
				preprocess(type, b);
			}			
		}
		
        int result=processIntent(intent, flags, startId);
        Log.v(TAG, "onStartCommand: END - intent: "+ intent);
        return result;
	}//

	protected abstract void preprocess(String type, Bundle b);
	
	/**
	 * For the Intents we can't preprocess directly
	 *  
	 * @param i
	 */
	protected abstract int processIntent(Intent intent, int flags, int startId);
	
}//
