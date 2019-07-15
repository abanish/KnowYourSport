package com.testyourself.adapter;


import com.testyourself.knowyoursport.R;

import android.content.Context;
import android.content.SharedPreferences;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
 

public class ButtonAdapter extends BaseAdapter {
	
	private final String PREFS_NAME="football";
	Boolean[] getAnswered=new Boolean[200];
	Boolean answered;
	private Context context;
	private Integer[] imageIds = new Integer[200];

	public ButtonAdapter(Context c) {
		context = c;
	}

	public int getCount() {
		return imageIds.length;
	}
	
	public Object getItem(int position) {
		return imageIds[position];
	}
	
	public long getItemId(int position) {
		return 0;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE);
	   	 final int maxUnlockedLevel = prefs.getInt("maxUnlockedLevel", 1);
	   	 final boolean answered=prefs.getBoolean("getAnswered_"+(position+1), false);
		View gridView;
		 
		 if(view==null)
		 {
			gridView = new View(context);
		
		 
			gridView.setPadding(5, 5, 5, 5);
		 }
		 else
		 {
			 gridView=(View) view;
		 }
		 	if(answered)
			{
				gridView.setBackgroundResource(R.drawable.correct_btn);
			}
			else if(position<=maxUnlockedLevel)
			 {
			gridView.setBackgroundResource(R.drawable.unlocked_btn);
			 }
			 else
			 {
				 gridView.setBackgroundResource(R.drawable.locked_btn);
			 }
		return gridView;
	}
}