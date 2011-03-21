/**
 * @author jldupont
 * 
 *   http://developer.android.com/reference/android/app/Activity.html
 */
package com.systemical.android.eventor;

import com.systemical.android.eventor.MainService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity {
	
	final String TAG="Eventor";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startService(new Intent(this, MainService.class));
    }

    public void onStart(){
    	Log.d(TAG, "onStart");
    }
    
    public void onResume() {
    	Log.d(TAG, "onResume");
    }
    
    public void onPause() {
    	Log.d(TAG, "onPause");    	
    }
    
    public void onStop() {
    	Log.d(TAG, "onStop");    	
    }
    
    public void onDestroy() {
    	Log.d(TAG, "onDestroy");    	
    }
    
    public void onRestart() {
    	Log.d(TAG, "onRestart");    	
    }
    
}//
