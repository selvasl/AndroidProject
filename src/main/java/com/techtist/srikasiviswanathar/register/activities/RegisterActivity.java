package com.techtist.srikasiviswanathar.register.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.api.ApiInterface;
import com.techtist.srikasiviswanathar.register.models.ApartmentResponse;
import com.techtist.srikasiviswanathar.register.models.RegisterRequest;
import com.techtist.srikasiviswanathar.register.models.RegisterResponse;
import com.techtist.srikasiviswanathar.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private EditText edtvName, edtvMobileNumber, edtvEmail, edtvPassword;
    private Button btnRegister;
    private AutoCompleteTextView dropdownApartments, dropdownDoorNumbers;
    private TextView tvLoginLink;

    private List<String> apartmentList = new ArrayList<>();
    private List<String> doorNumberList = new ArrayList<>();
    private String selectedApartment = null;
    private String selectedDoorNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Set padding to avoid overlapping system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        fetchApartments();
    }

    private void initViews() {
        edtvName = findViewById(R.id.edtvName);
        edtvMobileNumber = findViewById(R.id.edtvMobileNumber);
        edtvEmail = findViewById(R.id.edtvEmail);
        edtvPassword = findViewById(R.id.edtvPassword);
        btnRegister = findViewById(R.id.btnRegister);
        dropdownApartments = findViewById(R.id.dropdownApartment);
        dropdownDoorNumbers = findViewById(R.id.dropdownDoorNumber);


        dropdownApartments.setOnItemClickListener((parent, view, position, id) -> {
            selectedApartment = apartmentList.get(position);
            fetchDoorNumbers(selectedApartment); // Fetch door numbers when apartment is selected
        });

        dropdownDoorNumbers.setOnItemClickListener((parent, view, position, id) -> {
            selectedDoorNumber = doorNumberList.get(position);
        });

        btnRegister.setOnClickListener(v -> checkRegistrationValidation());
    }

    private void checkRegistrationValidation() {
        if (edtvName.getText().toString().trim().isEmpty()) {
            edtvName.setError("Enter name");
            return;
        }
        if (edtvMobileNumber.getText().toString().trim().isEmpty()) {
            edtvMobileNumber.setError("Enter mobile number");
            return;
        }
        if (edtvEmail.getText().toString().trim().isEmpty()) {
            edtvEmail.setError("Enter email");
            return;
        }
        if (edtvPassword.getText().toString().trim().isEmpty()) {
            edtvPassword.setError("Enter password");
            return;
        }
        if (selectedApartment == null || selectedApartment.isEmpty()) {
            Utils.showDialog("Warning", "Select an apartment", this);
            return;
        }
        if (selectedDoorNumber == null || selectedDoorNumber.isEmpty()) {
            Utils.showDialog("Warning", "Select a door number", this);
            return;
        }

        registerUser();
    }

    private void registerUser() {
        showProgress(true);

        // Create a RegisterRequest object
        RegisterRequest registerRequest = new RegisterRequest();

        // Create a Data object and set its values
        RegisterRequest.Data data = new RegisterRequest.Data();
        data.setName(edtvName.getText().toString());
        data.setMobileNumber(edtvMobileNumber.getText().toString());
        data.setEmail(edtvEmail.getText().toString());
        data.setPassword(edtvPassword.getText().toString());
        data.setBlockName(selectedApartment);
        data.setDoorNo(selectedDoorNumber);

        // Set the Data object in the RegisterRequest
        registerRequest.setData(data);

        // Convert RegisterRequest to JSON string
        Gson gson = new Gson();
        String registerRequestJson = gson.toJson(registerRequest);

        // Call the API to register the user
        ApiInterface apiService = Utils.getInterfaceService();
        Call<RegisterResponse> call = apiService.registerUser(registerRequestJson);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                showProgress(false);
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.getStatus().equals("success")) {
                        Utils.showDialog("Success", "Registration successful", RegisterActivity.this);
                    } else {
                        Utils.showDialog("Error", "Registration failed", RegisterActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                showProgress(false);
                Utils.showDialog("Error", "Failed to register: " + t.getMessage(), RegisterActivity.this);
            }
        });
    }

    private void fetchApartments() {
        showProgress(true);
        ApiInterface apiService = Utils.getInterfaceService();
        Call<List<ApartmentResponse>> call = apiService.getBlocks();

        call.enqueue(new Callback<List<ApartmentResponse>>() {
            @Override
            public void onResponse(Call<List<ApartmentResponse>> call, Response<List<ApartmentResponse>> response) {
                showProgress(false);
                if (response.isSuccessful() && response.body() != null) {
                    apartmentList.clear();
                    for (ApartmentResponse apartment : response.body()) {
                        apartmentList.add(apartment.getBlockName());
                    }
                    showApartmentDropdown();
                } else {
                    Utils.showDialog("Error", "Failed to fetch apartments", RegisterActivity.this);
                }
            }

            @Override
            public void onFailure(Call<List<ApartmentResponse>> call, Throwable t) {
                showProgress(false);
                Utils.showDialog("Error", "Failed to fetch apartments: " + t.getMessage(), RegisterActivity.this);
            }
        });
    }

    private void fetchDoorNumbers(String blockName) {
        showProgress(true);
        ApiInterface apiService = Utils.getInterfaceService();
        Call<List<ApartmentResponse>> call = apiService.getDoorNumbers(blockName);

        call.enqueue(new Callback<List<ApartmentResponse>>() {
            @Override
            public void onResponse(Call<List<ApartmentResponse>> call, Response<List<ApartmentResponse>> response) {
                showProgress(false);
                if (response.isSuccessful() && response.body() != null) {
                    doorNumberList.clear();
                    for (ApartmentResponse door : response.body()) {
                        doorNumberList.add(door.getDoorNo());
                    }
                    showDoorNumberDropdown();
                } else {
                    Utils.showDialog("Error", "Failed to fetch door numbers", RegisterActivity.this);
                }
            }

            @Override
            public void onFailure(Call<List<ApartmentResponse>> call, Throwable t) {
                showProgress(false);
                Utils.showDialog("Error", "Failed to fetch door numbers: " + t.getMessage(), RegisterActivity.this);
            }
        });
    }

    private void showApartmentDropdown() {
        ArrayAdapter<String> apartmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, apartmentList);
        dropdownApartments.setAdapter(apartmentAdapter);
    }

    private void showDoorNumberDropdown() {
        ArrayAdapter<String> doorNumberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, doorNumberList);
        dropdownDoorNumbers.setAdapter(doorNumberAdapter);
    }

    private void showProgress(boolean show) {
        if (show) {
            if (dialog == null) {
                dialog = new SpotsDialog(this);
                dialog.setCancelable(false);
            }
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } else if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
