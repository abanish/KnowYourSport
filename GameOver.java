package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class GameOver extends Activity {
	int q;
	MediaPlayer mp;

	private int category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		mp = MediaPlayer.create(this, R.raw.button);
	}

	public void playagain(View v) {
		mp.start();
		Bundle bundle = getIntent().getExtras();
		q = bundle.getInt("from");
		category = bundle.getInt("category");
		if (category == 1) {
			Intent i = new Intent(GameOver.this, Questions.class).putExtra(
					"from", q);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
		if (category == 2) {
			Intent i = new Intent(GameOver.this, Questions2.class).putExtra(
					"from", q);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}

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
		mp.start();
		Bundle bundle = getIntent().getExtras();
		q = bundle.getInt("from");
		category = bundle.getInt("category");

		if (category == 1) {
			Intent i = new Intent(GameOver.this, Buttons.class);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
		if (category == 2) {
			Intent i = new Intent(GameOver.this, Buttons2.class);
			startActivity(i);
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
	}
}
