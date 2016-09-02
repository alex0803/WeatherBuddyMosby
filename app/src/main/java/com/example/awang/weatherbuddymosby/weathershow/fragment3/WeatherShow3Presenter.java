package com.example.awang.weatherbuddymosby.weathershow.fragment3;

import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;
import com.example.awang.weatherbuddymosby.weathershow.WeatherLoadAPI;
import com.example.awang.weatherbuddymosby.weathershow.WeatherShowView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

/**
 * Created by awang on 2016-08-16.
 */
public class WeatherShow3Presenter extends MvpBasePresenter<WeatherShowView> {

    private WeatherLoadAPI weatherLoadAPI;

    public void loadDataInfo(final boolean pullToRefresh, List<String> cityNameList){
        if (isViewAttached()){
            getView().showLoading(pullToRefresh);
        }

        weatherLoadAPI = new WeatherLoadAPI(cityNameList, new WeatherLoadAPI.WeatherLoadListener() {
            @Override
            public void onSuccess(List<WeatherInfoBean> weatherInfoList) {
                if (isViewAttached()){
                    getView().setData(weatherInfoList);
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(int code) {
                if (isViewAttached()){
                    getView().showError(new Exception("msg:" + code), pullToRefresh);
                }
            }

            @Override
            public void onNullListException(int code) {
                if (isViewAttached()){
                    getView().showError(new Exception("msg:" + code), pullToRefresh);
                }
            }
        });
        weatherLoadAPI.loadWeatherInfo(pullToRefresh);
    }
}
