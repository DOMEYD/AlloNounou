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
	
	
    private SQLiteDatabase db;                              // Base de donn�es

    DBNanny2(Context context) {
		// Appel au constructeur qui s'occupe de cr�er ou ouvrir la base.
		super(context, DB_NAME, null, 2);
		// R�cup�ration de la base de donn�es.
		db = getWritableDatabase();
    }

    /**
     * M�thode appel�e si la base n'existe pas.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + DB_TABLE_NAME+ " (_id integer primary key autoincrement, value text not null);");
		db.execSQL("create table " + DB_TABLE_PERSONNE+ " (_id integer primary key autoincrement,"+ KEY_LASTNAME +" text not null,"+ KEY_FIRSTNAME +" text not null);");
    }

    /**
     * M�thode pour passer d'une version de SQLite � une nouvelle version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

    }

    /**
     * Insertion d'une cha�ne dans la table.
     */
    public void insertValue(String value) {
		// La cha�ne n'est pas directement ajout�e dans la base.
		// Il faut passer par la cr�ation d'une structure interm�diaire ContenValues.
		ContentValues content = new ContentValues();
		// Insertion de la cha�ne dans l'instance de ContentValues.
		content.put("value", value);

		// Insertion dans la base de l'instance de ContentValues contenant la chapine.
		db.insert(DB_TABLE_NAME, null, content);
    }

    /**
     * R�cup�ration des cha�nes de la table.
     */
    public List<String> getValues() {
		List<String> list = new ArrayList<String>();
		String[] columns = {"value"};
		// Ex�cution de la requ�te pour obtenir les cha�nes et r�cup�ration d'un curseur sur ces donn�es.
		Cursor cursor = db.query(DB_TABLE_NAME, columns, null, null, null, null, null);
		// Curseur plac� en d�but des cha�nes r�cup�r�es.
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// R�cup�ration d'une cha�ne et insertion dans une liste.
			list.add(cursor.getString(0));
			// Passage � l'entr�e suivante.
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
		// Ex�cution de la requ�te pour obtenir les cha�nes et r�cup�ration d'un curseur sur ces donn�es.
		Cursor cursor = db.query(DB_TABLE_PERSONNE, columns, null, null, null, null, null);
		// Curseur plac� en d�but des cha�nes r�cup�r�es.
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// R�cup�ration d'une cha�ne et insertion dans une liste.
			list.add(cursor.getString(0));
			list.add(cursor.getString(1));
			// Passage � l'entr�e suivante.
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
