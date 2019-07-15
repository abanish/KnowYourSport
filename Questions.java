package com.testyourself.knowyoursport;

import java.util.ArrayList;
import java.util.Collections;

import com.testyourself.adapter.DBAdapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class Questions extends Activity {

	private static final String TABLE_NAME = "questions";
	private static final String ROW_ID = "_id";
	private static final String QB_NAME = "canvas";
	private static final String QB_FIRST = "first";
	private static final String QB_SECOND = "second";
	private static final String QB_THIRD = "third";
	private static final String QB_FOURTH = "fourth";
	private static final String QB_FIFTH = "fifth";
	private static final String QB_SIXTH = "sixth";
	private static final String QB_SEVENTH = "seventh";
	private static final String QB_EIGHTH = "eighth";
	private static final String QB_NINTH = "ninth";
	private static final String QB_TENTH = "tenth";
	private static final String DB_NAME="footballdb";

	
	

	private static SQLiteDatabase database;
	public static TextView textview;
	public int q;
	private int k;
	private String[] first =new String[10];
	private String[] second =new String[10];
	private String[] extra =new String[2];
	MediaPlayer mp;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_questions);
		mp=MediaPlayer.create(this, R.raw.button);
		Bundle bundle = getIntent().getExtras();
		DBAdapter dbOpenHelper = new DBAdapter(this, DB_NAME);
		database = dbOpenHelper.openDataBase();
		textview = (TextView) findViewById(R.id.question);
		dbOpenHelper.openDataBase();
		
	
		q = bundle.getInt("from");
		Cursor c = Questions.getQuestion(q);
		if (c.moveToFirst()) {
			textview.setText(String.valueOf(c.getString(1)));
		}


		// shuffling the array of the retrieved letters to randomize
				for (int h = 2; h < 12; h++) {
					if (c.getString(h).toString().equals("!")) {
						break;
					} else {
						k++;
					}
				}

				ArrayList<Integer> numbers = new ArrayList<Integer>();
				for (int j = 2; j < k + 2; j++) {
					numbers.add(j);
				}

				Collections.shuffle(numbers);
				for (int i = 0; i < k; i++) {
					int retval = numbers.get(i);
					first[i] = c.getString(retval).toString();
				 
				}
				for (int i = k; i < 10; i++) {
					first[i] = "b";
				}
				for (int m = 0; m < 8; m++) {
					second[m] = c.getString(m + 2).toString();
				}
				
					extra[0]=c.getString(k).toString();
					extra[1]=c.getString(k+1).toString();
				
	}

	public void answer(View v) {
		
	    mp.start();
			Intent i = new Intent(Questions.this, Answer.class);
			Bundle extras = new Bundle();
			extras.putString("database", "football");
			extras.putInt("category", 1);
			extras.putInt("k", k);
			extras.putInt("from", q);
			extras.putStringArray("first", first);
			extras.putStringArray("second", second);
			extras.putStringArray("extra", extra);
			i.putExtras(extras);
			
			/*Bundle bnaniamtion = ActivityOptions.makeCustomAnimation(
			getApplicationContext(), R.anim.rightin, R.anim.rightout)
			.toBundle();*/
	startActivity(i);
	overridePendingTransition(R.anim.rightin, R.anim.rightout);
		 

	}

	public static Cursor getQuestion(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, TABLE_NAME,
				new String[] { ROW_ID, QB_NAME, QB_FIRST, QB_SECOND, QB_THIRD,
						QB_FOURTH, QB_FIFTH, QB_SIXTH, QB_SEVENTH, QB_EIGHTH,
						QB_NINTH, QB_TENTH }, ROW_ID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
 /*
	protected void onStop()
	{
		super.onStop();
		mp.stop();
	}
 
	 protected void onDestroy() {
	  // TODO Auto-generated method stub
	  super.onDestroy();
	  if (mp != null) {
	   mp.release();
	   mp = null; 
	  }
	 }

	 
	 protected void onPause() {
	  // TODO Auto-generated method stub
	  super.onPause();
	  if (mp != null) {
	   mp.release();
	   mp= null; 
	  }
	
	 }
 */
	@Override
	protected void onStop()
	{
		super.onStop();
		//Deallocate all memory
		if(mp!=null){
			if(mp.isPlaying()){
				mp.stop();
			}
			mp.release();
			mp=null;
		}
	}
	@Override
	public void onBackPressed() {
	    mp.start();
		DBAdapter dbOpenHelper = new DBAdapter(this, DB_NAME);
	 dbOpenHelper.close();
		 
			Intent i = new Intent(Questions.this, Buttons.class);
			/*Bundle bnaniamtion = ActivityOptions.makeCustomAnimation(
			getApplicationContext(), R.anim.rightin, R.anim.rightout)
			.toBundle();*/
	startActivity(i);
	overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}  
	}

