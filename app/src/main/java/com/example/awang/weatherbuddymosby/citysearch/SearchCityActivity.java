package com.example.awang.weatherbuddymosby.citysearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.awang.weatherbuddymosby.R;
import com.example.awang.weatherbuddymosby.weathershow.WeatherShowActivity;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-08.
 */
public class SearchCityActivity extends MvpActivity<SearchCityView, CitySearchPresenterImpl>
                                implements SearchCityView{


    @BindView(R.id.recyclerview_city_suggestion) RecyclerView recyclerViewCitySearchSuggestion;
    @BindView(R.id.searchView) SearchView searchView;

    CitySearchSuggestionAdapter citySearchSuggestionAdapter;

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, SearchCityActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcity);
        ButterKnife.bind(this);

        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length()>=2){
                    recyclerViewCitySearchSuggestion.setVisibility(View.VISIBLE);
                    getPresenter().loadCitySuggestionData(s);
                }else{
                    recyclerViewCitySearchSuggestion.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        recyclerViewCitySearchSuggestion.setLayoutManager(new LinearLayoutManager(this));
        citySearchSuggestionAdapter = new CitySearchSuggestionAdapter();
        recyclerViewCitySearchSuggestion.setAdapter(citySearchSuggestionAdapter);
        citySearchSuggestionAdapter.setOnItemClickListener(new CitySearchSuggestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                final String cityNameSelected = citySearchSuggestionAdapter.getCitySuggestionArray().get(position);
                String[] citySelected = cityNameSelected.replace(" ", "").split(",");
                int cityLength= citySelected.length;
                String cityName = citySelected[0] + "," + citySelected[cityLength-1];
                intent.putExtra("cityName", cityName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @NonNull
    @Override
    public CitySearchPresenterImpl createPresenter() {
        return new CitySearchPresenterImpl();
    }

    @Override
    public void getDataFailure(String s) {
//        Toast.makeText(this, getString(R.string.fetch_city_suggestion_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(final List<String> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "suggestion size " + list.size(), Toast.LENGTH_SHORT).show();
                citySearchSuggestionAdapter.setCitySuggestionArray(list);
                citySearchSuggestionAdapter.notifyDataSetChanged();
            }
        });
    }
}










