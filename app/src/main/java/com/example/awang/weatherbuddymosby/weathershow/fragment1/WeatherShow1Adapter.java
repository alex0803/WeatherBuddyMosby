package com.example.awang.weatherbuddymosby.weathershow.fragment1;

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

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-16.
 */
public class WeatherShow1Adapter extends RecyclerView.Adapter<WeatherShow1Adapter.ViewHolder> {


    private List<WeatherInfoBean> weatherInfoList;
    private Context context;

    public WeatherShow1Adapter(Context context) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_weather_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int minute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        String[] day1 = ca.getTime().toString().split(" ");

        viewHolder.postedTimeTextView1.setText("" + hour + ":" + minute + ":" + second);
        viewHolder.dateTextView1.setText(day1[0] + " " + day1[1] + " " + day1[2]);
        viewHolder.cityNameTextView1.setText(weatherInfoList.get(position).getCity().getName());
        viewHolder.tempTextView1.setText("" + (int)(weatherInfoList.get(position).getList().get(0).getTemp().getMax() - 273.00) + "\u2103" + "/" +
                Integer.toString((int) weatherInfoList.get(position).getList().get(0).getTemp().getMin() - 273) + "\u2103");
        viewHolder.windTextView1.setText(Double.toString(weatherInfoList.get(position).getList().get(0).getSpeed()) + "Km/h");
        viewHolder.humidityTextView1.setText(Double.toString(weatherInfoList.get(position).getList().get(0).getHumidity()) + "%");
        viewHolder.descriptionTextView1.setText(weatherInfoList.get(position).getList().get(0).getWeather().get(0).getDescription());


        WeatherIconAPI.loadWeatherIconWithGlide(context, weatherInfoList.get(position).getList().get(0).getWeather().get(0).getIcon(),
                viewHolder.weatherIcon1);

        if (mOnItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, (ImageView) view.findViewById(R.id.weather_icon1), pos);
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
        @BindView(R.id.citynameTextView1) TextView cityNameTextView1;
        @BindView(R.id.dateTextView1) TextView dateTextView1;
        @BindView(R.id.tempTextView1) TextView tempTextView1;
        @BindView(R.id.windTextView1) TextView windTextView1;
        @BindView(R.id.humidityTextView1) TextView humidityTextView1;
        @BindView(R.id.weather_icon1) ImageView weatherIcon1;
        @BindView(R.id.description1) TextView descriptionTextView1;
        @BindView(R.id.postedTime1) TextView postedTimeTextView1;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    // define the onItemClickListener on RecyclerView
    public interface OnItemClickListener {
        void onItemClick(View view, ImageView icon, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
