package com.example.awang.weatherbuddymosby.weathershow.fragment3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.awang.weatherbuddymosby.R;
import com.example.awang.weatherbuddymosby.weathershow.WeatherIconAPI;
import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;
import com.example.awang.weatherbuddymosby.weathershow.fragment1.WeatherShow1Adapter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-16.
 */
public class WeatherShow3Adapter extends RecyclerView.Adapter<WeatherShow3Adapter.ViewHolder> {

    private List<WeatherInfoBean> weatherInfoList;
    private Context context;

    public WeatherShow3Adapter(Context context) {
        this.context = context;
    }

    public void setWeatherInfoList(List<WeatherInfoBean> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }

    @Override
    public int getItemCount() {
        return weatherInfoList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_weather_3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int minute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);

        String[] day1 = ca.getTime().toString().split(" ");
        ca.add(Calendar.DATE, 1);
        String[] day2 = ca.getTime().toString().split(" ");
        ca.add(Calendar.DATE, 1);
        String[] day3 = ca.getTime().toString().split(" ");

        viewHolder.postedTimeTextView2.setText("" + hour + ":" + minute + ":" + second);

        viewHolder.day1TextView3.setText(day1[0] );
        viewHolder.day2TextView3.setText(day2[0] );
        viewHolder.day3TextView3.setText(day3[0] );

        viewHolder.citeNameTextView3.setText(weatherInfoList.get(position).getCity().getName());

        viewHolder.temp1TextView3.setText("" + (int)(weatherInfoList.get(position).getList().get(0).getTemp().getMax() - 273) + "\u2103" + "/"
                + (int)(weatherInfoList.get(position).getList().get(0).getTemp().getMin() - 273) + "\u2103");
        viewHolder.temp2TextView3.setText("" + (int)(weatherInfoList.get(position).getList().get(1).getTemp().getMax() - 273) + "\u2103" + "/"
                + (int)(weatherInfoList.get(position).getList().get(1).getTemp().getMin() - 273) + "\u2103");
        viewHolder.temp3TextView3.setText("" + (int)(weatherInfoList.get(position).getList().get(2).getTemp().getMax() - 273) + "\u2103" + "/"
                + (int)(weatherInfoList.get(position).getList().get(2).getTemp().getMin() - 273) + "\u2103");

        viewHolder.descriptionDay1TextView3.setText(weatherInfoList.get(position).getList().get(0).getWeather().get(0).getDescription());
        viewHolder.descriptionDay2TextView3.setText(weatherInfoList.get(position).getList().get(1).getWeather().get(0).getDescription());
        viewHolder.descriptionDay3TextView3.setText(weatherInfoList.get(position).getList().get(2).getWeather().get(0).getDescription());

        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(0).getWeather().get(0).getIcon(),
                viewHolder.icon1ImageView3);
        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(1).getWeather().get(0).getIcon(),
                viewHolder.icon2ImageView3);
        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(2).getWeather().get(0).getIcon(),
                viewHolder.icon3ImageView3);

        if (mOnItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, pos);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView, pos);
                    return false;
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.citynameTextView3) TextView citeNameTextView3;
        @BindView(R.id.postedTime2) TextView postedTimeTextView2;
        @BindView(R.id.day1TextView3) TextView day1TextView3;
        @BindView(R.id.day2TextView3) TextView day2TextView3;
        @BindView(R.id.day3TextView3) TextView day3TextView3;

        @BindView(R.id.tempDay1TextView3) TextView temp1TextView3;
        @BindView(R.id.tempDay2TextView3) TextView temp2TextView3;
        @BindView(R.id.tempDay3TextView3) TextView temp3TextView3;

        @BindView(R.id.iconDay1ImageView3) ImageView icon1ImageView3;
        @BindView(R.id.iconDay2ImageView3) ImageView icon2ImageView3;
        @BindView(R.id.iconDay3ImageView3) ImageView icon3ImageView3;

        @BindView(R.id.descriptionDay1TextView3) TextView descriptionDay1TextView3;
        @BindView(R.id.descriptionDay2TextView3) TextView descriptionDay2TextView3;
        @BindView(R.id.descriptionDay3TextView3) TextView descriptionDay3TextView3;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // define the onItemClickListener on RecyclerView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
