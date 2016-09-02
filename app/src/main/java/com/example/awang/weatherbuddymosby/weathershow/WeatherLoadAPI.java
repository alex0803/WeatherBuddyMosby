package com.example.awang.weatherbuddymosby.weathershow;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awang on 2016-08-16.
 */
public class WeatherLoadAPI {

    private final String BASE_URL_FORCAST = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    private final String IMG_URL = "http://openweathermap.org/img/w/";
    private final String API_KEY_WEATHER_REQUEST = "448effa71bf8e50f462def518a3a03d6";

    public interface WeatherLoadListener {
        void onSuccess(List<WeatherInfoBean> weatherInfoList);
        void onFailure(int code);
        void onNullListException(int code);
    }

    private static Handler handler = new Handler(Looper.getMainLooper());
    private List<String> cityNameList;
    private WeatherLoadListener listener;

    public WeatherLoadAPI(List<String> cityNameList, WeatherLoadListener listener) {
        this.cityNameList = cityNameList;
        this.listener = listener;
    }

    public void loadWeatherInfo(final boolean pullToRefresh){
        final List<WeatherInfoBean> weatherInfoList = new ArrayList<>();
        if (cityNameList != null && cityNameList.size() != 0) {
            for (int i = 0; i < cityNameList.size(); i++) {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder().url(BASE_URL_FORCAST + cityNameList.get(i) + "&appid=" + API_KEY_WEATHER_REQUEST).build();

                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        listener.onFailure(1000);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        final String res = response.body().string();
                        final WeatherInfoBean weatherInfo = new Gson().fromJson(res, WeatherInfoBean.class);
                        if (weatherInfoList.size() == 0) {
                            weatherInfoList.add(weatherInfo);
                        } else {
                            final int k = orderOfCityName(weatherInfo.getCity().getName());
                            final int lastPosition = weatherInfoList.size() - 1;
                            if (k > orderOfCityName(weatherInfoList.get(lastPosition).getCity().getName())) {
                                weatherInfoList.add(weatherInfo);
                            } else {
                                if (k < orderOfCityName(weatherInfoList.get(0).getCity().getName())) {
                                    weatherInfoList.add(0, weatherInfo);
                                } else {
                                    for (int j = 1; j < weatherInfoList.size(); j++) {
                                        if (k > orderOfCityName(weatherInfoList.get(j - 1).getCity().getName()) &&
                                                k < orderOfCityName(weatherInfoList.get(j).getCity().getName())) {
                                            weatherInfoList.add(j, weatherInfo);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onSuccess(weatherInfoList);
                            }
                        });

                    }
                });

            }
        }else{
            listener.onNullListException(1000);
        }
    }

    public int orderOfCityName(String s){
        int position = 0;
        for (int i = 0; i<cityNameList.size(); i++){
            if (cityNameList.get(i).contains(s)){
                position = i;
                break;
            }
        }
        return position;
    }

}
