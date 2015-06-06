package fr.iut.allonounou;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Jean-Yves -----------------------------------------------------------------------------------

public class NannyProfil extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil2);
		
		Button btn_contact = (Button) findViewById(R.id.button1);
		
		// si clique sur Dmande de contact --> ouvre ContactNanny
		btn_contact.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent openContactNanny = new Intent(NannyProfil.this, ContactNanny.class);
				startActivity(openContactNanny);
			}
		});
	}
}

//-------- -----------------------------------------------------------------------------------