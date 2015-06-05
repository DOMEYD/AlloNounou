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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends Activity {
	
	private String idNanny;
	
	DBNanny2 myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// RETRIEVE passed datas
		Intent intent = getIntent();
		
		idNanny = intent.getStringExtra(SearchNannyActivity.EXTRA_ID);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);
		
		openDB();
		recupData();
		
		Button btn_contact = (Button) findViewById(R.id.contact_profil);
		
		// si clique sur Dmande de contact --> ouvre ContactNanny
		btn_contact.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent openContactNanny = new Intent(ProfilActivity.this, ContactNanny.class);
				startActivity(openContactNanny);
			}
		});
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
	
	//recuperation des information
	private void recupData() {
		long idDB = 0 ;
		String name ="";
		String freeplace="";
		String adresse ="";
		String prix="";
		String workplace="";
		int favori =0;
		
		Cursor cursor = myDB.getRow(DBNanny2.KEY_ROWID ,idNanny);
		if (cursor.moveToFirst()) {
			idDB = cursor.getLong(DBNanny2.COL_ROWID);
			name = cursor.getString(DBNanny2.COL_NAME);
			freeplace  = cursor.getString(DBNanny2.COL_FREEPLACE);
			adresse  = cursor.getString(DBNanny2.COL_ADRESSE);
			prix = cursor.getString(DBNanny2.COL_PRIX);
			workplace = cursor.getString(DBNanny2.COL_WORKPLACE);
		}
		cursor.close();
		
		TextView name_profil = (TextView)findViewById(R.id.name_profil);
		TextView freeplace_profil = (TextView)findViewById(R.id.capacity_profil);
		TextView adresse_profil = (TextView)findViewById(R.id.adress_profil);
		TextView prix_profil = (TextView)findViewById(R.id.tarif_profil);
		TextView workplace_profil = (TextView)findViewById(R.id.workplace_profil);
		
		name_profil.setText(name);
		freeplace_profil.setText(freeplace);
		adresse_profil.setText(adresse);
		prix_profil.setText(prix);
		workplace_profil.setText(workplace);
	}
		
	}
