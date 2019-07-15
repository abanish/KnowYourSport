package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Category extends Activity {
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_category);
		// hardware buttons to control volume
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// create button sound
		mp = MediaPlayer.create(this, R.raw.button);
	}

	// show football question buttons on football icon clicked
	public void football(View v) {
		mp.start();
		Intent i = new Intent(Category.this, Buttons.class);
		startActivity(i);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
	}

	// show cricket questions on cricket icon clicked
	public void cricket(View v) {
		mp.start();
		Intent i = new Intent(Category.this, Buttons2.class);
		startActivity(i);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
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
	protected void onResume() {
		super.onResume();
		mp = MediaPlayer.create(this, R.raw.button);
	}

	// go back to main
	@Override
	public void onBackPressed() {
		mp.start();
		Intent i = new Intent(Category.this, MainActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.leftin, R.anim.leftout);
	}
}