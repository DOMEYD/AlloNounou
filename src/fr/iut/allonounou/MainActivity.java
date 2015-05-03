package fr.iut.allonounou;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_LOCATION = "fr.iut.allonounou.LOCATION";
	public final static String EXTRA_NANNY_NAME = "fr.iut.allonounou.NANNY_NAME";
	
	private double lat = 0;
	private double lon = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
			intent.putExtra(EXTRA_LOCATION, pos);
		} else {
			return;
		}
		
//		// MOVE Location TO new activity
//	    EditText editText = (EditText) findViewById(R.id.et_location);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_LOCATION, message);
//	    // MOVE Nanny name TO new activity
//	    EditText editText2 = (EditText) findViewById(R.id.et_assistName);
//	    String message2 = editText2.getText().toString();
//	    intent.putExtra(EXTRA_NANNY_NAME, message2);
	    
	    // LAUNCH
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
		
		// GET geocoder service
		Geocoder geocoder;
		List<Address> addresses;
		geocoder = new Geocoder(this, Locale.getDefault());
		
		// RETRIEVE list of matchs addresses
		addresses = geocoder.getFromLocation(lat, lon, 1);
		
		EditText editTextLocation = (EditText) findViewById(R.id.et_location);
		editTextLocation.setText(addresses.get(0).getAddressLine(0) + " " + addresses.get(0).getAdminArea() + " " + addresses.get(0).getLocality());
	}
}
