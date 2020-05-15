package com.example.androidpracticeforconcepclearing.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidpracticeforconcepclearing.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTotalDeaths, tvTodayCases, tvTodayDeaths, tvAffectedCountries;

    ScrollView scrollStats;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);

        scrollStats = findViewById(R.id.scrollStats);
        simpleArcLoader = findViewById(R.id.simpleArcLoader);
        pieChart = findViewById(R.id.pieChart);

        fetchCoronaData();
    }

    private void fetchCoronaData() {
        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    tvCases.setText(object.getString("cases"));
                    tvRecovered.setText(object.getString("recovered"));
                    tvCritical.setText(object.getString("critical"));
                    tvActive.setText(object.getString("active"));
                    tvTotalDeaths.setText(object.getString("deaths"));
                    tvTodayCases.setText(object.getString("todayCases"));
                    tvTodayDeaths.setText(object.getString("todayDeaths"));
                    tvAffectedCountries.setText(object.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Cases ", Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Recovered ", Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths ", Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active ", Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart.startAnimation();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollStats.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollStats.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "ERROR IN VOLLEY " + error.getMessage());
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollStats.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTrackCountries(View view) {
        Intent affectedCountries = new Intent(MainActivity.this, AffectedCountries.class);
        startActivity(affectedCountries);
    }
}
