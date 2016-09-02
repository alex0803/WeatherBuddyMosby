package com.example.awang.weatherbuddymosby.weathershow.fragment3;

import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;

import java.util.List;

/**
 * Created by awang on 2016-08-17.
 */
public class Adapter3DataSetChangedEvent {
    private List<WeatherInfoBean> weatherInfoBeanList;

    public Adapter3DataSetChangedEvent(List<WeatherInfoBean> weatherInfoBeanList) {
        this.weatherInfoBeanList = weatherInfoBeanList;
    }

    public List<WeatherInfoBean> getWeatherInfoBeanList() {
        return weatherInfoBeanList;
    }
}
