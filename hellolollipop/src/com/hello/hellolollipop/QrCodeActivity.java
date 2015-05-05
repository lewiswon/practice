package com.hello.hellolollipop;

import com.hello.hellolollipop.util.QRCodeGenerator;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class QrCodeActivity extends Activity {
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrcode);
		imageView=(ImageView)findViewById(R.id.qrcode_container);
		DisplayMetrics metrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);;
		QRCodeGenerator.createQRImage(metrics.widthPixels, metrics.widthPixels, "http://about.me/lewiswon", imageView);
	}
}
