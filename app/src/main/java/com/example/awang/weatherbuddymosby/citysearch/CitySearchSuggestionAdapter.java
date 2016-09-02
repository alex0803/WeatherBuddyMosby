package com.example.awang.weatherbuddymosby.citysearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.awang.weatherbuddymosby.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by awang on 2016-08-08.
 */
public class CitySearchSuggestionAdapter extends RecyclerView.Adapter<CitySearchSuggestionAdapter.ViewHolder> {

    private List<String> citySuggestionArray;

    public List<String> getCitySuggestionArray() {
        return citySuggestionArray;
    }

    public void setCitySuggestionArray(List<String> citySuggestionArray) {
        this.citySuggestionArray = citySuggestionArray;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textview_city_suggestion) TextView citySuggestion;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        if (citySuggestionArray!= null && citySuggestionArray.size()!= 0){
            return citySuggestionArray.size();
        }else {
            return 0;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_suggestion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.citySuggestion.setText(citySuggestionArray.get(position));

        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

        }

    }


    // define the onItemClickListener on RecyclerView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
     //   void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }


}
