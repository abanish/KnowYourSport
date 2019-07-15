package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;

public class Congratulation extends Activity {
	int q;
	int category;
	String DB_NAME;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_congrats);

	}

	public void correct(View v) {
		mp = MediaPlayer.create(this, R.raw.button);
		mp.start();
		// Bundle bundle=getIntent().getExtras();
		Bundle bundle = getIntent().getExtras();
		q = bundle.getInt("from");
		category = bundle.getInt("category");
		if (category == 1) {
			Intent i = new Intent(Congratulation.this, Buttons.class);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
		if (category == 2) {
			Intent i = new Intent(Congratulation.this, Buttons2.class);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		mp = MediaPlayer.create(this, R.raw.button);
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
		Bundle bundle = getIntent().getExtras();
		category = bundle.getInt("category");
		if (category == 1) {
			Intent i = new Intent(Congratulation.this, Buttons.class);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
		if (category == 2) {

			Intent i = new Intent(Congratulation.this, Buttons2.class);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
	}
}