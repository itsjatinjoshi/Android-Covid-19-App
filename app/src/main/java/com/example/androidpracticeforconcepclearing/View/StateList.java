package com.example.androidpracticeforconcepclearing.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidpracticeforconcepclearing.Interfaces.StatesApi;
import com.example.androidpracticeforconcepclearing.Model.States;
import com.example.androidpracticeforconcepclearing.R;
import com.example.androidpracticeforconcepclearing.ViewModel.StatesAdapter;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateList extends AppCompatActivity {

    RecyclerView stateRecyclerList;
    EditText etSearch;
    SimpleArcLoader arcLoader;

    StatesAdapter statesAdapter;
    List<States> states = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);

        getSupportActionBar().setTitle("Affected States");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etSearch = findViewById(R.id.etSearch);

        arcLoader = findViewById(R.id.arcLoader);

        getStatesList();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getStatesList() {

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatesApi statesApi = retrofit.create(StatesApi.class);

        Call<List<States>> call = statesApi.getStates();

        call.enqueue(new Callback<List<States>>() {
            @Override
            public void onResponse(Call<List<States>> call, Response<List<States>> response) {
                List<States> statesDetail = response.body();
                if (response.isSuccessful()) {
                   stateListData(response.body());
                } else {
                    Log.e("STATELIST", "onResponse: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<States>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("STATELIST", "onFailure: " + t.getMessage());
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
            }
        });
    }

    private void stateListData(List<States> state) {
        states = state;
        statesAdapter = new StatesAdapter(states, getApplicationContext());

        stateRecyclerList = findViewById(R.id.stateRecyclerList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        stateRecyclerList.setLayoutManager(llm);
        stateRecyclerList.setAdapter(statesAdapter);


    }


}
