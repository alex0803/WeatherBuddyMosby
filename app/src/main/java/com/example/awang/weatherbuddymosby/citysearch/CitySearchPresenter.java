package com.example.awang.weatherbuddymosby.citysearch;

import java.util.List;

/**
 * Created by awang on 2016-08-08.
 */
public interface CitySearchPresenter {

        interface LoadCitySuggestionListener {
        void onSuccess(List<String> list);
        void onFailure(String msg, Exception e);
    }


    void loadCitySuggestionData(String typedText);
}
