package com.systemical.android.eventor;

import com.systemical.android.eventor.MainService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity {
	
	final String TAG="Eventor";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startService(new Intent(this, MainService.class));
    }

    
    
}//