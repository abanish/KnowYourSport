package com.testyourself.knowyoursport;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;

public class Answer extends Activity {
	// string to get each letter from database
	String[] first = new String[12];
	private final String PREFS_NAME = "football";
	String[] getAnswered = new String[200];
	Boolean answered;
	private int p = 0;
	private int q;
	private int k = 0;
	private int y = 0;
	public int category = 1;
	private boolean hasLife = false;
	MediaPlayer mp;
	MediaPlayer mp2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_answer);
		// create music
		mp = MediaPlayer.create(this, R.raw.answer_sound);
		mp2 = MediaPlayer.create(this, R.raw.button);
		// get the game layout
		final RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.example1);
		// get the button layout to create dynamic button
		RelativeLayout buttonlayout = (RelativeLayout) findViewById(R.id.example3);
		// getting the question number from the question class.
		Bundle bundle = getIntent().getExtras();
		q = bundle.getInt("from");
		// getting the random letters from the answer
		first = bundle.getStringArray("first");
		// size of the answer
		k = bundle.getInt("k");
		// creating a view object for each letter in the database
		final MyView1 mv1 = new MyView1(this, first[0]);
		final MyView1 mv2 = new MyView1(this, first[1]);
		final MyView1 mv3 = new MyView1(this, first[2]);
		final MyView1 mv4 = new MyView1(this, first[3]);
		final MyView1 mv5 = new MyView1(this, first[4]);
		final MyView1 mv6 = new MyView1(this, first[5]);
		final MyView1 mv7 = new MyView1(this, first[6]);
		final MyView1 mv8 = new MyView1(this, first[7]);
		final MyView1 mv9 = new MyView1(this, first[8]);
		final MyView1 mv10 = new MyView1(this, first[9]);
		// adding first view to the screen
		relativelayout.addView(mv1);

		// starting position for the dynamically added buttons.
		int currentX = 0;
		int currentY = 0;

		// adding required buttons dynamically to the screen.
		for (int i = 0; i < (k - 2); i++) {
			// getting the buttons and setting some parameters
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			Button btn = new Button(this);
			btn.setId(i);
			final int id_ = btn.getId();
			btn.setText("" + (id_ + 1));
			btn.setBackgroundColor(Color.BLACK);
			btn.setTextColor(Color.parseColor("#999999"));
			btn.setTextSize(25);
			params.setMargins(currentX, currentY, 0, 0);
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int screen = (metrics.widthPixels) / (k - 2);
			params.width = screen;
			params.height = (metrics.heightPixels) / 9;
			btn.setLayoutParams(params);
			buttonlayout.addView(btn, params);

			// finding buttons separately
			final Button button = (Button) findViewById(id_);
			// setting what happens when clicking the buttons
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					mp.start();
					System.gc();
					y++;
					String[] second = new String[8];
					Bundle bundle = getIntent().getExtras();
					second = bundle.getStringArray("second");
					// checking if the letter falling on the screen and the
					// letter that should be on the button are same.
					if (first[p].equals(second[id_])) {
						// changing the text on the button to the letter
						// falling.
						// setting the text on the button as falling letter
						button.setText("" + second[id_].toUpperCase());
						button.setTextColor(Color.parseColor("#FFFFFF"));
						// removing the old view and creating a view for the new
						// falling letter on the screen
						if (y == (k - 2)) {
							relativelayout.removeAllViews();
							// the answer is correct, we go to winner function
							winner();
						}
						// switch to find exact view for the button in database
						switch (p) {
						case 0:
							relativelayout.removeView(mv1);
							p++;
							relativelayout.addView(mv2);
							break;
						case 1:
							relativelayout.removeView(mv2);
							p++;
							relativelayout.addView(mv3);
							break;
						case 2:
							relativelayout.removeView(mv3);
							p++;
							relativelayout.addView(mv4);
							break;
						case 3:
							relativelayout.removeView(mv4);
							p++;
							relativelayout.addView(mv5);
							break;
						case 4:
							relativelayout.removeView(mv5);
							p++;
							relativelayout.addView(mv6);
							break;
						case 5:
							relativelayout.removeView(mv6);
							p++;
							relativelayout.addView(mv7);
							break;
						case 6:
							relativelayout.removeView(mv7);
							p++;
							relativelayout.addView(mv8);
							break;
						case 7:
							relativelayout.removeView(mv8);
							p++;
							relativelayout.addView(mv9);
							break;
						case 8:
							relativelayout.removeView(mv9);
							p++;
							relativelayout.addView(mv10);
							break;
						}
						if (y == (k - 2)) {

							relativelayout.removeAllViews();
							winner();
						}
					} else {
						// button pressed doesn't match the position of the
						// letter
						relativelayout.removeAllViews();
						losser();
					}
				}

				// correct answer goes to correct class
				private void winner() {
					// for onStop, to check if onStop is due to activity change
					// or something else
					hasLife = true;
					mp2.stop();
					final RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.example1);
					relativelayout.removeAllViewsInLayout();
					// get our SharedPreferences object and create an editor for
					// it
					SharedPreferences prefs = getSharedPreferences(PREFS_NAME,
							MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					Bundle bundle = getIntent().getExtras();
					q = bundle.getInt("from");

					final int maxUnlockedLevel = prefs.getInt(
							"maxUnlockedLevel", 1);

					final Boolean answer = prefs.getBoolean("getAnswered_" + q,
							false);
					if (!answer) {
						editor.putInt("maxUnlockedLevel", maxUnlockedLevel + 2);
						editor.commit();
					}
					answered = true;
					editor.putBoolean("getAnswered_" + q, answered);
					editor.commit();
					if (q == 200) {
						Intent intent = new Intent(Answer.this,
								Congratulation.class).putExtra("category",
								category);
						startActivity(intent);

					} else {
						Intent intent = new Intent(Answer.this, Correct.class);
						Bundle extras = new Bundle();
						extras.putInt("category", 1);
						extras.putInt("from", q);
						intent.putExtras(extras);
						startActivity(intent);
						overridePendingTransition(R.anim.rightin,
								R.anim.rightout);
					}
				}

			}

			);
			currentX = currentX + (screen - 5);
		}

		final ImageButton imgbtn1 = (ImageButton) findViewById(R.id.imgbtn);
		imgbtn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				mp.start();
				RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.example1);
				String[] extra = new String[3];
				Bundle bundle = getIntent().getExtras();
				extra = bundle.getStringArray("extra");

				if (first[p].equals(extra[0]) || first[p].equals(extra[1])
						|| first[p].equals("b")) {
					switch (p) {
					case 0:
						relativelayout.removeView(mv1);
						p++;
						relativelayout.addView(mv2);
						break;
					case 1:
						relativelayout.removeView(mv2);
						p++;
						relativelayout.addView(mv3);
						break;
					case 2:
						relativelayout.removeView(mv3);
						p++;
						relativelayout.addView(mv4);
						break;
					case 3:
						relativelayout.removeView(mv4);
						p++;
						relativelayout.addView(mv5);
						break;
					case 4:
						relativelayout.removeView(mv5);
						p++;
						relativelayout.addView(mv6);
						break;
					case 5:
						relativelayout.removeView(mv6);
						p++;
						relativelayout.addView(mv7);
						break;
					case 6:
						relativelayout.removeView(mv7);
						p++;
						relativelayout.addView(mv8);
						break;
					case 7:
						relativelayout.removeView(mv8);
						p++;
						relativelayout.addView(mv9);
						break;
					case 8:
						relativelayout.removeView(mv9);
						p++;
						relativelayout.addView(mv10);
						break;

					}
				} else {
					relativelayout.removeAllViews();
					losser();

				}

			}

		}

		);

	}

	private void losser() {
		hasLife = true;
		final RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.example1);
		relativelayout.removeAllViewsInLayout();
		mp2.stop();
		Bundle bundle = getIntent().getExtras();
		q = bundle.getInt("from");
		Intent intent = new Intent(Answer.this, GameOver.class);
		Bundle extras = new Bundle();
		extras.putInt("category", 1);
		extras.putInt("from", q);
		extras.putString("database", "football");
		intent.putExtras(extras);

		startActivity(intent);
		overridePendingTransition(R.anim.rightin, R.anim.rightout);

	}

	@Override
	protected void onPause() {
		mp2.stop();
		if (!hasLife) {
			this.finish();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mp = MediaPlayer.create(this, R.raw.button);
		mp2 = MediaPlayer.create(this, R.raw.answer_sound);
		mp2.setLooping(true);
		mp2.start();

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
		if (mp2 != null) {
			if (mp2.isPlaying()) {
				mp2.stop();
			}
			mp2.release();
			mp2 = null;
		}

	}

	@Override
	public void onBackPressed() {
		mp.start();
		mp2.stop();
		Bundle bundle = getIntent().getExtras();
		category = bundle.getInt("category");

		Intent i = new Intent(Answer.this, Buttons.class);
		startActivity(i);
		overridePendingTransition(R.anim.leftin, R.anim.leftout);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		updateSizeInfo1();

	}

	private int updateSizeInfo1() {
		RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.example1);
		int h = relativelayout.getHeight();
		return (h);
	}

	class MyView1 extends View {
		Bitmap cloud;
		private int x1 = 0, y1 = 0, m, n, q1;
		Paint paint;
		Canvas canvas;
		char ch;

		// creating views for the letters
		public MyView1(Context context, String first1) {
			super(context);
			if (!mp2.isLooping())
				mp2.start();
			if (cloud != null) {
				cloud.recycle();
				cloud = null;
				System.gc();
			}

			if (first1.equals("a")) {
				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.a);
			}
			if (first1.equals("b")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.b);
			}
			if (first1.equals("c")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.c);
			}
			if (first1.equals("d")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.d);
			}
			if (first1.equals("e")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.e);
			}
			if (first1.equals("f")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.f);
			}
			if (first1.equals("g")) {
				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.g);
			}
			if (first1.equals("h")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.h);
			}
			if (first1.equals("i")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.i);
			}
			if (first1.equals("j")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.j);
			}
			if (first1.equals("k")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.k);
			}
			if (first1.equals("l")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.l);
			}
			if (first1.equals("m")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.m);
			}
			if (first1.equals("n")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.n);
			}
			if (first1.equals("o")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.o);
			}
			if (first1.equals("p")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.p);
			}
			if (first1.equals("q")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.q);
			}
			if (first1.equals("r")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.r);
			}
			if (first1.equals("s")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.s);
			}
			if (first1.equals("t")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.t);
			}
			if (first1.equals("u")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.u);
			}
			if (first1.equals("v")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.v);
			}
			if (first1.equals("w")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.w);
			}
			if (first1.equals("x")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.x);
			}
			if (first1.equals("y")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.y);
			}
			if (first1.equals("z")) {

				cloud = BitmapFactory.decodeResource(getResources(),
						R.drawable.z);
			}

		}

		// making letters fall
		@Override
		protected void onDraw(Canvas canvas) {

			super.onDraw(canvas);

			x1 = canvas.getWidth();
			canvas.drawBitmap(cloud, ((2 * x1) / 5), y1, paint);
			m = updateSizeInfo1();
			n = cloud.getHeight();
			q1 = m - n;
			// if the falling letter is on the main screen
			if (y1 < q1) {
				y1 = y1 + 4;
			}
			// if the falling letter touches the bottom of the main screen.
			else if (y1 > q1) {
				y1 = q1;
				losser();

			}

			invalidate();
		}
	}

}
