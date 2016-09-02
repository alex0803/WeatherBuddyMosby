package com.example.awang.weatherbuddymosby.citysearch;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

/**
 * Created by awang on 2016-08-08.
 */
public class CitySearchPresenterImpl extends MvpBasePresenter<SearchCityView>
                                    implements CitySearchPresenter {
    @Override
    public void loadCitySuggestionData(String typedText) {
        LoadCitySuggestionListener listener = new LoadCitySuggestionListener() {
            @Override
            public void onSuccess(List<String> list) {
                SearchCityView view = getView();
                if (view != null){
                    view.setData(list);
                }
            }

            @Override
            public void onFailure(String msg, Exception e) {
                SearchCityView view = getView();
                if(view!=null){
                    view.getDataFailure(msg);
                }
            }
        };
        new CitySuggestionModelImpl().loadCitySuggestionData(typedText, listener);
    }
}
