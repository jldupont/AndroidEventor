package com.systemical.android.eventor;

import java.io.IOException;
import java.net.URISyntaxException;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
	        	try {
	            doAuthorize();
	        	} catch(Exception e) {
	        		Context context = getApplicationContext();
	        		CharSequence text = "Exception: "+e.toString();
	        		int duration = Toast.LENGTH_SHORT;
	        		
	        		Toast toast = Toast.makeText(context, text, duration);
	        		toast.show();
	        	}
	        }
	    });
    }
    
    protected void doAuthorize() throws IOException, OAuthException, URISyntaxException {
    	OAuthAccessor accessor = defaultAccessor();

    	OAuthClient client = new OAuthClient(new HttpClient4());
        client.getRequestToken(accessor);

    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setData(
    	      Uri.parse(
    	           accessor.consumer.serviceProvider.userAuthorizationURL+
    	           "?oauth_token="+accessor.requestToken+
    	           "&oauth_callback="+accessor.consumer.callbackURL));
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