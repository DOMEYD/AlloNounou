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
		String adresse ="";
		
		int freecat1=0;
		int freecat2=0;
		int freecat3=0;
		
		String word="";
		int prix=0;
		
		int repas=0;  int secours=0;  int nuit=0; 
		int gouter=0; int chat=0;  int promenade=0;
		int jeux=0;	  int chien=0; int atelier=0;
		
		
	
	
		
		
		Cursor cursor = myDB.getRow(DBNanny2.KEY_ROWID ,idNanny);
		if (cursor.moveToFirst()) {
			idDB = cursor.getLong(DBNanny2.COL_ROWID);
			name = cursor.getString(DBNanny2.COL_NAME);

			adresse  = cursor.getString(DBNanny2.COL_ADRESSE);
			prix = cursor.getInt(DBNanny2.COL_PRIX);
			word = cursor.getString(DBNanny2.COL_WORD);
			
				
			freecat1  = cursor.getInt(DBNanny2.COL_FREECAT1);
			freecat2  = cursor.getInt(DBNanny2.COL_FREECAT2);
			freecat3  = cursor.getInt(DBNanny2.COL_FREECAT3);
			repas  = cursor.getInt(DBNanny2.COL_REPAS);
			secours  = cursor.getInt(DBNanny2.COL_SECOURS);
			nuit  = cursor.getInt(DBNanny2.COL_NUIT);
 			gouter  = cursor.getInt(DBNanny2.COL_GOUTER);
 			chat  = cursor.getInt(DBNanny2.COL_CHAT);
 			promenade  = cursor.getInt(DBNanny2.COL_PROMENADE);
			jeux  = cursor.getInt(DBNanny2.COL_JEUX);
			chien  = cursor.getInt(DBNanny2.COL_CHIEN);
			atelier  = cursor.getInt(DBNanny2.COL_CHAT);

		}
		cursor.close();
		
		TextView name_profil = (TextView)findViewById(R.id.name_profil);
		TextView freecat1_profil = (TextView)findViewById(R.id.freecat1_profil);
		TextView freecat2_profil = (TextView)findViewById(R.id.freecat2_profil);
		TextView freecat3_profil = (TextView)findViewById(R.id.freecat3_profil);
		TextView adresse_profil = (TextView)findViewById(R.id.adress_profil);
		
		TextView prix_profil =(TextView)findViewById(R.id.prix_profil);
		TextView word_profil = (TextView)findViewById(R.id.word_profil);
	
		TextView repas_profil  =(TextView)findViewById(R.id.repas_profil);
		TextView secours_profil = (TextView)findViewById(R.id.secours_profil);
		TextView nuit_profil  =(TextView)findViewById(R.id.nuit_profil);
		TextView gouter_profil  = (TextView)findViewById(R.id.gouter_profil);
		TextView chat_profil  =(TextView)findViewById(R.id.chat_profil);
		TextView promenade_profil  = (TextView)findViewById(R.id.promenade_profil);
		TextView jeux_profil  = (TextView)findViewById(R.id.jeux_profil);
		TextView chien_profil  =(TextView)findViewById(R.id.chien_profil);
		TextView atelier_profil  = (TextView)findViewById(R.id.atelier_profil);
		
		name_profil.setText(name);
		adresse_profil.setText(adresse);
		
		freecat1_profil.setText(" 0-12 mois : " + freecat1);
		freecat2_profil.setText(" 12-24 mois : " +freecat2);
		freecat3_profil.setText(" +24 mois : " +freecat3);
				
		prix_profil.setText(prix+"�/heure environ");
		word_profil.setText(word);
			
		if(repas==1){repas_profil.setText("oui");}else{ repas_profil.setText("non");}
		if(repas==1){secours_profil.setText("oui");}else{ secours_profil.setText("non");}
		if(nuit==1){nuit_profil.setText("oui");}else{ nuit_profil.setText("non");}
		if(gouter==1){gouter_profil.setText("oui");}else{ gouter_profil.setText("non");}
		if(chat==1){chat_profil.setText("oui");}else{ chat_profil.setText("non");}
		if(promenade==1){promenade_profil.setText("oui");}else{ promenade_profil.setText("non");}
		if(jeux==1){jeux_profil.setText("oui");}else{ jeux_profil.setText("non");}
		if(chien==1){chien_profil.setText("oui");}else{ chien_profil.setText("non");}
		if(atelier==1){atelier_profil.setText("oui");}else{ atelier_profil.setText("non");}

	}
		
	}
