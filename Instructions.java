package com.testyourself.knowyoursport;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Instructions extends Activity {

	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_instructions);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mp = MediaPlayer.create(this, R.raw.button);
	}

	@Override
	public void onBackPressed() {
		mp.start();
		this.finish();
		overridePendingTransition(R.anim.leftin, R.anim.leftout);
	}

}
