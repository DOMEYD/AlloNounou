package fr.iut.allonounou;

import android.text.TextUtils;
import android.widget.*;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.NumberPicker;

public class ContactNanny extends Activity
{
	public final static String USER_NAME = "com.iut.org.USER_NAME"; 
	public final static String USER_EMAIL = "com.iut.org.USER_EMAIL";
	public final static String USER_PHONE = "com.iut.org.USER_PHONE" ; 
	public final static String USER_MESSAGE = "com.iut.org.USER_MESSAGE" ; 
	public final static String CHILD_NAME = "com.iut.org.CHILD_NAME" ; 
	public final static String CHILD_AGED = "com.iut.org.CHILD_AGED"; 
	public final static String INFO_CHILD_AGE = "com.iut.org.INFO_CHILD_AGE"; 
	public final static String INFO_CHILD_SEXE = "com.iut.org.INFO_CHILD_AGE"; 
	public final static String HOURE_ARR = "com.iut.org.HOURE_ARR"; 
	public final static String MINUTE_ARR = "com.iut.org.MINUTE_ARR";
	public final static String HOURE_DEP = "com.iut.org.HOURE_DEP" ; 
	public final static String MINUTE_DEP = "com.iut.org.MINUTE_HOURE_DEP" ;
	
	private String nameNanny;
	private String phoneNanny;
	private String mailNanny;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
    	Intent intent = getIntent();
		nameNanny =intent.getStringExtra(ProfilActivity.EXTRA_NAME);
		phoneNanny= intent.getStringExtra(ProfilActivity.EXTRA_PHONE);
		mailNanny= intent.getStringExtra(ProfilActivity.EXTRA_MAIL);
    	
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		Button btn_send = (Button) findViewById(R.id.btn_send);
		
		final EditText edit_name = (EditText) findViewById(R.id.edit_name);
		final EditText edit_email = (EditText) findViewById(R.id.edit_email);
		final EditText edit_phone = (EditText) findViewById(R.id.edit_phone);
		final EditText edit_message = (EditText) findViewById(R.id.edit_message);
		final EditText edit_childname = (EditText) findViewById(R.id.edit_childname);
		final EditText edit_aged =  (EditText) findViewById(R.id.edit_aged);
		final Spinner info_age= (Spinner) findViewById(R.id.info_age);
		final Spinner info_sexe= (Spinner) findViewById(R.id.info_sexe);
		
		final NumberPicker houre_arr = (NumberPicker) findViewById(R.id.houre_arr);
		final NumberPicker minute_arr = (NumberPicker) findViewById(R.id.minute_arr);
		final NumberPicker houre_dep = (NumberPicker) findViewById(R.id.houre_dep);
		final NumberPicker minute_dep = (NumberPicker) findViewById(R.id.minute_dep);
		
		houre_arr.setMinValue(00);
		houre_arr.setMaxValue(23);
		houre_arr.setWrapSelectorWheel(false); 
		
		minute_arr.setMinValue(00);
		minute_arr.setMaxValue(59);
		minute_arr.setWrapSelectorWheel(false); 
		
		houre_dep.setMinValue(00);
		houre_dep.setMaxValue(23);
		houre_dep.setWrapSelectorWheel(false); 
		
		minute_dep.setMinValue(00);
		minute_dep.setMaxValue(59);
		minute_dep.setWrapSelectorWheel(false); 
        
		// Toucher le bouton "Envoyer" 
		btn_send.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent openSummaryActivity = new Intent(ContactNanny.this, SummaryContact.class);
				openSummaryActivity.putExtra(USER_NAME, edit_name.getText().toString());
				openSummaryActivity.putExtra(USER_PHONE, edit_phone.getText().toString());
				openSummaryActivity.putExtra(USER_EMAIL, edit_email.getText().toString());
				openSummaryActivity.putExtra(USER_MESSAGE, edit_message.getText().toString());
				
				openSummaryActivity.putExtra(CHILD_NAME, edit_childname.getText().toString());
				openSummaryActivity.putExtra(CHILD_AGED, edit_aged.getText().toString());
				openSummaryActivity.putExtra(INFO_CHILD_AGE, info_age.getSelectedItem().toString());
				
				openSummaryActivity.putExtra(HOURE_ARR, String.valueOf(houre_arr.getValue()));
				openSummaryActivity.putExtra(MINUTE_ARR, String.valueOf(minute_arr.getValue()));
				openSummaryActivity.putExtra(HOURE_DEP, String.valueOf(houre_dep.getValue()));
				openSummaryActivity.putExtra(MINUTE_DEP, String.valueOf(minute_dep.getValue()));
				
				if(TextUtils.isEmpty(edit_name.getText().toString()) || TextUtils.isEmpty(edit_email.getText().toString()) || TextUtils.isEmpty(edit_phone.getText().toString()) || TextUtils.isEmpty(edit_message.getText().toString()) || TextUtils.isEmpty(edit_aged.getText().toString()) || TextUtils.isEmpty(edit_childname.getText().toString())) {
					Toast.makeText(getApplicationContext(), "Informations manquantes", Toast.LENGTH_SHORT).show();
				} 
				else {
					startActivity(openSummaryActivity);
				}
			}
		});
		
		// Spinner ("mois" , "an(s)")
		ArrayAdapter<CharSequence> infoage = ArrayAdapter.createFromResource(this,
		R.array.month_array, android.R.layout.simple_spinner_item);
		infoage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		info_age.setAdapter(infoage);
		
		// Spinner (sexe)
		ArrayAdapter<CharSequence> infosexe = ArrayAdapter.createFromResource(this,
		R.array.infosexe_array, android.R.layout.simple_spinner_item);
		infosexe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		info_sexe.setAdapter(infosexe);
    }
}
