package fr.iut.allonounou;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class AddDBNanny extends Activity{
	DBNanny db = new DBNanny(this);
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
       
        try {        	
        	String destPath = "/data/data/" + getPackageName() + "/databases/AssignmentDB";
        	File f = new File(destPath);        	
        	if (!f.exists()) {        	
			    CopyDB( getBaseContext().getAssets().open("mydb"), 
					new FileOutputStream(destPath));
        	}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //---add an assignment---
        db.open();        
        long id = db.insertRecord("Paula","HENRIQUEZ","avenue des sports","WERVICK","59117","0","2","1","7","Maison","Jardin avec jeux en extérieur");        
        id = db.insertRecord("Marie","ANTOINETTE","rue des Poilus","OLIVET","45160","3","1","0","9"," "," ");
        db.close();
        
        //---get all Records---
        db.open();
        Cursor c = db.getAllRecords();
        if (c.moveToFirst())
        {
            do {          
                DisplayRecord(c);
            } while (c.moveToNext());
        }
        db.close();
        
        //---get a Record---
        /*db.open();
        Cursor c = db.getRecord(2);
        if (c.moveToFirst())        
            DisplayRecord(c);
        else
            Toast.makeText(this, "No Assignments found", Toast.LENGTH_LONG).show();
        db.close();*/
        
        
        //---update Record---
        /*
        db.open();
        if (db.updateRecord(1, "Hello Android", "2/19/2012", "DPR 224", "First Android Project"))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();        
        db.close();
        */
        
        /*
        //---delete a Record---
        db.open();
        if (db.deleteRecord(1))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();            
        db.close();
        */
    }
    
    /*private class DBNanny extends BaseAdapter {
    	private LayoutInflater mInflater;

		//private ArrayList<>

		@Override
		public int getCount() {
			
			return 0;
		}

		@Override
		public Object getItem(int arg0) {

			return null;
		}

		@Override
		public long getItemId(int arg0) {
			
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			
			return null;
		}
    }*/
        
    public void CopyDB(InputStream inputStream, OutputStream outputStream) 
    throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
    
    public void DisplayRecord(Cursor c)
    {
        Toast.makeText(this, 
                "id: " + c.getString(0) + "\n" +
                "Title: " + c.getString(1) + "\n" +
                "Due Date:  " + c.getString(2),
                Toast.LENGTH_SHORT).show();        
    } 
    
   /*public void addAssignment(View view)
    {
    	Intent i = new Intent("com.pinchtapzoom.addassignment");
    	startActivity(i);
    	Log.d("TAG", "Clicked");
    }*/
}
