package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by Vengage on 11/9/2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
    private final ListItemClickListener mOnClickListener;

    public ForecastAdapter(ListItemClickListener mOnClickListener, String[] mWeatherData) {
        this.mOnClickListener = mOnClickListener;
        this.mWeatherData = mWeatherData;
    }

    public interface ListItemClickListener {
        void onListItemClick(String itemClicked);
    }

    private String[] mWeatherData;

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.forecast_list_item;
        boolean shouldBindToParrentImediately = false;
        View view = inflater.inflate(layoutId, parent, shouldBindToParrentImediately);
        ForecastAdapterViewHolder forecastAdapterViewHolder
                = new ForecastAdapterViewHolder(view);
        return forecastAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if(mWeatherData==null)
            return 0;
        return mWeatherData.length;
    }

    void setWeatherData(String[] weatherData){
        mWeatherData=weatherData;
        notifyDataSetChanged();
    }
    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View itemView) {
            super(itemView);
            mWeatherTextView = itemView.findViewById(R.id.tv_weather_data);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(mWeatherTextView.getText().toString().trim());

        }
    }


}
