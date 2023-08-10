package sg.edu.rp.c346.id21018545.wk11mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnShowList;
    RadioGroup rg;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle((getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main)));
        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnGetTasks);
        rg = findViewById(R.id.RadioGroupStars);
        spinner = findViewById(R.id.spinner);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String genre = etGenre.getText().toString().trim();
                if (title.length() == 0 || genre.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                String year_str = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(year_str);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                Spinner mySpinner = (Spinner)findViewById(R.id.spinner);
                String ratings = mySpinner.getSelectedItem().toString();
                dbh.insertMovie(title, genre, year, stars, ratings);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etTitle.setText("");
                etGenre.setText("");
                etYear.setText("");


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(i);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


    }


    private int getStars() {
        int stars = 1;
        if(rg.getCheckedRadioButtonId()==R.id.radioButton1) {
            stars = 1;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton2) {
            stars = 2;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton3) {
            stars = 3;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton4) {
            stars = 4;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton5) {
            stars = 5;

        }

        return stars;
    }




}