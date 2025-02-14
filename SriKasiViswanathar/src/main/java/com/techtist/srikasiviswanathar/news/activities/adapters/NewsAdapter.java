package com.techtist.srikasiviswanathar.news.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.news.activities.models.NewsResponse;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsResponse.NewsData> newsList;

    public NewsAdapter(List<NewsResponse.NewsData> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsResponse.NewsData newsData = newsList.get(position);
        System.out.println("newsData.getTitle() : "+newsData.getTitle());
        holder.tvTitle.setText(newsData.getTitle());
        holder.tvDescription.setText(newsData.getDescription());
        holder.tvExpireDate.setText("Expires on: " + newsData.getExpiredate());
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvExpireDate;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvDescription = itemView.findViewById(R.id.tvNewsDescription);
            tvExpireDate = itemView.findViewById(R.id.tvExpireDate);
        }
    }
}
