package fr.iut.allonounou;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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
		
		dbopenhelper.insertPersonne();
		TextView textview = (TextView)findViewById(R.id.title);
		TextView textview1 = (TextView)findViewById(R.id.artiste);

		// Récupération des chaînes dans la base de données.
		
		List<String> list = dbopenhelper.getPersonne();

		/*String values = "";
		for(int i = 0 ; i < list.size() ;  ++i) {
			values += list.get(i) + " ";
		}*/

		// Concaténation des chaines pour affichage
		textview.setText(list.get(0));
		textview1.setText(list.get(1));
		
		//populateListViewFromDB();
	}
	
	private void populateListViewFromDB() {
		Cursor cursor = dbopenhelper.getAllRows();		
		
		startManagingCursor(cursor);
		
		String[] fromFieldNames = new String[] 
				{DBNanny2.KEY_LASTNAME, DBNanny2.KEY_FIRSTNAME};
		
		int[] toViewIDs = new int[]
				{R.id.title,            R.id.artiste};
		
		SimpleCursorAdapter myCursorAdapter = 
				new SimpleCursorAdapter(
						this,		// Context
						R.layout.activity_search,	// Row layout template
						cursor,					// cursor (set of DB records to map)
						fromFieldNames,			// DB Column names
						toViewIDs				// View IDs to put information in
						);
		
		/*ListView myList = (ListView) findViewById(R.id.listViewNanny);
		myList.setAdapter(myCursorAdapter);*/
	}
	
}
