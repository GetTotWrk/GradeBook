package com.GetTotWrk.gradebook;

import android.app.Application;

public class Global extends Application {

	private String myState;

	public String getState(){
		return myState;
	}
	public void setState(String s){
		myState = s;
	}
}

