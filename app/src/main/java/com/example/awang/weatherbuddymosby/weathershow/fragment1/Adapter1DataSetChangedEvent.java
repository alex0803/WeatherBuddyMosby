package com.example.awang.weatherbuddymosby.weathershow.fragment1;

import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;

import java.util.List;

/**
 * Created by awang on 2016-08-17.
 */
public class Adapter1DataSetChangedEvent {
    private List<WeatherInfoBean> weatherInfoList;

    public Adapter1DataSetChangedEvent(List<WeatherInfoBean> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }

    public List<WeatherInfoBean> getWeatherInfoList() {
        return weatherInfoList;
    }
}
