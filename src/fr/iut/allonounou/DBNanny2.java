package fr.iut.allonounou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

public class DBNanny2{

	private static final String TAG = "DBNanny2";

	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	
	 //clé de la base de donnée
	public static final String KEY_NAME = "name";
	public static final String KEY_FREEPLACE = "freeplace";
	public static final String KEY_FREECAT1 = "freeCat1";
	public static final String KEY_FREECAT2 = "freeCat2";
	public static final String KEY_FREECAT3 = "freeCat3";
	public static final String KEY_ADRESSE = "adresse";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_MAIL = "mail";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_PRIX = "prix";
	public static final String KEY_WORD = "word";
	
	public static final String KEY_REPAS = "repas";
	public static final String KEY_GOUTER = "gouter";
	public static final String KEY_CHAT = "chat";
	public static final String KEY_CHIEN = "chien";
	public static final String KEY_NUIT = "nuit";
	public static final String KEY_JEUX = "jeux";
	public static final String KEY_SECOURS = "secours";
	public static final String KEY_PROMENADE = "promenade";
	public static final String KEY_ATELIER = "atelier";
	
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_NAME = 1;
	public static final int COL_FREEPLACE = 2;
	public static final int COL_FREECAT1 = 3;
	public static final int COL_FREECAT2 = 4;
	public static final int COL_FREECAT3 = 5;
	public static final int COL_ADRESSE = 6;
	public static final int COL_LONGITUDE = 7;
	public static final int COL_LATITUDE = 8;
	public static final int COL_MAIL = 9;
	public static final int COL_PHONE = 10;
	public static final int COL_PRIX = 11;
	public static final int COL_WORD = 12;
	public static final int COL_REPAS  = 13;
	public static final int COL_GOUTER = 14;
	public static final int COL_CHAT = 15;
	public static final int COL_CHIEN = 16;
	public static final int COL_NUIT = 17;
	public static final int COL_JEUX = 18;
	public static final int COL_SECOURS = 19;
	public static final int COL_PROMENADE = 20;
	public static final int COL_ATELIER = 21;
	
	public static final String[] ALL_KEYS = new String[] 
			{KEY_ROWID, KEY_NAME,KEY_FREEPLACE, 
			KEY_FREECAT1, KEY_FREECAT2, KEY_FREECAT3, 
			KEY_ADRESSE, KEY_LONGITUDE, KEY_LATITUDE, 
			KEY_MAIL,KEY_PHONE, KEY_PRIX, 
			KEY_WORD, 
			KEY_REPAS,KEY_GOUTER, KEY_CHAT, KEY_CHIEN, 
			KEY_NUIT, KEY_JEUX, KEY_SECOURS , KEY_PROMENADE , KEY_ATELIER};
	
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE = "mainTable";

	public static final int DATABASE_VERSION = 2;	
    
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_NAME + " text not null, "
			+ KEY_FREEPLACE + " text not null, "
			+ KEY_FREECAT1 + " integer not null, "
			+ KEY_FREECAT2 + " integer not null, "
			+ KEY_FREECAT3 + " integer not null, "
			+ KEY_ADRESSE + " text not null, "
			+ KEY_LONGITUDE + " integer not null, "
			+ KEY_LATITUDE + " integer not null, "
			+ KEY_MAIL + " text not null, "
			+ KEY_PHONE + " text not null, "
			+ KEY_PRIX + " integer not null, "
			+ KEY_WORD + " text not null, "
			+ KEY_REPAS + " integer not null, "
			+ KEY_GOUTER + " integer not null, "
			+ KEY_CHAT + " integer not null, "
			+ KEY_CHIEN + " integer not null, "
			+ KEY_NUIT + " integer not null, "
			+ KEY_JEUX + " integer not null, "
			+ KEY_SECOURS + " integer not null, "
			+ KEY_PROMENADE + " integer not null, "
			+ KEY_ATELIER + " integer not null "
			+ ");";
	
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

    public DBNanny2(Context ctx) {
    	this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
    }
    
	// Open the database connection.
	public DBNanny2 open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
		
