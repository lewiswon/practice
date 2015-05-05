package com.hello.hellolollipop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListFooterActivity extends Activity implements OnScrollListener, OnGestureListener {
	private ListView listView;
	private LinearLayout hiede_ll;
	private boolean isMove = false;

	private GestureDetector gestureDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_footer);
		listView = (ListView)findViewById(R.id.food_list);
		hiede_ll = (LinearLayout) findViewById(R.id.hide_ll);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("hello " + i);

		}
		listView.setOnScrollListener(this);
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
//		this.changeListViewHeight();
		this.handleSocroll();
	}
	@Override
	public void onScroll(AbsListView view, int firstVisiableItem, int visibleItemCount, int totalItemCount) {
		Log.i("visible", "firstVisible:" + firstVisiableItem + ",visibleCount:" + visibleItemCount + ",totalCount" + totalItemCount);
		View chiledView = view.getChildAt(0);
		if (chiledView == null)
			return;
		Rect rect = new Rect();
		chiledView.getLocalVisibleRect(rect);
		ListAdapter adapter = listView.getAdapter();
		if ((firstVisiableItem + visibleItemCount) == totalItemCount) {
//			listView.setVisibility(view.GONE);
			isMove = true;
		}

	}
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
	}
	public void changeListViewHeight() {
		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		((MarginLayoutParams) params).setMargins(10, 10, 10, 10);

		listView.setLayoutParams(params);
	}

	public void handleSocroll() {
		gestureDetector = new GestureDetector(this, this);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		gestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	@Override
	public boolean onDown(MotionEvent arg0) {
		return false;
	}
	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
		return false;
	}
	@Override
	public void onLongPress(MotionEvent arg0) {
	}
	@Override
	public boolean onScroll(MotionEvent startEvent, MotionEvent endEvent, float arg2, float arg3) {
		float starY = startEvent.getY();
		float endY = endEvent.getY();
		float absY=Math.abs(starY-endY);
		if (isMove) {
	RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)hiede_ll.getLayoutParams();
		params.topMargin=(int) -absY;
		hiede_ll.setLayoutParams(params);
		}
		Log.i("absy", absY+"");
		return false;
	}
	@Override
	public void onShowPress(MotionEvent arg0) {
	}
	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		return false;
	}
}
