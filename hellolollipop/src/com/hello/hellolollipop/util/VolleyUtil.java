package com.hello.hellolollipop.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.webkit.WebView.FindListener;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;
import com.hello.hellolollipop.application.MyApplication;
import com.hello.hellolollopop.model.Post;

public class VolleyUtil<T> {

	private Map<String, String> params;
	private RequestQueue requestQueue;
	private String url;
	private NetworkListener<T> lisenListener;
	private Type type;
	private final String SERVER_PATH = "";
	public VolleyUtil() {
		params = new HashMap<String, String>();
	}
	public VolleyUtil<T> get(MyApplication application, NetworkListener<T> networkListener, String url) {
		requestQueue = application.getRequestQuene();
		this.url = url;
		this.lisenListener = networkListener;
		return this;
	}
	public VolleyUtil<T> params(String key, String value) {
		params.put(key, value);
		return this;
	}
	public VolleyUtil<T> dataType(Type t) {
		this.type = t;
		return this;
	}
	public void build() {
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				handleResponse(response);
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				lisenListener.onError(error.getMessage());
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}
		};
		requestQueue.add(request);
	}
	public void handleResponse(String result) {
		BaseResponse<T> response = null;
		try {
			response = new HttpAnalyze().analyze(result, type);
		} catch (Exception e) {
			lisenListener.onError(e.getMessage());
		}
		if (response != null) {
			int status = response.getCode();
			switch (status) {
				case 200 :
					lisenListener.onSucess((ArrayList<T>) response.getData());
					break;
				default :
					String error = response.getMsg() == null ? "" : response.getMsg();
					lisenListener.onError(error);
					break;
			}
		} else {
		}
	}
}
