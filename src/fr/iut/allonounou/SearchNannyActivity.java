package fr.iut.allonounou;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchNannyActivity extends Activity {
	
	private String nannyName;
	private double[] location;
	private DBNanny2 dbopenhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		dbopenhelper = new DBNanny2(this);
		
		// RETRIEVE passed datas
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		location = b.getDoubleArray(MainActivity.EXTRA_LOCATION);
		nannyName = intent.getStringExtra(MainActivity.EXTRA_NANNY_NAME);
		
		dbopenhelper.insertValue("abcdef");
		
		TextView textview = (TextView)findViewById(R.id.title);
		TextView textview1 = (TextView)findViewById(R.id.artiste);

		// Récupération des chaînes dans la base de données.
		
		List<String> list = dbopenhelper.getValues();

		String values = "";
		for(int i = 0 ; i < list.size() ;  ++i) {
			values += list.get(i) + " ";
		}

		// Concaténation des chaines pour affichage
		textview.setText(list.get(0));
		textview1.setText(list.get(1));
	}
	
	
}
