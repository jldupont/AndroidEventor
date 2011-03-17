/**
 * @author jldupont
 */
package com.systemical.android.eventor;

import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.os.Bundle;

public class Notifier {

	public String prepare(Bundle b) {
		JsonObject jo=new JsonObject();
		
		Set<String> keys=b.keySet();
		
		for (String key: keys) {
			String value=b.getString(key);
			jo.addProperty(key, value);
		}
		
		return new Gson().toJson(jo);
	}
	
}///
