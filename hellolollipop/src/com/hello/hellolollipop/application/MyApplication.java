package com.hello.hellolollipop.application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class MyApplication extends Application {
	private RequestQueue  requestQueue;	
	
	@Override
		public void onCreate() {
			super.onCreate();
			requestQueue=Volley.newRequestQueue(this);
		}
	public RequestQueue getRequestQuene(){
		return requestQueue;
	}
}
