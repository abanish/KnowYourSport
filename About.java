package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class About extends Activity {

	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// set content view AFTER ABOVE sequence (to avoid crash)
		setContentView(R.layout.activity_about);
	}

	// starting category activity when the play button is clicked.
	public void facebook(View v) {
		mp = MediaPlayer.create(this, R.raw.button);
		mp.start();
		Intent browserIntent = new Intent("android.intent.action.VIEW",
				Uri.parse("https://www.facebook.com/knowyoursportapp"));
		startActivity(browserIntent);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);

	}

	public void twitter(View v) {
		mp = MediaPlayer.create(this, R.raw.button);
		mp.start();

		Intent browserIntent = new Intent("android.intent.action.VIEW",
				Uri.parse("https://twitter.com/Know_Your_Sport"));
		startActivity(browserIntent);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);

	}

	public void googleplus(View v) {
		mp = MediaPlayer.create(this, R.raw.button);
		mp.start();

		Intent browserIntent = new Intent("android.intent.action.VIEW",
				Uri.parse("https://plus.google.com/110904005625570958328/"));
		startActivity(browserIntent);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);

	}

	public void Rate(View v) {
		mp = MediaPlayer.create(this, R.raw.button);
		mp.start();

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

	@Override
	public void onBackPressed() {
		mp = MediaPlayer.create(this, R.raw.button);
		mp.start();
		Intent i = new Intent(About.this, MainActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.leftin, R.anim.leftout);
	}

}