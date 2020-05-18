package com.example.androidpracticeforconcepclearing.ViewModel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidpracticeforconcepclearing.R;
import com.example.androidpracticeforconcepclearing.Model.States;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.ViewHolder> {
    private static final String TAG = "StatesAdapter";
    List<States> listStates;
    Context context;

    public StatesAdapter(List<States> listStates, Context context) {
        this.listStates = listStates;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: VIEWHOLDER START ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.states_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ONBINDVIEW STARTS");
        holder.tvStateName.setText(listStates.get(position).getStateName());

        String name = listStates.get(position).getStateName();
        System.out.println("STATES NAME : " + name);
        Log.d(TAG, "onBindViewHolder: ONBINDVIEW STOP");
    }

    @Override
    public int getItemCount() {
        if (listStates != null) {
            return listStates.size();
        }
        return 0;
    }

    public void arraySize(){
        int size = listStates.size();
        System.out.println("ARRAY SIZE IS : " + size);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStateName = itemView.findViewById(R.id.tvStatesName);
        }
    }
}
