package com.example.androidpracticeforconcepclearing.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidpracticeforconcepclearing.Model.Country;
import com.example.androidpracticeforconcepclearing.R;
import com.example.androidpracticeforconcepclearing.ViewModel.CustomAdapter;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {
    private static final String TAG = "AffectedCountries";
    EditText etSearch;
    ListView listView;
    SimpleArcLoader arcLoader;

    public static List<Country> countriesList = new ArrayList<>();
    Country country;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        etSearch = findViewById(R.id.etSearch);
        listView = findViewById(R.id.listView);
        arcLoader = findViewById(R.id.arcLoader);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fetchCountryData();

        searchCountries();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailActivity = new Intent(AffectedCountries.this, DetailActivity.class);
                detailActivity.putExtra("position", position);
                startActivity(detailActivity);
            }
        });
    }

    private void searchCountries() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customAdapter.getFilter().filter(s);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchCountryData() {
        String url = "https://corona.lmao.ninja/v2/countries/";
        arcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String countryName = object.getString("country");
                        String cases = object.getString("cases");
                        String todayCases = object.getString("todayCases");
                        String deaths = object.getString("deaths");
                        String todayDeaths = object.getString("todayDeaths");
                        String recovered = object.getString("recovered");
                        String active = object.getString("active");
                        String critical = object.getString("critical");

                        JSONObject countryFlagObject = object.getJSONObject("countryInfo");
                        String flagUrl = countryFlagObject.getString("flag");

                        country = new Country(flagUrl, countryName, cases, todayCases, deaths, todayDeaths, recovered, active, critical);

                        countriesList.add(country);
                    }

                    customAdapter = new CustomAdapter(AffectedCountries.this, countriesList);
                    listView.setAdapter(customAdapter);
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);

                } catch (JSONException e) {
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "ERROR IN VOLLEY " + error.getMessage());
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
