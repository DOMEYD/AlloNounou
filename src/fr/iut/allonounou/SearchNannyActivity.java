package fr.iut.allonounou;

import java.util.List;

import android.widget.AdapterView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class SearchNannyActivity extends Activity {
	public final static String EXTRA_ID = "fr.iut.allonounou.ID";
	
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
		
		openDB();
	
		//initiate DATABASE
		ClearAll();
		InitiateBD();
		
		populateListViewFromDB();
		// registerListClickCallback();
		
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
		// nom et prénom,    nb de place dipo,    indic adress,     longitude,    latitude,    mail,    prix,    workplace
		myDB.insertRow("Jacqueline TUILARD" ,1, "quartier Blossière",0,0,"t-benjamin@hotmail.fr","0662685281",12,"appartement");
		myDB.insertRow("Maelle LEBON" ,3, "près de la gare",0,0,"t-benjamin@hotmail.fr","0662685281",8,"appartement");
		myDB.insertRow("Lilianne MARECHAL" ,2, "bord de Loire",0,0,"t-benjamin@hotmail.fr","0662685281",10,"maison");
		myDB.insertRow("Catherine DUPONT" ,0, "Parc des Expositions",0,0,"t-benjamin@hotmail.fr","0662685281",11,"appartement");
		myDB.insertRow("Joelle MARTIN" ,0, "près de la gare",0,0,"t-benjamin@hotmail.fr","0662685281",7,"maison");
		myDB.insertRow("Brigitte LEPONDEL" ,1, "près de la gare",0,0,"t-benjamin@hotmail.fr","0662685281",14,"maison");
		myDB.insertRow("Maelle COURVA" ,1, "quartier Blossière",0,0,"t-benjamin@hotmail.fr","0662685281",20,"appartement");
		myDB.insertRow("Maelle SILVA" ,2, "bord de Loire",0,0,"t-benjamin@hotmail.fr","0662685281",15,"appartement");
		myDB.insertRow("Diana MARTIN" ,4, "près de la gare",0,0,"t-benjamin@hotmail.fr","0662685281",18,"maison");
		myDB.insertRow("Diana GRATADE" ,4, "Orléans Sud",0,0,"t-benjamin@hotmail.fr","0662685281",9,"appartement");
		myDB.insertRow("Benjamin TUILARD" ,2, "Orléans Sud",0,0,"t-benjamin@hotmail.fr","0662685281",10,"appartement");
		myDB.insertRow("Dimitri DOMEY" ,1, "Orléans Sud",0,0,"t-benjamin@hotmail.fr","0662685281",45,"maison");
		myDB.insertRow("Jean-Yves NUBRET" ,6, "Orléans Sud",0,0,"t-benjamin@hotmail.fr","0662685281",1,"maison");
		
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
		myList.setOnItemClickListener(profilClickListener);
		myList.setAdapter(myCursorAdapter);

	}
	
		public OnItemClickListener profilClickListener = new OnItemClickListener() {
		 @Override
			public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3)
			{			
			 searchNanny(arg3);
				
			}
	 };	
	
	
		//détection du clique sur un item
		private void registerListClickCallback() {
			ListView myList = (ListView) findViewById(R.id.listViewNanny);
			myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View viewClicked, 
						int position, long idInDB) {

					//Lancement de l'activity profil en passant en paramètre l'id
					//updateItemForId(idInDB);
					searchNanny(idInDB);
				}
			});
		}
		
		
		public void searchNanny(long idInDB) {
			// GET intent for search nanny
			Intent intent = new Intent(this, ProfilActivity.class);
			
			intent.putExtra(EXTRA_ID, idInDB+"");
			
			// LAUNCH
		    startActivity(intent);
		}
		
		
}
