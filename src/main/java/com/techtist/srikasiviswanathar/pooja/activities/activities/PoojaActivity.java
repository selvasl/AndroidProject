package com.techtist.srikasiviswanathar.pooja.activities.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.gson.Gson;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.api.ApiInterface;
import com.techtist.srikasiviswanathar.api.ApiCodeConfig;
import com.techtist.srikasiviswanathar.home.activities.HomeActivity;
import com.techtist.srikasiviswanathar.pooja.activities.models.PoojaRequest;
import com.techtist.srikasiviswanathar.pooja.activities.models.PoojaResponse;
import com.techtist.srikasiviswanathar.pooja.activities.adapters.PoojaAdapter;
import com.techtist.srikasiviswanathar.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoojaActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private ListView lvPoojaList;
    private PoojaAdapter poojaAdapter;
    private TextView tvNoPooja;
    private Toolbar toolbar;
    private ArrayList<PoojaResponse.PoojaData> poojaListArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja);

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.getNavigationIcon().setTint(getResources().getColor(android.R.color.black)); // Change back button color

        // Apply Custom Font to Toolbar Title
        applyCustomFontToToolbar();

        lvPoojaList = findViewById(R.id.listViewPooja);
        tvNoPooja = findViewById(R.id.tvNoPooja);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            int statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            int navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            v.setPadding(v.getPaddingLeft(), statusBarHeight, v.getPaddingRight(), navBarHeight);
            return insets;
        });

        Log.d("PoojaActivity", "onCreate: Initialized components successfully");
        fetchPooja();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(PoojaActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        return true;
    }


    // Apply Custom Font to Toolbar Title
    private void applyCustomFontToToolbar() {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView titleTextView = (TextView) view;
                Typeface typeface = ResourcesCompat.getFont(this, R.font.poppins_semibold);
                titleTextView.setTypeface(typeface);
                break; // Exit loop once title is found
            }
        }
    }

    private void fetchPooja() {
        if (Utils.isNetworkConnected(PoojaActivity.this)) {
            Log.d("PoojaActivity", "Internet connection available, fetching data...");
            fetchPoojaAPI();
        } else {
            Log.e("PoojaActivity", "No internet connection.");
            Utils.showDialog(getString(R.string.str_Warning_title), getString(R.string.str_internet), PoojaActivity.this);
        }
    }

    private void fetchPoojaAPI() {
        showProgress(true);

        PoojaRequest poojaRequest = new PoojaRequest();
        poojaRequest.setAppKey(ApiCodeConfig.APP_KEY);
        poojaRequest.setAppVersion(ApiCodeConfig.APPVERSION_CODE);
        poojaRequest.setApiCode(ApiCodeConfig.API_POOJA_CODE);
        poojaRequest.setData(poojaRequest.new Data());

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(poojaRequest);
        Log.d("PoojaActivity", "Request Sent: " + jsonRequest);

        ApiInterface mApiService = Utils.getInterfaceService();
        Call<PoojaResponse> mService = mApiService.getPoojaDetails(jsonRequest);
        mService.enqueue(new Callback<PoojaResponse>() {
            @Override
            public void onResponse(Call<PoojaResponse> call, Response<PoojaResponse> response) {
                showProgress(false);

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("PoojaActivity", "Response Received: " + new Gson().toJson(response.body()));

                    PoojaResponse poojaResponse = response.body();
                    if (poojaResponse.getResult() != null && poojaResponse.getResult().getData() != null && !poojaResponse.getResult().getData().isEmpty()) {
                        List<PoojaResponse.PoojaData> poojaDataList = poojaResponse.getResult().getData();
                        updateListView(poojaDataList);
                    } else {
                        Log.e("PoojaActivity", "No data found in response");
                        showNoData();
                    }
                } else {
                    Log.e("PoojaActivity", "Response unsuccessful: " + response.code());
                    showNoData();
                }
            }

            @Override
            public void onFailure(Call<PoojaResponse> call, Throwable t) {
                showProgress(false);
                Log.e("PoojaActivity", "API Error: " + t.getMessage());
                showNoData();
            }
        });
    }

    private void updateListView(List<PoojaResponse.PoojaData> poojaDataList) {
        if (poojaDataList == null || poojaDataList.isEmpty()) {
            showNoData();
            return;
        }

        poojaListArrayList = new ArrayList<>(poojaDataList);
        poojaAdapter = new PoojaAdapter(this, poojaListArrayList);
        lvPoojaList.setAdapter(poojaAdapter);
        lvPoojaList.setVisibility(View.VISIBLE);
        tvNoPooja.setVisibility(View.GONE);
    }

    private void showNoData() {
        Log.w("PoojaActivity", "No pooja data available");
        lvPoojaList.setVisibility(View.GONE);
        tvNoPooja.setVisibility(View.VISIBLE);
    }

    private void showProgress(Boolean flag) {
        if (dialog == null) {
            dialog = new SpotsDialog(this, R.style.CustomDialog);
        }
        if (flag) {
            Log.d("PoojaActivity", "Showing progress dialog");
            dialog.show();
        } else {
            Log.d("PoojaActivity", "Hiding progress dialog");
            dialog.dismiss();
        }
    }
}
