package com.systemical.android.eventor;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthServiceProvider;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	
	static final String CONSUMER_KEY    = "services.systemical.com";
	static final String CONSUMER_SECRET = "PkyFMaAhcPacERXjRWFv1a/U";
			
	static final String REQUEST_TOKEN_URL = "http://services.systemical.com/_ah/OAuthGetRequestToken";
	static final String ACCESS_TOKEN_URL =  "http://services.systemical.com/_ah/OAuthGetAccessToken";
	static final String AUTHORIZATION_URL = "http://services.systemical.com/_ah/OAuthAuthorizeToken";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        createAuthorizeButton();
    }
    
    protected void createAuthorizeButton() {
	    final Button button = (Button) findViewById(R.id.bAuthorize);
	    button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            doAuthorize();
	        }
	    });
    }
    
    protected void doAuthorize() {
    	OAuthAccessor client = defaultAccessor();
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setData(
    	      Uri.parse(
    	           client.consumer.serviceProvider.userAuthorizationURL+
    	           "?oauth_token="+client.requestToken+
    	           "&oauth_callback="+client.consumer.callbackURL));
    	startActivity(intent);
    }
    
    
	public static OAuthServiceProvider defaultProvider() {

		    OAuthServiceProvider provider = new OAuthServiceProvider(
		    		REQUEST_TOKEN_URL, 
		    		AUTHORIZATION_URL, 
		    		ACCESS_TOKEN_URL);
		    return provider;
		}
	
	public OAuthAccessor defaultAccessor() {
	    String callbackUrl = "eventor-android-app:///";
	    OAuthServiceProvider provider =  defaultProvider();
	    OAuthConsumer consumer = new OAuthConsumer(callbackUrl, 
	    											CONSUMER_KEY,
	    											CONSUMER_SECRET, 
	    											provider);
	    OAuthAccessor accessor = new OAuthAccessor(consumer);
	    return accessor;
	}    
    
}//