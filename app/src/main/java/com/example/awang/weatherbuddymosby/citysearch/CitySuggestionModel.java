package com.example.awang.weatherbuddymosby.citysearch;


/**
 * Created by awang on 2016-08-08.
 */
public interface CitySuggestionModel {
    void loadCitySuggestionData(String typedText, CitySearchPresenter.LoadCitySuggestionListener listener);
}
