package com.example.awang.weatherbuddymosby.weathershow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.awang.weatherbuddymosby.R;

import com.example.awang.weatherbuddymosby.citysearch.SearchCityActivity;
import com.example.awang.weatherbuddymosby.weathershow.fragment1.WeatherShow1Fragment;
import com.example.awang.weatherbuddymosby.weathershow.fragment3.WeatherShow3Fragment;
import com.example.awang.weatherbuddymosby.weathershow.fragment5.WeatherShow5Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-08.
 */
public class WeatherShowActivity extends AppCompatActivity{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;
    @BindView(R.id.tabs) TabLayout tabLayout;

    private final static int SearchCityActivity_REQUEST = 1;
    private List<String> cityNameList = new ArrayList<>();
    private List<WeatherInfoBean> weatherInfoList = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathershow);
        ButterKnife.bind(this);

        //set toolbar
        toolbar.setLogo(R.drawable.ic_toolbar);
        toolbar.setTitle(R.string.toobar_title);
        setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchCityActivity.createIntent(WeatherShowActivity.this);
                startActivityForResult(intent, SearchCityActivity_REQUEST);
            }
        });

        loadCityNameList(getBaseContext());
        if (cityNameList == null || cityNameList.size() == 0){



        }
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveCityNameList(getBaseContext());
    }

    public void setupViewPager(final ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        WeatherShow1Fragment weatherShow1Fragment = new WeatherShow1Fragment();
        weatherShow1Fragment.setCityNameList(cityNameList);
        adapter.addFragment(weatherShow1Fragment, "Today");

        WeatherShow3Fragment weatherShow3Fragment = new WeatherShow3Fragment();
        weatherShow3Fragment.setCityNameList(cityNameList);
        adapter.addFragment(weatherShow3Fragment, "3 days");

        WeatherShow5Fragment weatherShow5Fragment = new WeatherShow5Fragment();
        weatherShow5Fragment.setCityNameList(cityNameList);
        adapter.addFragment(weatherShow5Fragment, "5 days");

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SearchCityActivity_REQUEST:
                if (resultCode == RESULT_OK){
                    String cityName = data.getStringExtra("cityName");
                    if (cityNameList.contains(cityName)) {
                        Toast.makeText(WeatherShowActivity.this, cityName + "is already in the list", Toast.LENGTH_SHORT).show();
                    } else if (cityNameList.size() >= 5) {
                        Toast.makeText(WeatherShowActivity.this, "Out of capacity, place limitation is 5", Toast.LENGTH_SHORT).show();
                    } else {
                        cityNameList.add(cityName);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            default:
        }
    }

    public void loadCityNameList(Context mContext){
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
        int size = mSharedPreference.getInt("cityNameList_size", 0);

        cityNameList.clear();
        for (int i = 0; i<size; i++){
            cityNameList.add(mSharedPreference.getString("cityNameList_" + i, null));
        }
    }

    public void saveCityNameList(Context mContext){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        editor.putInt("cityNameList_size", cityNameList.size());

        for (int i = 0; i<cityNameList.size(); i++){
            editor.remove("cityNameList_" + i);
            editor.putString("cityNameList_" + i, cityNameList.get(i));
        }
        editor.commit();
    }
}
