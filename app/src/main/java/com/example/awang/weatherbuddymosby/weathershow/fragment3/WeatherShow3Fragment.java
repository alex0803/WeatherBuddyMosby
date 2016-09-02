package com.example.awang.weatherbuddymosby.weathershow.fragment3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.awang.weatherbuddymosby.R;
import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;
import com.example.awang.weatherbuddymosby.weathershow.WeatherShowView;
import com.example.awang.weatherbuddymosby.weathershow.fragment1.Adapter1DataSetChangedEvent;
import com.example.awang.weatherbuddymosby.weathershow.fragment1.WeatherShow1Adapter;
import com.example.awang.weatherbuddymosby.weathershow.fragment1.WeatherShow1Presenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-11.
 */
public class WeatherShow3Fragment extends MvpLceFragment <SwipeRefreshLayout, List<WeatherInfoBean>, WeatherShowView, WeatherShow3Presenter>
                                    implements WeatherShowView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView_weather_show)
    RecyclerView recyclerView;
    private WeatherShow3Adapter adapter;
    private List<String> cityNameList;
    private List<WeatherInfoBean> weatherInfoList;

    public void setCityNameList(List<String> cityNameList) {
        this.cityNameList = cityNameList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weathershow, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new WeatherShow3Adapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        contentView.setOnRefreshListener(this);
        loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return getString(R.string.select_city_notice);
    }

    @Override
    public WeatherShow3Presenter createPresenter() {
        return new WeatherShow3Presenter();

    }

    @Override
    public void setData(final List<WeatherInfoBean> data) {
        weatherInfoList = data;
        adapter.setWeatherInfoList(weatherInfoList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadDataInfo(pullToRefresh, cityNameList);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Register
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(Adapter1DataSetChangedEvent event) {
        weatherInfoList = event.getWeatherInfoList();
        setData(weatherInfoList);
    }

}

