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
	public static final String KEY_ADRESSE = "adresse";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_MAIL = "mail";
	public static final String KEY_PRIX = "prix";
	public static final String KEY_WORKPLACE = "workplace";
	public static final String KEY_FAVORI = "favori";
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_NAME = 1;
	public static final int COL_FREEPLACE = 2;
	public static final int COL_ADRESSE = 3;
	public static final int COL_LONGITUDE = 4;
	public static final int COL_LATITUDE = 5;
	public static final int COL_MAIL = 6;
	public static final int COL_PRIX = 7;
	public static final int COL_WORKPLACE = 8;
	public static final int COL_FAVORI = 9;
	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_FREEPLACE, KEY_ADRESSE, KEY_LONGITUDE, KEY_LATITUDE, KEY_MAIL, KEY_PRIX, KEY_WORKPLACE, KEY_FAVORI};
	
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE = "mainTable";

	public static final int DATABASE_VERSION = 2;	
    
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_NAME + " text not null, "
			+ KEY_FREEPLACE + " text not null, "
			+ KEY_ADRESSE + " text not null, "
			+ KEY_LONGITUDE + " integer not null, "
			+ KEY_LATITUDE + " integer not null, "
			+ KEY_MAIL + " text not null, "
			+ KEY_PRIX + " text not null, "
			+ KEY_WORKPLACE + " text not null, "
			+ KEY_FAVORI + " integer not null"
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
	
		
	public long insertRow(String name, int freeplace, String adresse, int longitude, int latitude, String mail, int prix, String workplace, int favori) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_FREEPLACE, freeplace+" place(s) dispo(s)");
		initialValues.put(KEY_ADRESSE, adresse);
		initialValues.put(KEY_LONGITUDE, longitude);
		initialValues.put(KEY_LATITUDE, latitude);
		initialValues.put(KEY_MAIL, mail);
		initialValues.put(KEY_PRIX, prix +" €/heure");
		initialValues.put(KEY_WORKPLACE, workplace);
		initialValues.put(KEY_FAVORI, favori);
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
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
	
	
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

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
	
		
	// Change an existing row to be equal to new data.
		public boolean updateRow(long rowId, String name, String freeplace, String adresse, int longitude, int latitude, String mail, int prix, String workplace, int favori) {
			String where = KEY_ROWID + "=" + rowId;
			ContentValues newValues = new ContentValues();
			newValues.put(KEY_NAME, name);
			newValues.put(KEY_FREEPLACE, freeplace+" place(s) dispo(s)");
			newValues.put(KEY_ADRESSE, adresse);
			newValues.put(KEY_LONGITUDE, longitude);
			newValues.put(KEY_LATITUDE, latitude);
			newValues.put(KEY_MAIL, mail);
			newValues.put(KEY_PRIX, prix +" €/heure");
			newValues.put(KEY_WORKPLACE, workplace);
			newValues.put(KEY_FAVORI, favori);
			// Insert it into the database.
			return db.update(DATABASE_TABLE, newValues, where, null) != 0;
		}
	
	
	

    /**
     * Insertion d'une chaîne dans la table.
     */
   /* public void insertValue(String value) {
		// La chaîne n'est pas directement ajoutée dans la base.
		// Il faut passer par la création d'une structure intermédiaire ContenValues.
		ContentValues content = new ContentValues();
		// Insertion de la chaîne dans l'instance de ContentValues.
		content.put("value", value);

		// Insertion dans la base de l'instance de ContentValues contenant la chapine.
		db.insert(DB_TABLE_NANNY, null, content);
    }*/

    /**
     * Récupération des chaînes de la table.
     */
  /*  public List<String> getValues() {
		List<String> list = new ArrayList<String>();
		String[] columns = {"value"};
		// Exécution de la requête pour obtenir les chaînes et récupération d'un curseur sur ces données.
		Cursor cursor = db.query(DB_TABLE_NANNY, columns, null, null, null, null, null);
		// Curseur placé en début des chaînes récupérées.
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// Récupération d'une chaîne et insertion dans une liste.
			list.add(cursor.getString(0));
			// Passage à l'entrée suivante.
			cursor.moveToNext();
		}
		// Fermeture du curseur.
		cursor.close();

		return list;
    }*/
    
    
   /* public void insertPersonnePersonne p(){
		ContentValues content = new ContentValues();
		content.put"prenom"Mp.getPrenom()");
		content.put"nom"Mp.getNom()")
		;
		db.insert("personne",null,content);
	}*/
    
    
   /* public List<String> getPersonne() {
		List<String> list = new ArrayList<String>();
		String[] columns = {"prenom","nom"};
		// Exécution de la requête pour obtenir les chaînes et récupération d'un curseur sur ces données.
		Cursor cursor = db.query(DB_TABLE_NANNY, columns, null, null, null, null, null);
		// Curseur placé en début des chaînes récupérées.
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// Récupération d'une chaîne et insertion dans une liste.
			list.add(cursor.getString(0));
			// Passage à l'entrée suivante.
			cursor.moveToNext();
		}
		// Fermeture du curseur.
		cursor.close();

		return list;
    }*/
    
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

