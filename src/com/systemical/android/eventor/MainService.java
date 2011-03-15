package com.systemical.android.eventor;

import com.systemical.android.system.IAgent;
import com.systemical.android.system.MsgSwitch;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MainService extends IntentService {

	final static String TAG="MainService";
	
	public MainService() {
		super(TAG);
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "MainService.onStartCommand: start");
		
        MsgSwitch ms=new MsgSwitch();
        ms.start();

        Thread debug=(Thread) Factory.get(K.DEBUG_THREAD);
        debug.start();
        

        ms.registerAgent((IAgent) debug);
        
        Log.d(TAG, "MainService.onStartCommand: end");
        return START_STICKY;
	}//

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}///
