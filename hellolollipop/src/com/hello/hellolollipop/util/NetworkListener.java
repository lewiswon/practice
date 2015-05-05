package com.hello.hellolollipop.util;

import java.util.ArrayList;


public interface NetworkListener<T> {
	public void onSucess(ArrayList<T> arrayList);
	public void onError(String error);
}
