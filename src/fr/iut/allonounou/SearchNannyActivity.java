package fr.iut.allonounou;

import java.util.List;

import android.widget.AdapterView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SearchNannyActivity extends Activity {
	
	private String nannyName;
	private double[] location;
	
	DBNanny2 myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// RETRIEVE passed datas
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		location = b.getDoubleArray(MainActivity.EXTRA_LOCATION);
		nannyName = intent.getStringExtra(MainActivity.EXTRA_NANNY_NAME);
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search2);
		
		/* Jean-Yves (Je veux pouvoir acceder à nannyPtofil mais comment ?)
		
		Button button = (Button) findViewById (R.id.button1);
		// si clique ouvre NannyProfil
		button.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent openNannyProfil = new Intent(SearchNannyActivity.this, NannyProfil.class);

				startActivity(openNannyProfil);
			}
		});
		*/
		openDB();
	
		//initiate DATABASE
		ClearAll();
		InitiateBD();
		
		populateListViewFromDB();
		
	
		//myDB.insertPersonne();
		//TextView textview = (TextView)findViewById(R.id.title);
		//TextView textview1 = (TextView)findViewById(R.id.artiste);

		// Récupération des chaînes dans la base de données.
		
		//List<String> list = myDB.getPersonne();

		/*String values = "";
		for(int i = 0 ; i < list.size() ;  ++i) {
			values += list.get(i) + " ";
		}*/

		// Concaténation des chaines pour affichage
		//textview.setText(list.get(0));
		//textview1.setText(list.get(1));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();	
		closeDB();
	}

	private void openDB() {
		myDB = new DBNanny2(this);
		myDB.open();
		
	}
	private void closeDB() {
		myDB.close();
	}
	
	/* 
	 * UI Button Callbacks
	 */
	public void InitiateBD() {
		// nom et prénom, nb de place dipo, indic adress, longitude, latitude, mail, prix, workplace, favori
		myDB.insertRow("Jacqueline TUILARD" ,1, "quartier Blossière",0,0,"t-benjamin@hotmail.fr",12,"appartement",1);
		myDB.insertRow("Maelle LEBON" ,3, "près de la gare",0,0,"t-benjamin@hotmail.fr",8,"appartement",0);
		myDB.insertRow("Lilianne MARECHAL" ,2, "bord de Loire",0,0,"t-benjamin@hotmail.fr",10,"maison",0);
		myDB.insertRow("Catherine DUPONT" ,0, "Parc des Expositions",0,0,"t-benjamin@hotmail.fr",11,"appartement",0);
		myDB.insertRow("Joelle MARTIN" ,0, "près de la gare",0,0,"t-benjamin@hotmail.fr",7,"maison",0);
		myDB.insertRow("Brigitte LEPONDEL" ,1, "près de la gare",0,0,"t-benjamin@hotmail.fr",14,"maison",0);
		myDB.insertRow("Maelle COURVA" ,1, "quartier Blossière",0,0,"t-benjamin@hotmail.fr",20,"appartement",0);
		myDB.insertRow("Maelle SILVA" ,2, "bord de Loire",0,0,"t-benjamin@hotmail.fr",15,"appartement",0);
		myDB.insertRow("Diana MARTIN" ,4, "près de la gare",0,0,"t-benjamin@hotmail.fr",18,"maison",1);
		
	}
	
	
	public void ClearAll() {
		myDB.deleteAll();
	}

	
	private void populateListViewFromDB() {

		Cursor cursor = myDB.getAllRows();

		// Allow activity to manage lifetime of the cursor.
		// DEPRECATED! Runs on the UI thread, OK for small/short queries.
		startManagingCursor(cursor);

		// Setup mapping from cursor to view fields:
		String[] fromFieldNames = new String[] 
				{DBNanny2.KEY_NAME,  DBNanny2.KEY_ADRESSE,  DBNanny2.KEY_FREEPLACE};
	
		int[] toViewIDs = new int[]
				{R.id.title,     R.id.duree,     R.id.artiste};

		
		// Create adapter to may columns of the DB onto elemesnt in the UI.
		SimpleCursorAdapter myCursorAdapter = 
				new SimpleCursorAdapter(
						this,		// Context
						R.layout.activity_search,	// Row layout template
						cursor,					// cursor (set of DB records to map)
						fromFieldNames,			// DB Column names
						toViewIDs				// View IDs to put information in
						);

		// Set the adapter for the list view
		ListView myList = (ListView) findViewById(R.id.listViewNanny);

		myList.setAdapter(myCursorAdapter);

	}
}
