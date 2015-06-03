package fr.iut.allonounou;

import android.widget.*;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.telephony.gsm.SmsManager;

@SuppressWarnings("deprecation")
public class SummaryContact extends Activity {
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_contact);
		
		Button summary_ok = (Button) findViewById(R.id.summary_ok);
		TextView sum_name = (TextView) findViewById(R.id.sum_name);
		TextView sum_aged = (TextView) findViewById(R.id.sum_aged);
		TextView sum_info_age = (TextView) findViewById(R.id.sum_info_age);
		TextView arr_h = (TextView) findViewById(R.id.arr_h);
		TextView arr_m = (TextView) findViewById(R.id.arr_m);
		TextView dep_h = (TextView) findViewById(R.id.dep_h);
		TextView dep_m = (TextView) findViewById(R.id.dep_m);
		
		Intent intent = getIntent();
		
		// Récupération des éléments du formaire et mise à jour des données pour le résumé
		   
		String GET_CHILD_NAME = intent.getStringExtra(ContactNanny.CHILD_NAME);
		sum_name.setText(GET_CHILD_NAME);
		
		String GET_CHILD_AGED = intent.getStringExtra(ContactNanny.CHILD_AGED);
		sum_aged.setText(GET_CHILD_AGED);
		
		String GET_INFO_CHILD_AGE = intent.getStringExtra(ContactNanny.INFO_CHILD_AGE);
		sum_info_age.setText(GET_INFO_CHILD_AGE);
		
		String GET_HOURE_ARR = intent.getStringExtra(ContactNanny.HOURE_ARR);
		arr_h.setText(GET_HOURE_ARR);
		
		String GET_MINUTE_ARR = intent.getStringExtra(ContactNanny.MINUTE_ARR);
		arr_m.setText(GET_MINUTE_ARR);
		
		String GET_HOURE_DEP = intent.getStringExtra(ContactNanny.HOURE_DEP);
		dep_h.setText(GET_HOURE_DEP);
		
		String GET_MINUTE_DEP = intent.getStringExtra(ContactNanny.MINUTE_DEP);
		dep_m.setText(GET_MINUTE_DEP);
		
		final String NUM_TEL = intent.getStringExtra(ContactNanny.USER_PHONE);
		
		final String MSG = "TEST";
		
		// Si touche le bouton OK, revient à l'activité principale et envoit un SMS
		summary_ok.setOnClickListener(new View.OnClickListener(){
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v){
				Intent intent = new Intent(SummaryContact.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SmsManager.getDefault().sendTextMessage(NUM_TEL, null, MSG, null, null);
				startActivity(intent);
			}
		});
    }
}

	