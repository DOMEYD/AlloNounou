package fr.iut.allonounou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
		Intent intent = new Intent(this, SearchNannyActivity.class);
		
		// MOVE Location TO new activity
	    EditText editText = (EditText) findViewById(R.id.et_location);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_LOCATION, message);
	    // MOVE Nanny name TO new activity
	    EditText editText2 = (EditText) findViewById(R.id.et_assistName);
	    String message2 = editText2.getText().toString();
	    intent.putExtra(EXTRA_NANNY_NAME, message2);
	    
	    // LAUNCH
	    startActivity(intent);
	}
	
	public void getLocation(View view) {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

//		// Define a listener that responds to location updates
//		LocationListener locationListener = new LocationListener() {
//		    public void onLocationChanged(Location location) {
//		      // Called when a new location is found by the network location provider.
//		      makeUseOfNewLocation(location);
//		    }
//
//		    public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//		    public void onProviderEnabled(String provider) {}
//
//		    public void onProviderDisabled(String provider) {}
//		};
//
//		// Register the listener with the Location Manager to receive location updates
//		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		
		Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
	    EditText editText = (EditText) findViewById(R.id.et_location);
		EditText editText2 = (EditText) findViewById(R.id.et_assistName);
		editText.setText("" + lastKnownLocation.getLatitude());
		editText2.setText("" + lastKnownLocation.getLongitude());
	}
}
