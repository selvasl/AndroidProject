package com.techtist.srikasiviswanathar.login.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;  // Import Button
import android.widget.EditText;
import android.widget.TextView;  // Import TextView

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.api.ApiCodeConfig;
import com.techtist.srikasiviswanathar.api.ApiInterface;
import com.techtist.srikasiviswanathar.forgotpassword.activities.ForgotpasswordActivity;
import com.techtist.srikasiviswanathar.home.activities.HomeActivity;
import com.techtist.srikasiviswanathar.login.models.LoginRequest;
import com.techtist.srikasiviswanathar.login.models.LoginResponse;
import com.techtist.srikasiviswanathar.register.RegisterActivity;
import com.techtist.srikasiviswanathar.utils.Config;
import com.techtist.srikasiviswanathar.utils.PreferencesSession;
import com.techtist.srikasiviswanathar.utils.Utils;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private AlertDialog dialog;
    private TextView tvForgotPassword, tvRegisterLink;
    private EditText edtvMobileNumber,edtvPassword;
    private Button btnLoginButton;
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
        tvRegisterLink = findViewById(R.id.tvRegisterLink);
        tvRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLoginButton=findViewById(R.id.btnLoginButton);
        btnLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCustomerValidation();
            }
        });
        TextView forgotPasswordTextView = findViewById(R.id.tvForgotPassword);
        forgotPasswordTextView.setOnClickListener(v -> {
            // Intent to navigate to ForgotPasswordActivity
            Intent intent = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
            startActivity(intent);
        });
    }
    private void checkCustomerValidation() {
        if (edtvMobileNumber.getText().toString().length() <= 0) {
            //tilLoginUsername.setError(getResources().getText(R.string.str_login_error_mobile));
            edtvMobileNumber.setError(" ");
            return;
        } else {
            edtvMobileNumber.setError(null);
        }
        if (edtvPassword.getText().toString().length() <= 0) {
            edtvPassword.setError(" ");
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
        loginRequestData.setMobileNumber(edtvMobileNumber.getText().toString());
        loginRequestData.setPassword(edtvPassword.getText().toString());
        loginRequest.setData(loginRequestData);

        Gson GSONData = new Gson();

        Log.d("LoginRequest:", GSONData.toJson(loginRequest));

        ApiInterface mApiService = Utils.getInterfaceService();
        Call<LoginResponse> mService = mApiService.loginReguest(GSONData.toJson(loginRequest));
        mService.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                Log.d("LoginResponse:", loginResponse.toString());
                String returnedResponse = loginResponse.getResult().getApiStatus();

                showProgress(false);
                if (returnedResponse.trim().equals("success")) {
                    System.out.println("loginResponse.getResult().getData().getPassword() : "+loginResponse.getResult().getData().getpassword());
                    PreferencesSession.saveStringSession(LoginActivity.this, Config.PREF_USER_MOBILE_NUMBER, loginResponse.getResult().getData().getMobileNumber());

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("Action", "OPEN_POST_MESSAGE");
                    startActivity(intent);
                    finish();
                } else if (returnedResponse.trim().equals("fail")) {
                    //  showProgress(false);
                    System.out.println("loginResponse.getReason() : " + loginResponse.getResult().getData().getReason());
                    System.out.println("loginResponse.getMessage() : " + loginResponse.getResult().getData().getMessage());

                    Utils.showDialog(getResources().getString(R.string.login_error_popup_dialog_title), getResources().getString(R.string.login_error_popup_dialog_message), LoginActivity.this);


                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                call.cancel();
                System.out.println("onFailure : " + t.toString());
                }
        });
    }
    private void createDialog() {
        dialog = new SpotsDialog(this, R.style.CustomDialog);

    }

    private void showProgress(Boolean flag) {
        if (dialog != null) {
            if (flag == true) {
                dialog.show();
            } else if (flag == false) {
                dialog.dismiss();
            }
        }
    }
}
