package com.example.awang.weatherbuddymosby.WeatherShowDetails1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.awang.weatherbuddymosby.R;
import com.example.awang.weatherbuddymosby.weathershow.WeatherIconAPI;
import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;
import com.google.gson.Gson;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-17.
 */
public class WeatherShowDetails1 extends AppCompatActivity {

    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.cityname_detail) TextView cityNameTextView;
    @BindView(R.id.update_time) TextView updateTextView;
    @BindView(R.id.temp_detail) TextView tempTextView;
    @BindView(R.id.description_detail) TextView descriptionTextView;
    @BindView(R.id.pressure_detail) TextView pressureTextView;
    @BindView(R.id.humidity_detail) TextView humidityTextView;
    @BindView(R.id.wind_detail) TextView windTextView;

    public static Intent createIntent(Context context, String cityName, String weatherInfoBean){
        Intent intent = new Intent(context, WeatherShowDetails1.class);
        intent.putExtra("cityName", cityName);
        intent.putExtra("weatherInfoBean", weatherInfoBean);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather_info1);
        ButterKnife.bind(this);
        imageView.setTransitionName(getString(R.string.transition_icon));


        Intent intent = getIntent();
        String weatherInfo5Json = intent.getStringExtra("weatherInfoBean");
        WeatherInfoBean weatherInfo5 = new Gson().fromJson(weatherInfo5Json, WeatherInfoBean.class);
        String cityName = intent.getStringExtra("cityName");

        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int minute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        String[] day1 = ca.getTime().toString().split(" ");

        cityNameTextView.setText(cityName);
        updateTextView.setText(  day1[0] + " " + day1[1] + " " + day1[2]  );
        tempTextView.setText("" + (int)(weatherInfo5.getList().get(0).getTemp().getMax() - 273) + "\u2103" + "/" +
                (int)(weatherInfo5.getList().get(0).getTemp().getMin() - 273) + "\u2103"       );
        String url = weatherInfo5.getList().get(0).getWeather().get(0).getIcon();
        WeatherIconAPI.loadWeatherIconWithGlide(this, url,
                imageView);
        descriptionTextView.setText(weatherInfo5.getList().get(0).getWeather().get(0).getDescription());
        humidityTextView.setText(Double.toString(weatherInfo5.getList().get(0).getHumidity()) + "%");
        pressureTextView.setText(Double.toString(weatherInfo5.getList().get(0).getPressure()) + "kPa");
        windTextView.setText(Double.toString(weatherInfo5.getList().get(0).getSpeed()) + "Km/h");
    }

}
