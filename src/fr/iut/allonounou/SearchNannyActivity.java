package fr.iut.allonounou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SearchNannyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		Intent intent = getIntent();
		String location = intent.getStringExtra(MainActivity.EXTRA_LOCATION);
		String nannyName = intent.getStringExtra(MainActivity.EXTRA_NANNY_NAME);
	}
}
