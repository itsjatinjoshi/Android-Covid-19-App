package com.example.androidpracticeforconcepclearing.ViewModel;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.androidpracticeforconcepclearing.Model.Country;
import com.example.androidpracticeforconcepclearing.R;
import com.example.androidpracticeforconcepclearing.View.AffectedCountries;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Country> {
    private static final String TAG = "CustomAdapter";
    private Context context;
    private List<Country> countryList;
    private List<Country> countryListFiltered;

    public CustomAdapter(Context context, List<Country> countryList) {
        super(context, R.layout.list_custom_item, countryList);
        this.context = context;
        this.countryList = countryList;
        this.countryListFiltered = countryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG, "GETVIEW START");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item, null, true);
        TextView countryName = view.findViewById(R.id.tvCountryName);
        ImageView countryFlagImage = view.findViewById(R.id.imageFlag);

        countryName.setText(countryListFiltered.get(position).getCountryName());
        Glide.with(context).load(countryListFiltered.get(position).getCountryFlag()).into(countryFlagImage);


        return view;
    }

    @Override
    public int getCount() {
        return countryListFiltered.size();
    }

    @Nullable
    @Override
    public Country getItem(int position) {
        return countryListFiltered.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countryList.size();
                    filterResults.values = countryList;
                } else {
                    List<Country> searchCountry = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (Country items : countryList) {
                        if (items.getCountryName().toLowerCase().contains(searchStr)) {
                            searchCountry.add(items);
                        }
                        filterResults.count = searchCountry.size();
                        filterResults.values = searchCountry;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryListFiltered = (List<Country>) results.values;
                AffectedCountries.countriesList = (List<Country>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}