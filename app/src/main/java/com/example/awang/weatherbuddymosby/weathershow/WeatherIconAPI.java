package com.example.awang.weatherbuddymosby.weathershow;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by awang on 2016-07-20.
 */
public class WeatherIconAPI {

    public static void loadWeatherIconWithGlide(Context context, String icon, ImageView imageView){
        final String WEATHER_ICON_BASE_URL = "http://openweathermap.org/img/w/";
        Glide
                .with(context)
                .load(WEATHER_ICON_BASE_URL + icon + ".png")
                .into(imageView);
    }
}
