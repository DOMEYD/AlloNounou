package fr.iut.allonounou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SearchNannyActivity extends Activity {
	
	private String nannyName;
	private double[] location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		// RETRIEVE passed datas
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		location = b.getDoubleArray(MainActivity.EXTRA_LOCATION);
		nannyName = intent.getStringExtra(MainActivity.EXTRA_NANNY_NAME);
	}
}
