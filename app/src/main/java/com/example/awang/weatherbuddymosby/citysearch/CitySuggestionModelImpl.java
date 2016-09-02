package com.example.awang.weatherbuddymosby.citysearch;

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
 * Created by awang on 2016-08-08.
 */
public class CitySuggestionModelImpl implements CitySuggestionModel {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY_search_request = "AIzaSyCHNWQ8s3yEmUfEdCF88zlcWnsuRhuFMQg";

    @Override
    public void loadCitySuggestionData(String typedText, final CitySearchPresenter.LoadCitySuggestionListener listener) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String urlAddress = PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON
                            + "?input=" + typedText
                            + "&types=(cities)" + "&key=" + API_KEY_search_request;

        final Request request = new Request.Builder().url(urlAddress).build();
        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onFailure("load city suggestion failed", e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String jsonData = response.body().string();
                final CitySuggestionBean citySuggestionBean = new Gson().fromJson(jsonData, CitySuggestionBean.class);
                final List<String> listCitySuggestion = new ArrayList<>();

                int size = citySuggestionBean.getPredictions().size();
                for (int i=0; i<size; i++){
                    listCitySuggestion.add(citySuggestionBean.getPredictions().get(i).getDescription());
                }

                listener.onSuccess(listCitySuggestion);
            }
        });
    }
}
