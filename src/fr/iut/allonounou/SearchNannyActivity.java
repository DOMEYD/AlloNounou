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
	
	int[] imagesIDs = {
			R.drawable.ic_nurse_aquaman,
			R.drawable.ic_nurse_batman,
			R.drawable.ic_nurse_beast,
			R.drawable.ic_nurse_black,
			R.drawable.ic_nurse_captain,
			R.drawable.ic_nurse_flash,
			R.drawable.ic_nurse_green,
			R.drawable.ic_nurse_hulk,
			R.drawable.ic_nurse_iron,
			R.drawable.ic_nurse_nick,
			R.drawable.ic_nurse_professor,
			R.drawable.ic_nurse_rogue,
			R.drawable.ic_nurse_superman,
			R.drawable.ic_nurse_thor,
			R.drawable.ic_nurse_wolverine,
			R.drawable.ic_nurse_wonder
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// RETRIEVE passed datas
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		location = b.getDoubleArray(MainActivity.EXTRA_LOCATION);
		nannyName = intent.getStringExtra(MainActivity.EXTRA_NANNY_NAME);
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
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
		//int imageId = imagesIDs[nextImageIndex];
		//nextImageIndex = (nextImageIndex + 1) % imagesIDs.length;
		
		//  name, freecat1, freecat2, freecat3, 
		// adresse, longitude,  latitude, 
		// mail,  phone,prix, word,
		//repas,  gouter,  chat,  chien,  nuit, 
		// jeux,  secours,  promenade,  atelier
		myDB.insertRow("Jacqueline TUILARD" ,1,0,2, 
				"quartier Blossière",0,0,
				"t-benjamin@hotmail.fr","0662685281",12,
				"Bonjour, ceci est le mot de la nounou ",
				0,1,1,1,0,1,0,1,0);
		myDB.insertRow("Maelle LEBON" ,1,1,1, 
				"près de la gare",0,0,
				"t-benjamin@hotmail.fr","0662685281",8,
				"Bonjour, ceci est le mot de la nounou ",
				1,1,1,1,1,1,1,1,1);
		myDB.insertRow("Lilianne MARECHAL" ,2,0,0, 
				"bord de Loire",0,0,
				"t-benjamin@hotmail.fr","0662685281",10,
				"Bonjour, ceci est le mot de la nounou ",
				1,0,1,1,0,0,0,1,0);
		myDB.insertRow("Catherine DUPONT" ,0,0,0, 
				"Parc des Expositions",0,0,
				"t-benjamin@hotmail.fr","0662685281",11,
				"Bonjour, ceci est le mot de la nounou ",
				1,0,1,0,0,0,1,1,0);
		myDB.insertRow("Joelle MARTIN" ,0,1,0, 
				"près de la gare",0,0,
				"t-benjamin@hotmail.fr","0662685281",7,
				"Bonjour, ceci est le mot de la nounou ",
				0,0,0,0,0,0,0,0,0);
		myDB.insertRow("Brigitte LEPONDEL" ,1,0,1, 
				"près de la gare",0,0,
				"t-benjamin@hotmail.fr","0662685281",14,
				"Bonjour, ceci est le mot de la nounou ",
				0,1,0,1,0,1,0,0,1);
		myDB.insertRow("Maelle COURVA" ,0,0,0,
				"quartier Blossière",0,0,
				"t-benjamin@hotmail.fr","0662685281",20,
				"Bonjour, ceci est le mot de la nounou ",
				1,1,1,1,0,1,0,0,1);
		myDB.insertRow("Maelle SILVA" ,2, 2,0,
				"bord de Loire",0,0,
				"t-benjamin@hotmail.fr","0662685281",15,
				"Bonjour, ceci est le mot de la nounou ",
				1,0,1,1,1,0,0,1,0);
		myDB.insertRow("Diana MARTIN" ,4,0,0, 
				"près de la gare",0,0,
				"t-benjamin@hotmail.fr","0662685281",18,
				"Bonjour, ceci est le mot de la nounou ",
				0,1,0,1,1,1,0,1,0);
		myDB.insertRow("Diana GRATADE" ,1,0,0, 
				"Orléans Sud",0,0,
				"t-benjamin@hotmail.fr","0662685281",9,
				"Bonjour, ceci est le mot de la nounou ",
				1,0,1,0,1,0,1,0,1);
		myDB.insertRow("Benjamin TUILARD" ,0,2,0, 
				"Orléans Sud",0,0,
				"t-benjamin@hotmail.fr","0662685281",10,
				"Bonjour, ceci est le mot de la nounou ",
				1,1,0,0,1,1,0,0,1);
		myDB.insertRow("Dimitri DOMEY" ,0,0,0, 
				"Orléans Sud",0,0,
				"t-benjamin@hotmail.fr","0662685281",45,
				"Bonjour, ceci est le mot de la nounou ",
				1,1,1,0,0,0,1,1,1);
		myDB.insertRow("Jean-Yves NUBRET" ,6,0,0, 
				"Orléans Sud",0,0,
				"t-benjamin@hotmail.fr","0662685281",1,
				"Bonjour, ceci est le mot de la nounou ",
				1,0,0,0,1,0,0,0,1);
		
	}
	
	
	public void ClearAll() {
		myDB.deleteAll();
	}

	
	private void populateListViewFromDB() {
		Cursor cursor = myDB.getRowSearching(nannyName);
		// Allow activity to manage lifetime of the cursor.
		// DEPRECATED! Runs on the UI thread, OK for small/short queries.
		startManagingCursor(cursor);

		// Setup mapping from cursor to view fields:
		String[] fromFieldNames = new String[] 
				{DBNanny2.KEY_NAME,  DBNanny2.KEY_ADRESSE,  DBNanny2.KEY_FREEPLACE};
	
		int[] toViewIDs = new int[]
				{R.id.nameNanny,     R.id.adresseNanny,     R.id.placeNanny};

		
		// Create adapter to may columns of the DB onto elemesnt in the UI.
		SimpleCursorAdapter myCursorAdapter = 
				new SimpleCursorAdapter(
						this,		// Context
						R.layout.model_search,	// Row layout template
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
