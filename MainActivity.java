package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	MediaPlayer mp;
	private final String PREFS_NAME = "sound";
	Boolean music;
	Boolean sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// set content view AFTER ABOVE sequence (to avoid crash)
		setContentView(R.layout.activity_main);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// get sound preference
		sound = getSoundPref();

		final Button button = (Button) findViewById(R.id.button1);
		if (sound == true) {
			button.setBackgroundResource(R.drawable.button_rounded);
			setSoundStream(false);
		} else if (sound == false) {
			button.setBackgroundResource(R.drawable.sound_button);
			setSoundStream(true);
		}
		// create button sound
		mp = MediaPlayer.create(this, R.raw.button);
	}

	// starting category class activity when the play button is clicked.
	public void play(View v) {
		mp.start();
		Intent i = new Intent(MainActivity.this, Category.class);
		startActivity(i);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
	}

	// starting the instructions class
	public void instructions(View v) {
		mp.start();
		Intent i = new Intent(MainActivity.this, Instructions.class);
		startActivity(i);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
	}

	// starting the more class
	public void about(View v) {
		Intent i = new Intent(MainActivity.this, About.class);
		startActivity(i);
		mp.start();
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
	}

	// set sound button to mute or unmute
	public void sound(View v) {
		Button button = (Button) v;
		SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

		sound = prefs.getBoolean("music", true);
		if (sound == true) {
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("music", false);
			editor.commit();
			button.setBackgroundResource(R.drawable.sound_button);
			setSoundStream(true);
		} else {
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("music", true);
			editor.commit();
			button.setBackgroundResource(R.drawable.button_rounded);
			setSoundStream(false);
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
		sound = getSoundPref();
		if (sound == false) {
			setSoundStream(false);
		} else {
			mp = MediaPlayer.create(this, R.raw.button);
			mp.start();
		}
		this.finish();
		overridePendingTransition(R.anim.leftin, R.anim.leftout);
	}

	// set audio stream
	public void setSoundStream(Boolean state) {
		AudioManager audioManager = (AudioManager) this
				.getSystemService(this.AUDIO_SERVICE);
		audioManager.setStreamMute(AudioManager.STREAM_MUSIC, state);

	}

	// get sound preference
	public boolean getSoundPref() {
		SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		boolean peace = prefs.getBoolean("music", true);
		return peace;
	}

}