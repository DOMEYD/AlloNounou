package fr.iut.allonounou;

import java.io.IOException;

import fr.iut.allonounou.modelAdapter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/*
 * ACTIVITY which manage search form
 */
public class MainActivity extends Activity {
	public final static String EXTRA_LOCATION = "fr.iut.allonounou.LOCATION";
	public final static String EXTRA_NANNY_NAME = "fr.iut.allonounou.NANNY_NAME";
	
	private double lat = 0;
	private double lon = 0;
	private EditText editTextLocation;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// CREATE listview		
		ArrayList<Nanny> nannys = new ArrayList<Nanny>();
		
		// LOAD Favorites
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		String json = sharedPref.getString("Favorites", null);
		JSONArray jObject = null;
		if(json != null) {
			try {
				jObject = new JSONArray(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(jObject != null) {
			for(int i = 0; i < jObject.length(); i++) {
				JSONObject jo = null;
				try {
					jo = jObject.getJSONObject(i);
					Nanny tmpNanny = new Nanny(jo.getString("Name"), "", jo.getString("district"), jo.getString("district"));
					nannys.add(tmpNanny);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			
			// Create the adapter to convert the array to views
			NannyAdapter adapter = new NannyAdapter(this, nannys);
			// Attach the adapter to a ListView
			ListView listView = (ListView) findViewById(R.id.listView);
			listView.setAdapter(adapter);		
		}
		
		// DISPLAY layout after to prevent crash
		setContentView(R.layout.activity_main);
		
		editTextLocation = (EditText) findViewById(R.id.et_location);
		editTextLocation.setOnFocusChangeListener(new OnFocusChangeListener() {          
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					// code to execute when EditText loses focus
					Geocoder geocoder = new Geocoder(MainActivity.this);
					List<Address> addresses;
					
					try {
						addresses = geocoder.getFromLocationName(editTextLocation.getText().toString(), 1);
						lat = addresses.get(0).getLatitude();
						lon = addresses.get(0).getLongitude();
					} catch(IOException e) {}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void searchNanny(View view) {
		// GET intent for search nanny
		Intent intent = new Intent(this, SearchNannyActivity.class);
		
		EditText editTextnannyName = (EditText) findViewById(R.id.et_assistName);
	    String nannyName = editTextnannyName.getText().toString();
		if(nannyName.length() > 0) {
			intent.putExtra(EXTRA_NANNY_NAME, nannyName);
		} else if(this.lat != 0 && this.lon != 0) {
			Double [] pos = {lat, lon};
			Log.d("DEBUG_LATLON", lat + " " + lon);
			intent.putExtra(EXTRA_LOCATION, pos);
		} else {
			return;
		}
	    
	    // LAUNCH
	    startActivity(intent);
	}
	
	public void startContact(View view) {
		Intent intent = new Intent(this, ContactNanny.class);
		startActivity(intent);
	}
	
	public void getLocation(View view) throws IOException {
		// GET locationManager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		// RETRIEVE last location (know) from network
		Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		// SET latitude, longitude
		lat = lastKnownLocation.getLatitude();
		lon = lastKnownLocation.getLongitude();
		
		// CREATE waiting dialog box
		final Dialog d = new Dialog(MainActivity.this);
//		d.setTitle(getString(R.string));
		d.setContentView(R.layout.dialog_loadaddresses);
		d.show();
		
		// GET geocoder service
		Geocoder geocoder;
		List<Address> addresses;
		geocoder = new Geocoder(this, Locale.getDefault());
		
		// RETRIEVE list of matchs addresses
		addresses = geocoder.getFromLocation(lat, lon, 1);
		
		EditText editTextLocation = (EditText) findViewById(R.id.et_location);
		editTextLocation.setText(addresses.get(0).getAddressLine(0) + " " + addresses.get(0).getPostalCode() + " " + addresses.get(0).getLocality());
		
		d.dismiss();
	}
}
