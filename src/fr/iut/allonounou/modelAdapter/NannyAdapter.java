package fr.iut.allonounou.modelAdapter;

import java.util.ArrayList;

import fr.iut.allonounou.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NannyAdapter extends ArrayAdapter<Nanny> {
	private final Context context;

	public NannyAdapter(Context context, ArrayList<Nanny> nanny) {
		super(context, 0, nanny);
		this.context = context;
	}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Nanny nanny = getItem(position);   
       
       // Check if an existing view is being reused, otherwise inflate the view
//       if (convertView == null) {
//          convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_search, parent, false);
//       }
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View rowView = inflater.inflate(R.layout.model_search, parent, false);
       
       // Lookup view for data population
       TextView nannyName = (TextView) rowView.findViewById(R.id.nameNanny);
       TextView nannyPlace = (TextView) rowView.findViewById(R.id.placeNanny);
       TextView nannyAddress = (TextView) rowView.findViewById(R.id.adresseNanny);
       
       // Populate the data into the template view using the data object
       nannyName.setText(nanny.getName());
       nannyPlace.setText(nanny.getPlace());
       nannyAddress.setText(nanny.getAddress());
       
       rowView.setTag(nanny.getId());
       
       // Return the completed view to render on screen
       return rowView;
   }
}
