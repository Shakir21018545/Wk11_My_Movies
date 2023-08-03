package sg.edu.rp.c346.id21018545.wk11mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;

    ArrayList<Movie> Movies;
    Spinner spinner;



    public CustomAdapter(Context context, int resource,
                         ArrayList<Movie> objects){
        super(context, resource, objects);

        //parent_context = context;
        // layout_id = resource;
        //versionList = objects;

        this.parent_context = context;
        this.layout_id = resource;
        this.Movies = objects;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textTitle);
        TextView tvYear = rowView.findViewById(R.id.textYear);
        TextView tvStar = rowView.findViewById(R.id.textStar);
        TextView tvGenre = rowView.findViewById(R.id.textGenre);
        ImageView ivRating = rowView.findViewById(R.id.imageView);



        // Obtain the Android Version information based on the position
        Movie currentMovie = Movies.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        String star ="";
        for(int i=0; i< currentMovie.getStars();i++){
            star += "*";
        }
        tvStar.setText(star);
        tvYear.setText(currentMovie.getYearReleased()+"");


        ivRating.setImageResource((R.drawable.rating_g));

        if (currentMovie.getRatings().equalsIgnoreCase("g")) {
            ivRating.setImageResource(R.drawable.rating_g);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("m18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("pg")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("pg13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("r21")) {
            ivRating.setImageResource(R.drawable.rating_r21);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("nc16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        }

        return rowView;
    }


}

