package com.example.awang.weatherbuddymosby.weathershow.fragment1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.awang.weatherbuddymosby.R;
import com.example.awang.weatherbuddymosby.WeatherShowDetails1.WeatherShowDetails1;
import com.example.awang.weatherbuddymosby.weathershow.ViewPagerAdapter;
import com.example.awang.weatherbuddymosby.weathershow.WeatherInfoBean;
import com.example.awang.weatherbuddymosby.weathershow.WeatherShowView;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by awang on 2016-08-11.
 */
public class WeatherShow1Fragment extends MvpLceFragment<SwipeRefreshLayout, List<WeatherInfoBean>, WeatherShowView, WeatherShow1Presenter>
                                    implements WeatherShowView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView_weather_show) RecyclerView recyclerView;
    private WeatherShow1Adapter adapter;
    private List<String> cityNameList;
    private List<WeatherInfoBean> weatherInfoList;
    public void setCityNameList(List<String> cityNameList) {
        this.cityNameList = cityNameList;
    }

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weathershow, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new WeatherShow1Adapter(getContext());
        adapter.setOnItemClickListener(new WeatherShow1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ImageView icon, int position) {
                Intent intent = WeatherShowDetails1.createIntent(getContext(), cityNameList.get(position),
                        new Gson().toJson(weatherInfoList.get(position)));

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        new Pair<View, String>(icon, getString(R.string.transition_icon))/*,
                        new Pair<View, String>(view.findViewById(R.id.dateTextView1), getString(R.string.transition_date)),
                        new Pair<View, String>(view.findViewById(R.id.humidityTextView1), getString(R.string.transition_humidity)),
                        new Pair<View, String>(view.findViewById(R.id.windTextView1), getString(R.string.transition_speed)),
                        new Pair<View, String>(view.findViewById(R.id.tempTextView1), getString(R.string.transition_temp)),
                        new Pair<View, String>(view.findViewById(R.id.citynameTextView1), getString(R.string.transition_cityname))*/
                );
                ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setCancelable(true);
                String cityname = cityNameList.get(position);
                dialog.setMessage("Are you sure to remove " + cityname + " from the list?");
                dialog.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cityNameList.remove(position);
                        weatherInfoList.remove(position);
                        adapter.notifyItemRemoved(position);
                        EventBus.getDefault().post(new Adapter1DataSetChangedEvent(weatherInfoList));
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        contentView.setOnRefreshListener(this);
        loadData(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return getString(R.string.select_city_notice);
    }

    @Override
    public WeatherShow1Presenter createPresenter() {
        return new WeatherShow1Presenter();
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
        EventBus.getDefault().post(new Adapter1DataSetChangedEvent(weatherInfoList));
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
}