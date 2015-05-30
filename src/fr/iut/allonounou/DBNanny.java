package fr.iut.allonounou;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBNanny extends SQLiteOpenHelper {
	public static final String KEY_NANNY_ID = "id";
	public static final String KEY_NANNY_FIRSTNAME = "first_name";
	public static final String KEY_NANNY_LASTNAME ="last_name";
	public static final String KEY_NANNY_STREET = "street";
	public static final String KEY_NANNY_CITY = "city";
	public static final String KEY_NANNY_CITYPC = "postal_code";
	public static final String KEY_NANNY_CAPACITANCE_TYPE1 = "type1";
	public static final String KEY_NANNY_CAPACITANCE_TYPE2 = "type2";
	public static final String KEY_NANNY_CAPACITANCE_TYPE3 = "type3";
	public static final String KEY_NANNY_PRICE = "price";
	public static final String KEY_NANNY_WORKPLACE = "workplace";
	public static final String KEY_NANNY_OTHER = "other";
	
	private static final String TAG = "DBNanny";
	private static final String DATABASE_NAME = "FindNannyDB";
	private static final String DATABASE_TABLE = "assignments";
	private static final int DATABASE_VERSION = 1;
	
	private static String DATABASE_CREATE=null;
	
   // private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
	private Context context;

	DBNanny(Context context) {
		// Appel au constructeur qui s'occupe de créer ou ouvrir la base.
		super(context, DATABASE_TABLE, null, 2);
		// Récupération de la base de données.
		db = getWritableDatabase();
    }
	@Override
	public void onCreate(SQLiteDatabase db) {
		DATABASE_CREATE = "create table if not exists assignments (id integer primary key autoincrement, "
			+ "first_name VARCHAR not null, last_name VARCHAR not null, "
			+ "street VARCHAR, city VARCHAR, postal_code INT, "
			+ "type1 INT not null, type2 INT not null, type3 INT not null "
			+ "price INT, workplace VARCHAR, other VARCHAR);";
		// TODO Auto-generated method stub
		long id = insertRecord("Paula","HENRIQUEZ","avenue des sports","WERVICK","59117","0","2","1","7","Maison","Jardin avec jeux en extérieur");        
        id = insertRecord("Marie","ANTOINETTE","rue des Poilus","OLIVET","45160","3","1","0","9"," "," ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	 //---insert a record into the database---
    public long insertRecord(String first_name, String last_name,
    		String street, String city, String postal_code,
    		String type1, String type2, String type3,
    		String price, String workplace, String other) 
    {
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_NANNY_FIRSTNAME, first_name);
    	initialValues.put(KEY_NANNY_LASTNAME, last_name);
    	initialValues.put(KEY_NANNY_STREET, street);
    	initialValues.put(KEY_NANNY_CITY, city);
    	initialValues.put(KEY_NANNY_CITYPC, postal_code);
    	initialValues.put(KEY_NANNY_CAPACITANCE_TYPE1, type1);
    	initialValues.put(KEY_NANNY_CAPACITANCE_TYPE2, type2);
    	initialValues.put(KEY_NANNY_CAPACITANCE_TYPE3, type3);
    	initialValues.put(KEY_NANNY_PRICE, price);
    	initialValues.put(KEY_NANNY_WORKPLACE, workplace);
    	initialValues.put(KEY_NANNY_OTHER, other);
    	return db.insert(DATABASE_TABLE, null, initialValues);
    }
    
    public List<String> getAllRecords() 
    {
    	List<String> list = new ArrayList<String>();
    	String[] columns = {"nom","ville"};
		Cursor cursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
       /* Cursor cursor = db.query(DATABASE_TABLE, new String[] {
        		KEY_NANNY_ID, 
        		KEY_NANNY_FIRSTNAME, KEY_NANNY_LASTNAME,
        		KEY_NANNY_STREET, KEY_NANNY_CITY, KEY_NANNY_CITYPC,
        		KEY_NANNY_CAPACITANCE_TYPE1, KEY_NANNY_CAPACITANCE_TYPE2, KEY_NANNY_CAPACITANCE_TYPE3,
        		KEY_NANNY_PRICE, KEY_NANNY_WORKPLACE, KEY_NANNY_OTHER}, 
        		null, null, null, null, null);*/
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
        
    /*private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	try {
        		db.execSQL(DATABASE_CREATE);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }    

    //---opens the database---
    public DBNanny open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a record into the database---
    public long insertRecord(String first_name, String last_name,
    		String street, String city, String postal_code,
    		String type1, String type2, String type3,
    		String price, String workplace, String other) 
    {
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_NANNY_FIRSTNAME, first_name);
    	initialValues.put(KEY_NANNY_LASTNAME, last_name);
    	initialValues.put(KEY_NANNY_STREET, street);
    	initialValues.put(KEY_NANNY_CITY, city);
    	initialValues.put(KEY_NANNY_CITYPC, postal_code);
    	initialValues.put(KEY_NANNY_CAPACITANCE_TYPE1, type1);
    	initialValues.put(KEY_NANNY_CAPACITANCE_TYPE2, type2);
    	initialValues.put(KEY_NANNY_CAPACITANCE_TYPE3, type3);
    	initialValues.put(KEY_NANNY_PRICE, price);
    	initialValues.put(KEY_NANNY_WORKPLACE, workplace);
    	initialValues.put(KEY_NANNY_OTHER, other);
    	return db.insert(DATABASE_TABLE, null, initialValues);
    }
    		
   //---deletes a particular record---
    public boolean deleteContact(long nannyId) 
    {
        return db.delete(DATABASE_TABLE, KEY_NANNY_ID + "=" + nannyId, null) > 0;
    }

    //---retrieves all the records---
    public List<String> getAllRecords() 
    {
    	List<String> list = new ArrayList<String>();
    	String[] columns = {"nom","ville"};
		Cursor cursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
       /* Cursor cursor = db.query(DATABASE_TABLE, new String[] {
        		KEY_NANNY_ID, 
        		KEY_NANNY_FIRSTNAME, KEY_NANNY_LASTNAME,
        		KEY_NANNY_STREET, KEY_NANNY_CITY, KEY_NANNY_CITYPC,
        		KEY_NANNY_CAPACITANCE_TYPE1, KEY_NANNY_CAPACITANCE_TYPE2, KEY_NANNY_CAPACITANCE_TYPE3,
        		KEY_NANNY_PRICE, KEY_NANNY_WORKPLACE, KEY_NANNY_OTHER}, 
        		null, null, null, null, null);*/
    /*    cursor.moveToFirst();
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

    //---retrieves a particular record---
    public Cursor getRecord(long nannyId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_NANNY_ID, 
                		KEY_NANNY_FIRSTNAME, KEY_NANNY_LASTNAME,
                		KEY_NANNY_STREET, KEY_NANNY_CITY, KEY_NANNY_CITYPC,
                		KEY_NANNY_CAPACITANCE_TYPE1, KEY_NANNY_CAPACITANCE_TYPE2, KEY_NANNY_CAPACITANCE_TYPE3,
                		KEY_NANNY_PRICE, KEY_NANNY_WORKPLACE, KEY_NANNY_OTHER}, 
                		KEY_NANNY_ID + "=" + nannyId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    //test
    public Cursor getRecord2(long nannyId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_NANNY_ID, 
                		KEY_NANNY_FIRSTNAME, KEY_NANNY_CITY}, 
                		KEY_NANNY_ID + "=" + nannyId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long nannyId, 
    		String first_name, String last_name,
    		String street, String city, int postal_code,
    		int type1, int type2, int type3,
    		int price, String workplace, String other) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NANNY_FIRSTNAME, first_name);
        args.put(KEY_NANNY_LASTNAME, last_name);
        args.put(KEY_NANNY_STREET, street);
        args.put(KEY_NANNY_CITY, city);
        args.put(KEY_NANNY_CITYPC, postal_code);
        args.put(KEY_NANNY_CAPACITANCE_TYPE1, type1);
        args.put(KEY_NANNY_CAPACITANCE_TYPE2, type2);
        args.put(KEY_NANNY_CAPACITANCE_TYPE3, type3);
        args.put(KEY_NANNY_PRICE, price);
        args.put(KEY_NANNY_WORKPLACE, workplace);
        args.put(KEY_NANNY_OTHER, other);
        return db.update(DATABASE_TABLE, args, KEY_NANNY_ID + "=" + nannyId, null) > 0;
    }*/
}
