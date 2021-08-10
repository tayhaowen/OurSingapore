package sg.edu.rp.c346.id20042303.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<islands> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<islands> object) {
        super(context, resource, object);

        parent_context = context;
        layout_id = resource;
        versionList = object;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvSize = rowView.findViewById(R.id.textViewSize);
        RatingBar tvStar = rowView.findViewById(R.id.editStars);
        TextView tvDescription = rowView.findViewById(R.id.textViewDescription);

        // Obtain the Android Version information based on the position
        islands currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information

        tvName.setText(currentVersion.getName());
        tvSize.setText(currentVersion.getSize() + "");
        tvStar.setRating(currentVersion.getStar());
        tvDescription.setText(currentVersion.getDescription());


        return rowView;
    }
}