	  /**
     *Ajout d'un nouveau profil
     */
	public long insertRow(String name, int freecat1,int freecat2,int freecat3, 
							String adresse, int longitude, int latitude, 
							String mail,String phone, int prix, String word,
							int repas, int gouter, int chat, int chien, int nuit, 
							int jeux, int secours, int promenade, int atelier) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_FREEPLACE, freecat1+freecat2+freecat3 +" place(s) disponible(s)");
		initialValues.put(KEY_FREECAT1, freecat1);
		initialValues.put(KEY_FREECAT2, freecat2);
		initialValues.put(KEY_FREECAT3, freecat3);
		initialValues.put(KEY_ADRESSE, adresse);
		initialValues.put(KEY_LONGITUDE, longitude);
		initialValues.put(KEY_LATITUDE, latitude);
		initialValues.put(KEY_MAIL, mail);
		initialValues.put(KEY_PHONE, phone);
		initialValues.put(KEY_PRIX, prix);
		initialValues.put(KEY_WORD, word);
		initialValues.put(KEY_REPAS,repas);
		initialValues.put(KEY_GOUTER, gouter);
		initialValues.put(KEY_CHAT, chat);
		initialValues.put(KEY_CHIEN, chien);
		initialValues.put(KEY_NUIT,nuit);
		initialValues.put(KEY_JEUX, jeux);
		initialValues.put(KEY_SECOURS , secours);
		initialValues.put(KEY_PROMENADE , promenade);
		initialValues.put(KEY_ATELIER, atelier);
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	  /**
     *Suppression d'un profil
     */
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	  /**
     *Suppresion de tous les profils
     */
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	  /**
     *Récupération de l'ensemble des profils et de leurs données
     */
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	
	public Cursor getRowSearching(String name) {
		String where = KEY_NAME + " LIKE '%"+name+"%'";
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	

	  /**
     *Récupération des données d'un profil spécifique
     */
	// Get a specific row (by rowId)
	public Cursor getRow(String key, String condition) {
		String where = key + "=" + condition;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
		
	  /**
     *Changement des données relatif à un profil
     */
		public boolean updateRow(long rowId, String name, 
				int freecat1,int freecat2,int freecat3, 
				String adresse, int longitude, int latitude, 
				String mail, String phone,int prix, String word,
				int repas, int gouter, int chat, int chien, int nuit, 
				int jeux, int secours, int promenade, int atelier) {
			
			String where = KEY_ROWID + "=" + rowId;
			ContentValues newValues = new ContentValues();
			newValues.put(KEY_NAME, name);
			newValues.put(KEY_FREEPLACE, freecat1+freecat2+freecat3+" place(s) disponible(s)");
			newValues.put(KEY_FREECAT1, freecat1);
			newValues.put(KEY_FREECAT2, freecat2);
			newValues.put(KEY_FREECAT3, freecat3);
			newValues.put(KEY_ADRESSE, adresse);
			newValues.put(KEY_LONGITUDE, longitude);
			newValues.put(KEY_LATITUDE, latitude);
			newValues.put(KEY_MAIL, mail);
			newValues.put(KEY_PHONE, phone);
			newValues.put(KEY_PRIX, prix);
			newValues.put(KEY_WORD, word);
			newValues.put(KEY_REPAS,repas);
			newValues.put(KEY_GOUTER, gouter);
			newValues.put(KEY_CHAT, chat);
			newValues.put(KEY_CHIEN, chien);
			newValues.put(KEY_NUIT,nuit);
			newValues.put(KEY_JEUX, jeux);
			newValues.put(KEY_SECOURS , secours);
			newValues.put(KEY_PROMENADE , promenade);
			newValues.put(KEY_ATELIER, atelier);
			// Insert it into the database.
			return db.update(DATABASE_TABLE, newValues, where, null) != 0;
		}
	
	
			/**
		 * Private class which handles database creation and upgrading.
		 * Used to handle low-level database access.
		 */
		private static class DatabaseHelper extends SQLiteOpenHelper
		{
			DatabaseHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}

			@Override
			public void onCreate(SQLiteDatabase _db) {
				_db.execSQL(DATABASE_CREATE_SQL);			
			}

			@Override
			public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
				Log.w(TAG, "Upgrading application's database from version " + oldVersion
						+ " to " + newVersion + ", which will destroy all old data!");
				
				// Destroy old database:
				_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
				
				// Recreate new database:
				onCreate(_db);
			}
		}
	}

