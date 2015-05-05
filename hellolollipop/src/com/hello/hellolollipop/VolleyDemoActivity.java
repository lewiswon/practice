package com.hello.hellolollipop;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hello.hellolollipop.application.MyApplication;
import com.hello.hellolollipop.util.BaseResponse;
import com.hello.hellolollipop.util.NetworkListener;
import com.hello.hellolollipop.util.VolleyUtil;
import com.hello.hellolollopop.model.Post;

public class VolleyDemoActivity extends Activity {
	private Context mContext;
	private MyApplication myApplication;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley_demo);
		mContext = this;
		myApplication = (MyApplication) getApplication();
		getData();
	}

	public void getData() {
		VolleyUtil<Post> volleyUtil = new VolleyUtil<>();
		Type type = new TypeToken<BaseResponse<Post>>() {
		}.getType();
		volleyUtil.get(myApplication, new NetworkListener<Post>() {

			@Override
			public void onSucess(ArrayList<Post> list) {
				Log.i("list", list.size() + "");
			}
			@Override
			public void onError(String error) {
			}

		}, "http://www.cqjlpfy.gov.cn:8090/api/postList.htm").params("type", "guide").dataType(type).build();
	}
}
