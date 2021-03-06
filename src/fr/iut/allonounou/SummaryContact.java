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
		Button summary_cancel = (Button) findViewById(R.id.summary_cancel);
		TextView sum_name = (TextView) findViewById(R.id.sum_name);
		TextView sum_aged = (TextView) findViewById(R.id.sum_aged);
		TextView sum_info_age = (TextView) findViewById(R.id.sum_info_age);
		TextView arr_h = (TextView) findViewById(R.id.arr_h);
		TextView arr_m = (TextView) findViewById(R.id.arr_m);
		TextView dep_h = (TextView) findViewById(R.id.dep_h);
		TextView dep_m = (TextView) findViewById(R.id.dep_m);
		
		Intent intent = getIntent();
		
		// R�cup�ration des �l�ments du formaire et mise � jour des donn�es pour le r�sum�
		
		String GET_USER_NAME = intent.getStringExtra(ContactNanny.USER_NAME);
		String GET_USER_EMAIL = intent.getStringExtra(ContactNanny.USER_EMAIL);
		String GET_USER_MESSAGE = intent.getStringExtra(ContactNanny.USER_MESSAGE);
		
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
		
		String ARR = GET_HOURE_ARR + "h" + GET_MINUTE_ARR;
		
		String GET_HOURE_DEP = intent.getStringExtra(ContactNanny.HOURE_DEP);
		dep_h.setText(GET_HOURE_DEP);
		
		String GET_MINUTE_DEP = intent.getStringExtra(ContactNanny.MINUTE_DEP);
		dep_m.setText(GET_MINUTE_DEP);
		
		String DEP = GET_HOURE_DEP + "h" + GET_MINUTE_DEP;
		
		final String NANNY_NUM_TEL = intent.getStringExtra(ContactNanny.PHONE_NANNY);
		final String USER_NUM_TEL = intent.getStringExtra(ContactNanny.USER_PHONE);
		final String MSG = "Vous venez de recevoir une demande de garde de :\n" + GET_CHILD_NAME + " (" + GET_CHILD_AGED + " " + GET_INFO_CHILD_AGE + ")\n" + "De " + ARR + " � " + DEP + "\n" + "Veillez prendre contact avec " + GET_USER_NAME + " au " + USER_NUM_TEL ;
		
		// Si touche le bouton OK, revient � l'activit� principale et envoit un SMS
		summary_ok.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent(SummaryContact.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SmsManager.getDefault().sendTextMessage(NANNY_NUM_TEL, null, MSG, null, null);
				startActivity(intent);
			}
		});
		
		// Si touche le bouton Annuler, revient � l'activit� pr�cedente
		summary_cancel.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v){
				SummaryContact.this.finish();
			}
		});
    }
}

	