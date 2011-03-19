/**
 * @author jldupont
 */
package com.systemical.android.eventor;

import android.content.Context;
import android.content.Intent;

import com.systemical.android.system.BaseReceiver;

public class BootReceiver extends BaseReceiver {

	public void onReceive(Context context, Intent intent) {

		context.startService(new Intent(context, MainService.class));
	}

}///

