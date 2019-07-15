package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import com.testyourself.adapter.ButtonAdapter2;

public class Buttons2 extends Activity {
	private final String PREFS_NAME = "cricket";
	MediaPlayer mp;
	static int index = 0;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_buttons2);
		mp = MediaPlayer.create(this, R.raw.button);

		GridView gridview = (GridView) findViewById(R.id.gridView);
		final ButtonAdapter2 gridadapter = new ButtonAdapter2(this);
		gridview.setAdapter(gridadapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				int i = position + 1;
				SharedPreferences prefs = getSharedPreferences(PREFS_NAME,
						MODE_PRIVATE);
				final int maxUnlockedLevel = prefs
						.getInt("maxUnlockedLevel", 1);
				if (position <= maxUnlockedLevel) {
					mp.start();
					Intent intent = new Intent(Buttons2.this, Questions2.class)
							.putExtra("from", i);

					startActivity(intent);
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		GridView gridview = (GridView) findViewById(R.id.gridView);
		mp = MediaPlayer.create(this, R.raw.button);
		gridview.setSelection(index);
		super.onResume();
	}

	@Override
	protected void onPause() {
		GridView gridview = (GridView) findViewById(R.id.gridView);
		index = gridview.getFirstVisiblePosition();
		super.onPause();

	}

	@Override
	protected void onStop() {
		super.onStop();
		// Deallocate all memory
		if (mp != null) {
			if (mp.isPlaying()) {
				mp.stop();
			}
			mp.release();
			mp = null;
		}
	}

	@Override
	public void onBackPressed() {
		mp.start();
		Intent i = new Intent(Buttons2.this, Category.class);
		startActivity(i);
		overridePendingTransition(R.anim.leftin, R.anim.leftout);
	}
}