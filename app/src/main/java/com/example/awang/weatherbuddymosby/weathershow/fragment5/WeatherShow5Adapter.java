package com.example.awang.weatherbuddymosby.weathershow.fragment5;

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
public class WeatherShow5Adapter extends RecyclerView.Adapter<WeatherShow5Adapter.ViewHolder> {

    private List<WeatherInfoBean> weatherInfoList;
    private Context context;

    public WeatherShow5Adapter(Context context) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_weather_5, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.cityNameTextView5.setText(weatherInfoList.get(position).getCity().getName());

        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int minute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        String[] day1 = ca.getTime().toString().split(" ");
        ca.add(Calendar.DATE, 1);
        String[] day2 = ca.getTime().toString().split(" ");
        ca.add(Calendar.DATE, 1);
        String[] day3 = ca.getTime().toString().split(" ");
        ca.add(Calendar.DATE, 1);
        String[] day4 = ca.getTime().toString().split(" ");
        ca.add(Calendar.DATE, 1);
        String[] day5 = ca.getTime().toString().split(" ");

        viewHolder.postTime5.setText("" + hour + ":" + minute + ":" + second);
        viewHolder.day1TextView5.setText(day1[1] + " " + day1[2]);
        viewHolder.day2TextView5.setText(day2[1] + " " + day2[2]);
        viewHolder.day3TextView5.setText(day3[1] + " " + day3[2]);
        viewHolder.day4TextView5.setText(day4[1] + " " + day4[2]);
        viewHolder.day5TextView5.setText(day5[1] + " " + day5[2]);

        viewHolder.temp1TextView5.setText("" + (int)(weatherInfoList.get(position).getList().get(0).getTemp().getMax() - 273) + "\u2103" + "\n"
                + (int)(weatherInfoList.get(position).getList().get(0).getTemp().getMin() - 273) + "\u2103");
        viewHolder.temp2TextView5.setText("" + (int)(weatherInfoList.get(position).getList().get(1).getTemp().getMax() - 273) + "\u2103" + "\n"
                + (int)(weatherInfoList.get(position).getList().get(1).getTemp().getMin() - 273) + "\u2103");
        viewHolder.temp3TextView5.setText("" + (int)(weatherInfoList.get(position).getList().get(2).getTemp().getMax() - 273) + "\u2103" + "\n"
                + (int)(weatherInfoList.get(position).getList().get(2).getTemp().getMin() - 273) + "\u2103");
        viewHolder.temp4TextView5.setText("" + (int)(weatherInfoList.get(position).getList().get(3).getTemp().getMax() - 273) + "\u2103" + "\n"
                + (int)(weatherInfoList.get(position).getList().get(3).getTemp().getMin() - 273) + "\u2103");
        viewHolder.temp5TextView5.setText("" + (int)(weatherInfoList.get(position).getList().get(4).getTemp().getMax() - 273) + "\u2103" + "\n"
                + (int)(weatherInfoList.get(position).getList().get(4).getTemp().getMin() - 273) + "\u2103");

        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(0).getWeather().get(0).getIcon(),
                viewHolder.icon1ImageView5);
        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(1).getWeather().get(0).getIcon(),
                viewHolder.icon2ImageView5);
        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(2).getWeather().get(0).getIcon(),
                viewHolder.icon3ImageView5);
        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(3).getWeather().get(0).getIcon(),
                viewHolder.icon4ImageView5);
        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(4).getWeather().get(0).getIcon(),
                viewHolder.icon5ImageView5);

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
        @BindView(R.id.citynameTextView5) TextView cityNameTextView5;
        @BindView(R.id.postedTime5) TextView postTime5;

        @BindView(R.id.day1TextView5) TextView day1TextView5;
        @BindView(R.id.day2TextView5) TextView day2TextView5;
        @BindView(R.id.day3TextView5) TextView day3TextView5;
        @BindView(R.id.day4TextView5) TextView day4TextView5;
        @BindView(R.id.day5TextView5) TextView day5TextView5;

        @BindView(R.id.tempDay1TextView5) TextView temp1TextView5;
        @BindView(R.id.tempDay2TextView5) TextView temp2TextView5;
        @BindView(R.id.tempDay3TextView5) TextView temp3TextView5;
        @BindView(R.id.tempDay4TextView5) TextView temp4TextView5;
        @BindView(R.id.tempDay5TextView5) TextView temp5TextView5;

        @BindView(R.id.iconDay1ImageView5) ImageView icon1ImageView5;
        @BindView(R.id.iconDay2ImageView5) ImageView icon2ImageView5;
        @BindView(R.id.iconDay3ImageView5) ImageView icon3ImageView5;
        @BindView(R.id.iconDay4ImageView5) ImageView icon4ImageView5;
        @BindView(R.id.iconDay5ImageView5) ImageView icon5ImageView5;

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
