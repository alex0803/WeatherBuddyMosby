package com.example.awang.weatherbuddymosby.citysearch;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by awang on 2016-08-08.
 */
public interface SearchCityView extends MvpView{
    void setData(List<String> list);
    void getDataFailure(String s);
}
