package fr.iut.allonounou;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;


public class DBNanny2 extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB";             // Nom de la base.
    public static final String DB_TABLE_NAME = "valeurs";  // Nom de la table.
	public static final String DB_TABLE_PERSONNE = "personne";  // Nom de la table.
	  
	public static final String KEY_FIRSTNAME = "nom";
	public static final String KEY_LASTNAME = "prenom";
	
	
    private SQLiteDatabase db;                              // Base de données

    DBNanny2(Context context) {
		// Appel au constructeur qui s'occupe de créer ou ouvrir la base.
		super(context, DB_NAME, null, 2);
		// Récupération de la base de données.
		db = getWritableDatabase();
    }

    /**
     * Méthode appelée si la base n'existe pas.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + DB_TABLE_NAME+ " (_id integer primary key autoincrement, value text not null);");
		db.execSQL("create table " + DB_TABLE_PERSONNE+ " (_id integer primary key autoincrement,"+ KEY_LASTNAME +" text not null,"+ KEY_FIRSTNAME +" text not null);");
    }

    /**
     * Méthode pour passer d'une version de SQLite à une nouvelle version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

    }

    /**
     * Insertion d'une chaîne dans la table.
     */
    public void insertValue(String value) {
		// La chaîne n'est pas directement ajoutée dans la base.
		// Il faut passer par la création d'une structure intermédiaire ContenValues.
		ContentValues content = new ContentValues();
		// Insertion de la chaîne dans l'instance de ContentValues.
		content.put("value", value);

		// Insertion dans la base de l'instance de ContentValues contenant la chapine.
		db.insert(DB_TABLE_NAME, null, content);
    }

    /**
     * Récupération des chaînes de la table.
     */
    public List<String> getValues() {
		List<String> list = new ArrayList<String>();
		String[] columns = {"value"};
		// Exécution de la requête pour obtenir les chaînes et récupération d'un curseur sur ces données.
		Cursor cursor = db.query(DB_TABLE_NAME, columns, null, null, null, null, null);
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
    }
    
    
    public void insertPersonne(){
		ContentValues content = new ContentValues();
		content.put(KEY_LASTNAME,"Jacqueline");
		content.put(KEY_FIRSTNAME,"TUILARD");
		db.insert("personne",null,content);
		content.put(KEY_LASTNAME,"Jacqueline2");
		content.put(KEY_FIRSTNAME,"TUILARD2");
		db.insert("personne",null,content);
	}
    
    
    public List<String> getPersonne() {
		List<String> list = new ArrayList<String>();
		String[] columns = {"prenom","nom"};
		// Exécution de la requête pour obtenir les chaînes et récupération d'un curseur sur ces données.
		Cursor cursor = db.query(DB_TABLE_PERSONNE, columns, null, null, null, null, null);
		// Curseur placé en début des chaînes récupérées.
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// Récupération d'une chaîne et insertion dans une liste.
			list.add(cursor.getString(0));
			list.add(cursor.getString(1));
			// Passage à l'entrée suivante.
			cursor.moveToNext();
		}
		// Fermeture du curseur.
		cursor.close();

		return list;
    }
    
 // Return all data in the database.
 	public Cursor getAllRows() {
 		String where = null;
 		List<String> list = new ArrayList<String>();
		String[] columns = {KEY_LASTNAME,KEY_FIRSTNAME};
 		Cursor c = 	db.query(true,DB_TABLE_PERSONNE, columns, null, null, null, null, null,null);
 		if (c != null) {
 			c.moveToFirst();
 		}
 		return c;
 	}

}
