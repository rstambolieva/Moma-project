package com.example.modernartui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SeekBar mSeekBar;
	private TextView view1, view2, view3, view4, view5;
	private TextView[] viewsArray;
	private static final String TAG = "MOMI_MainActivity";
	private static final String FRAGMENT_TAG = "Link";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get reference to the UI elements: seekbar and textviews
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		view1 = (TextView) findViewById(R.id.text1);
		view2 = (TextView) findViewById(R.id.text2);
		view3 = (TextView) findViewById(R.id.text3);
		view4 = (TextView) findViewById(R.id.text4);
		view5 = (TextView) findViewById(R.id.text5);
		viewsArray = new TextView[] { view1, view2, view3, view4, view5 };

		// set the rectangle view colors
		setDefaultColors();

		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int listenerProgress = 0;

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Log.d(TAG, "stop touch");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Log.d(TAG, "start touch");

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				listenerProgress = progress;
				for (TextView v : viewsArray) {
					ColorDrawable color = (ColorDrawable) v.getBackground();
					int colorCode = color.getColor();

					if (colorCode == Color.WHITE || colorCode == Color.GRAY
							|| colorCode == Color.LTGRAY
							|| colorCode == Color.DKGRAY) {
						// don't change color
					} else {
						changeColor(v, progress, seekBar.getMax());
					}

				}

			}
		});
	}

	// handle the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.more_info, menu);
		return true;
	}

	// handle "More Information buttons' behaviour on click"
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// If more info is clicked in the options menu open alert dialog
		if (id == R.id.more_info) {
			(new MoreInformationDialog()).show(getFragmentManager(),
					FRAGMENT_TAG);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// change color of the view per the progress seek bar by changing hue value
	// 0- 360
	public void changeColor(View view, int progress, int maxProgress) {
		float[] colorCodeHSV = new float[3];
		// generate only hue component in range [0, 360),
		// leaving saturation and brightness maximum possible
		ColorDrawable color = (ColorDrawable) view.getBackground();
		int colorCode = color.getColor();
		Color.colorToHSV(colorCode, colorCodeHSV);

		colorCodeHSV[0] = 360f * progress / maxProgress;
		view.setBackgroundColor(Color.HSVToColor(colorCodeHSV));
	}

	// set default colors to rectangle views
	private void setDefaultColors() {
		view1.setBackgroundColor(Color.BLUE);
		view2.setBackgroundColor(Color.RED);
		view3.setBackgroundColor(Color.YELLOW);
		view4.setBackgroundColor(Color.WHITE);
		view5.setBackgroundColor(Color.GREEN);
	}
}
