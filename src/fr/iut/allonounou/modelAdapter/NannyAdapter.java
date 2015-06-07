package fr.iut.allonounou.modelAdapter;

import java.util.ArrayList;

import fr.iut.allonounou.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NannyAdapter extends ArrayAdapter<Nanny> {

	public NannyAdapter(Context context, ArrayList<Nanny> nanny) {
		super(context, 0, nanny);
		// TODO Auto-generated constructor stub
	}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Nanny nanny = getItem(position);   
       
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_search, parent, false);
       }
       
       // Lookup view for data population
       TextView nannyName = (TextView) convertView.findViewById(R.id.title);
       TextView nannyPlace = (TextView) convertView.findViewById(R.id.artiste);
       TextView nannyAddress = (TextView) convertView.findViewById(R.id.duree);
       
       // Populate the data into the template view using the data object
       nannyName.setText(nanny.getName());
       nannyPlace.setText(nanny.getPlace());
       nannyAddress.setText(nanny.getAddress());
       
       // Return the completed view to render on screen
       return convertView;
   }
}
