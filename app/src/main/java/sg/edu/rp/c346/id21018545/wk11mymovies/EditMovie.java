package sg.edu.rp.c346.id21018545.wk11mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditMovie extends AppCompatActivity {

    EditText etID, etTitle, etGenre, etYear;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;
    TextView tvRating;
    ImageView ivRating;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_Edit_Movie));

        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        rg = findViewById(R.id.RadioGroupStars);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinner = findViewById(R.id.spinner);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        etID.setText(currentMovie.getId()+"");
        etTitle.setText(currentMovie.getTitle());
        etGenre.setText(currentMovie.getGenre());
        etYear.setText(currentMovie.getYearReleased()+"");

        switch (currentMovie.getStars()){
            case 5: rb5.setChecked(true);
                break;
            case 4: rb4.setChecked(true);
                break;
            case 3: rb3.setChecked(true);
                break;
            case 2: rb2.setChecked(true);
                break;
            case 1: rb1.setChecked(true);
        }



        if (currentMovie.getRatings().equalsIgnoreCase("g")) {
            spinner.setSelection(0);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("m18")) {
            spinner.setSelection(1);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("pg")) {
            spinner.setSelection(2);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("pg13")) {
            spinner.setSelection(3);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("r21")) {
            spinner.setSelection(4);
        }
        if (currentMovie.getRatings().equalsIgnoreCase("nc16")) {
            spinner.setSelection(5);
        }






        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovie.this);
                currentMovie.setTitle(etTitle.getText().toString().trim());
                currentMovie.setGenre(etGenre.getText().toString().trim());


                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditMovie.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentMovie.setYearReleased(year);

                int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);
                currentMovie.setStars(Integer.parseInt(rb.getText().toString()));


                Spinner mySpinner = (Spinner)findViewById(R.id.spinner);
                String ratings = mySpinner.getSelectedItem().toString();
                currentMovie.setRatings(ratings);


                int result = dbh.updateMovie(currentMovie);
                if (result>0){
                    Toast.makeText(EditMovie.this, "Movie updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditMovie.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovie.this);
                int result = dbh.deleteMovie(currentMovie.getId());
                if (result>0){
                    Toast.makeText(EditMovie.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditMovie.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
