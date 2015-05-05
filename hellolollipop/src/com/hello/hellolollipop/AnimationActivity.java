package com.hello.hellolollipop;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationActivity extends Activity {
	ImageView carIv, wheelLeft, wheelRight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_loadding);
		carIv = (ImageView) findViewById(R.id.car);
		wheelLeft = (ImageView) findViewById(R.id.wheel_left);
		wheelRight = (ImageView) findViewById(R.id.wheel_right);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.wheel_rotation);
		LinearInterpolator lir = new LinearInterpolator();
		animation.setInterpolator(lir);
		Animation shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
		carIv.startAnimation(shakeAnimation);
		wheelLeft.startAnimation(animation);
		wheelRight.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
	}
}
