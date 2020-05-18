package com.example.androidpracticeforconcepclearing.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.androidpracticeforconcepclearing.R;
import com.leo.simplearcloader.SimpleArcLoader;

public class DetailActivity extends AppCompatActivity {
    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTotalDeaths, tvTodayCases, tvTodayDeaths, tvAffectedCountries;
    int position;

    TextView tvStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        position = getIntent().getIntExtra("position", 0);

        getSupportActionBar().setTitle(AffectedCountries.countriesList.get(position).getCountryName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);

        tvStates = findViewById(R.id.tvStates);
        tvStates.setPaintFlags(tvStates.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stateList = new Intent(DetailActivity.this, StateList.class);
                startActivity(stateList);

            }
        });


        tvCases.setText(AffectedCountries.countriesList.get(position).getCases());
        tvRecovered.setText(AffectedCountries.countriesList.get(position).getRecovered());
        tvCritical.setText(AffectedCountries.countriesList.get(position).getCritical());
        tvActive.setText(AffectedCountries.countriesList.get(position).getActive());
        tvTotalDeaths.setText(AffectedCountries.countriesList.get(position).getDeaths());
        tvTodayCases.setText(AffectedCountries.countriesList.get(position).getTodayCases());
        tvTodayDeaths.setText(AffectedCountries.countriesList.get(position).getTodayDeaths());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }
}
