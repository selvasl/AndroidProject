package com.techtist.srikasiviswanathar.forgotpassword.activities.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.api.ApiInterface;
import com.techtist.srikasiviswanathar.api.ApiCodeConfig;
import com.techtist.srikasiviswanathar.forgotpassword.activities.models.ForgotpasswordRequest;
import com.techtist.srikasiviswanathar.forgotpassword.activities.models.ForgotpasswordResponse;
import com.techtist.srikasiviswanathar.login.activities.LoginActivity;
import com.techtist.srikasiviswanathar.utils.Utils;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotpasswordActivity extends AppCompatActivity {

    private EditText edtMobile, edtPassword;
    private Button btnSubmit;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtMobile = findViewById(R.id.edtmobile);
        edtPassword = findViewById(R.id.edtPassword);
        btnSubmit = findViewById(R.id.btnreset);

        btnSubmit.setOnClickListener(v -> {
            String mobileNumber = edtMobile.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (!mobileNumber.isEmpty() && !password.isEmpty() && Utils.isValidMobileNumber(mobileNumber)) {
                sendForgotPasswordRequest(mobileNumber, password);
            } else {
                Utils.showDialog(getString(R.string.str_Warning_title), getString(R.string.str_invalid_mobile), this);
            }
        });
    }

    private void sendForgotPasswordRequest(String mobileNumber, String password) {
        showProgress(true);

        ForgotpasswordRequest request = new ForgotpasswordRequest();
        request.setAppKey(ApiCodeConfig.APP_KEY);
        request.setAppVersion(ApiCodeConfig.APPVERSION_CODE);
        request.setApiCode(ApiCodeConfig.API_FORGOT_PASSWORD_CODE);

        ForgotpasswordRequest.Data requestData = request.new Data();
        requestData.setMobile_number(mobileNumber);
        requestData.setPassword(password);
        request.setData(requestData);

        ApiInterface apiService = Utils.getInterfaceService();
        Call<ForgotpasswordResponse> call = apiService.forgotPassword(new Gson().toJson(request));
        call.enqueue(new Callback<ForgotpasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotpasswordResponse> call, Response<ForgotpasswordResponse> response) {
                showProgress(false);
                ForgotpasswordResponse forgotPasswordResponse = response.body();
                if (forgotPasswordResponse != null && "success".equals(forgotPasswordResponse.getResult().getApiStatus())) {
                    Utils.showDialog(getString(R.string.str_Success_title), getString(R.string.str_password_reset_success), ForgotpasswordActivity.this);

                    // Redirect to LoginActivity after success
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(ForgotpasswordActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }, 2000); // 2-second delay for user to read the message
                } else {
                    Utils.showDialog(getString(R.string.str_Warning_title), getString(R.string.str_password_reset_failed), ForgotpasswordActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ForgotpasswordResponse> call, Throwable t) {
                showProgress(false);
                Log.e("ForgotPassword", "Error: " + t.getMessage());
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
