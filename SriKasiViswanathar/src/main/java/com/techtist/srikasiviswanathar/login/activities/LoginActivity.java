package com.techtist.srikasiviswanathar.login.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.api.ApiCodeConfig;
import com.techtist.srikasiviswanathar.api.ApiInterface;
import com.techtist.srikasiviswanathar.forgotpassword.activities.activities.ForgotpasswordActivity;
import com.techtist.srikasiviswanathar.home.activities.HomeActivity;
import com.techtist.srikasiviswanathar.login.models.LoginRequest;
import com.techtist.srikasiviswanathar.login.models.LoginResponse;
import com.techtist.srikasiviswanathar.register.activities.RegisterActivity;
import com.techtist.srikasiviswanathar.utils.Config;
import com.techtist.srikasiviswanathar.utils.PreferencesSession;
import com.techtist.srikasiviswanathar.utils.Utils;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private AlertDialog dialog;
    private TextView tvForgotPassword;
    private EditText edtvMobileNumber, edtvPassword;
    private Button btnLoginButton, btnRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    private void initViews() {
        edtvMobileNumber = findViewById(R.id.edtvMobileNumber);
        edtvPassword = findViewById(R.id.edtvPassword);

        // Register button link
        btnRegisterButton = findViewById(R.id.btnRegisterButton);
        btnRegisterButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Forgot Password link
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
            startActivity(intent);
        });

        // Login button
        btnLoginButton = findViewById(R.id.btnLoginButton);
        btnLoginButton.setOnClickListener(view -> checkCustomerValidation());
    }

    private void checkCustomerValidation() {
        if (edtvMobileNumber.getText().toString().trim().isEmpty()) {
            edtvMobileNumber.setError("Enter Mobile Number");
            return;
        } else {
            edtvMobileNumber.setError(null);
        }

        if (edtvPassword.getText().toString().trim().isEmpty()) {
            edtvPassword.setError("Enter Password");
            return;
        } else {
            edtvPassword.setError(null);
        }

        if (Utils.isNetworkConnected(LoginActivity.this)) {
            loginAPI();
        } else {
            Utils.showDialog(getString(R.string.str_Warning_title), getString(R.string.str_internet), LoginActivity.this);
        }
    }

    private void loginAPI() {
        showProgress(true);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAppKey(ApiCodeConfig.APP_KEY);
        loginRequest.setAppVersion(ApiCodeConfig.APPVERSION_CODE);
        loginRequest.setApiCode(ApiCodeConfig.API_LOGIN_CODE);
        LoginRequest.Data loginRequestData = loginRequest.new Data();
        loginRequestData.setMobileNumber(edtvMobileNumber.getText().toString().trim());
        loginRequestData.setPassword(edtvPassword.getText().toString().trim());
        loginRequest.setData(loginRequestData);

        Gson GSONData = new Gson();
        Log.d("LoginRequest:", GSONData.toJson(loginRequest));

        ApiInterface mApiService = Utils.getInterfaceService();
        Call<LoginResponse> mService = mApiService.loginReguest(GSONData.toJson(loginRequest));
        mService.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                showProgress(false);
                if (response.body() == null) {
                    Utils.showDialog("Error", "Invalid response from server", LoginActivity.this);
                    return;
                }

                LoginResponse loginResponse = response.body();
                Log.d("LoginResponse:", loginResponse.toString());
                String returnedResponse = loginResponse.getResult().getApiStatus();

                if ("success".equalsIgnoreCase(returnedResponse)) {
                    PreferencesSession.saveStringSession(LoginActivity.this, Config.PREF_USER_MOBILE_NUMBER, loginResponse.getResult().getData().getMobileNumber());

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("Action", "OPEN_POST_MESSAGE");
                    startActivity(intent);
                    finish();
                } else {
                    Utils.showDialog("Login Failed", loginResponse.getResult().getData().getMessage(), LoginActivity.this);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showProgress(false);
                Utils.showDialog("Error", "Network Error: " + t.getMessage(), LoginActivity.this);
                call.cancel();
            }
        });
    }

    private void createDialog() {
        dialog = new SpotsDialog(this, R.style.CustomDialog);
    }

    private void showProgress(Boolean flag) {
        if (dialog != null) {
            if (flag) {
                dialog.show();
            } else {
                dialog.dismiss();
            }
        }
    }
}
