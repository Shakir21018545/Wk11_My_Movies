package sg.edu.rp.c346.id21018545.wk11mymovies;


import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Movie> MovieList;
    //ArrayAdapter<Song> adapter;
    Button btnPG13;
    ArrayList<String> years;
    CustomAdapter adapter;



    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        MovieList.clear();
        MovieList.addAll(dbh.getAllMovies());
        adapter.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getYears());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_answer));

        lv = this.findViewById(R.id.lv);
        btnPG13 = this.findViewById(R.id.btnPG13);

        DBHelper dbh = new DBHelper(this);
        MovieList = dbh.getAllMovies();
        years = dbh.getYears();
        dbh.close();

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
        //lv.setAdapter(adapter);
        adapter = new CustomAdapter(this, R.layout.row,MovieList);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(AnswerActivity.this, EditMovie.class);
                i.putExtra("movie", MovieList.get(position));
                startActivity(i);
            }
        });

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(AnswerActivity.this);
                MovieList.clear();
                MovieList.addAll(dbh.getAllMoviesByRatings("PG13"));
                adapter.notifyDataSetChanged();
            }
        });



    }
}