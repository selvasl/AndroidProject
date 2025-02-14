package com.techtist.srikasiviswanathar.news.activities.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.api.ApiInterface;
import com.techtist.srikasiviswanathar.api.ApiCodeConfig;
import com.techtist.srikasiviswanathar.news.activities.models.NewsRequest;
import com.techtist.srikasiviswanathar.news.activities.models.NewsResponse;
import com.techtist.srikasiviswanathar.news.activities.adapters.NewsAdapter;
import com.techtist.srikasiviswanathar.utils.Utils;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private RecyclerView rvNewsList;
    private NewsAdapter newsAdapter;
    private TextView tvNoNews;
    private ArrayList<NewsResponse.NewsData> newsListArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Initialize views
        rvNewsList = findViewById(R.id.recyclerViewNews);
        rvNewsList.setHasFixedSize(true);
        tvNoNews = findViewById(R.id.tvNoNews);

        // Handling system bars padding dynamically
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            int statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            int navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;

            v.setPadding(v.getPaddingLeft(), statusBarHeight, v.getPaddingRight(), navBarHeight);
            return insets;
        });

        // Fetch news on activity start
        fetchNews();
    }

    private void fetchNews() {
        if (Utils.isNetworkConnected(NewsActivity.this)) {
            fetchNewsAPI();
        } else {
            Utils.showDialog(getString(R.string.str_Warning_title), getString(R.string.str_internet), NewsActivity.this);
        }
    }

    private void fetchNewsAPI() {
        showProgress(true);

        // Set up NewsRequest object
        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setAppKey(ApiCodeConfig.APP_KEY);
        newsRequest.setAppVersion(ApiCodeConfig.APPVERSION_CODE);
        newsRequest.setApiCode(ApiCodeConfig.API_NEWS_CODE);
        newsRequest.setData(newsRequest.new Data());

        Gson GSONData = new Gson();
        Log.d("NewsRequest:", GSONData.toJson(newsRequest));

        // API call
        ApiInterface mApiService = Utils.getInterfaceService();
        Call<NewsResponse> mService = mApiService.getActiveNews(GSONData.toJson(newsRequest));
        mService.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                showProgress(false);
                NewsResponse newsResponse = response.body();

                if (newsResponse != null && "success".equals(newsResponse.getResult().getApiStatus())) {
                    List<NewsResponse.NewsData> newsDataList = newsResponse.getResult().getData();
                    newsListArrayList = new ArrayList<>(newsDataList);

                    if (!newsDataList.isEmpty()) {
                        newsAdapter = new NewsAdapter(newsListArrayList);
                        rvNewsList.setLayoutManager(new LinearLayoutManager(NewsActivity.this, LinearLayoutManager.VERTICAL, false));
                        rvNewsList.setAdapter(newsAdapter);
                        rvNewsList.setVisibility(View.VISIBLE);
                        tvNoNews.setVisibility(View.GONE);
                    } else {
                        tvNoNews.setVisibility(View.VISIBLE);
                        rvNewsList.setVisibility(View.GONE);
                    }
                } else {
                    Utils.showDialog(getString(R.string.str_Warning_title), getString(R.string.str_Warning_title), NewsActivity.this);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                call.cancel();
                showProgress(false);
                Log.e("NewsActivity", "Error fetching news: " + t.getMessage());
            }
        });
    }

    private void showProgress(Boolean flag) {
        if (dialog == null) {
            dialog = new SpotsDialog(this, R.style.CustomDialog);
        }
        if (flag) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }
}
