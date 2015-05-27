package fr.iut.allonounou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBNanny<DBAdapter> {
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
	
	private static final String DATABASE_CREATE = 
			"create table if not exists assignments (id integer primary key autoincrement, "
			+ "first_name VARCHAR not null, last_name VARCHAR not null, "
			+ "street VARCHAR, city VARCHAR, postal_code int, "
			+ "type1 int not null, type2 int not null, type3 int not null "
			+ "price int, workplace VARCHAR, other VARCHAR);";
	
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
	private Context context;

    public DBNanny(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
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
    		String street, String city, int postal_code,
    		int type1, int type2, int type3,
    		int price, String workplace, String other) 
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
    public Cursor getAllRecords() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_NANNY_ID, 
        		KEY_NANNY_FIRSTNAME, KEY_NANNY_LASTNAME,
        		KEY_NANNY_STREET, KEY_NANNY_CITY, KEY_NANNY_CITYPC,
        		KEY_NANNY_CAPACITANCE_TYPE1, KEY_NANNY_CAPACITANCE_TYPE2, KEY_NANNY_CAPACITANCE_TYPE3,
        		KEY_NANNY_PRICE, KEY_NANNY_WORKPLACE, KEY_NANNY_OTHER}, 
        		null, null, null, null, null);
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
    }
}
