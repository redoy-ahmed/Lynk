package com.example.redoy.lynk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.application.ApiClient;
import com.example.redoy.lynk.application.RetrofitLynk;
import com.example.redoy.lynk.model.BusinessRegistration;
import com.example.redoy.lynk.model.BusinessRegistrationResponse;
import com.example.redoy.lynk.model.CategoryData;
import com.example.redoy.lynk.model.CategoryResponse;
import com.example.redoy.lynk.model.ThanaData;
import com.example.redoy.lynk.model.ThanaResponse;
import com.example.redoy.lynk.service.CustomSharedPreference;
import com.example.redoy.lynk.util.ConnectionStatus;
import com.example.redoy.lynk.util.CustomSweetAlertDialog;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class BusinessInformationRegistrationActivity extends AppCompatActivity implements Validator.ValidationListener {

    /*@NotEmpty
    @BindView(R.id.registration_edit_text_widget_full_name)
    EditText mEditTextFullName;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_email)
    EditText mEditTextEmail;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_password)
    TextView mTextViewPassword;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_confirm_password)
    TextView mTextViewConfirmPassword;*/

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_business_name)
    TextView mTextViewBusinessName;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_business_category)
    AutoCompleteTextView mTextViewBusinessCategory;

    /*@NotEmpty
    @BindView(R.id.registration_edit_text_widget_google_location)
    TextView mTextViewGoogleLocation;*/

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_business_location)
    TextView mTextViewBusinessLocation;

    /*@NotEmpty
    @BindView(R.id.registration_edit_text_widget_phone)
    TextView mTextViewPhone;*/

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_verified_phone)
    TextView mTextViewVerifiedPhone;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_thana)
    AutoCompleteTextView mTextViewThana;

    @BindView(R.id.registration_edit_text_widget_deals)
    TextView mTextViewDeals;

    @BindView(R.id.submit_button)
    Button mButtonSubmit;

    private Validator validator;

    private String mBusinessNameString, mBusinessCategoryString, mBusinessLocationString, mBusinessVerifiedPhoneString, mBusinessThanaString, mBusinessDealsString;
    private String token;

    String[] thanaArray;
    String[] categoryArray;

    private static final String TAG = BusinessRegistration.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_information_registration);
        getCategory();
        getThana();
        initializeWidgets();
    }

    private void initializeWidgets() {
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validator = new Validator(this);
        validator.setValidationListener(this);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessInformationRegistrationActivity.this.validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        if (ConnectionStatus.getInstance(this).isOnline()) {
            submitData();
        } else {
            showToast(getString(R.string.connection_msg1));
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void submitData() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(getApplicationContext(), "Posting...");
        dialog.show();

        CustomSharedPreference customSharedPreference = new CustomSharedPreference(getApplicationContext());
        token = customSharedPreference.getAccessToken();

        mBusinessNameString = mTextViewBusinessName.getText().toString();
        mBusinessCategoryString = mTextViewBusinessCategory.getText().toString();
        mBusinessLocationString = mTextViewBusinessLocation.getText().toString();
        mBusinessThanaString = mTextViewThana.getText().toString();
        mBusinessVerifiedPhoneString = mTextViewVerifiedPhone.getText().toString();
        mBusinessDealsString = mTextViewDeals.getText().toString();

        BusinessRegistration businessRegistration = new BusinessRegistration(mBusinessNameString, mBusinessCategoryString, mBusinessLocationString, mBusinessThanaString, mBusinessVerifiedPhoneString, mBusinessDealsString);

        if (token.length() > 0) {
            RetrofitLynk apiService = ApiClient.getLynkClient().create(RetrofitLynk.class);
            Call<BusinessRegistrationResponse> call = apiService.submitBusinessRegistration(token, businessRegistration);
            call.enqueue(new Callback<BusinessRegistrationResponse>() {
                @Override
                public void onResponse(final Response<BusinessRegistrationResponse> response, Retrofit retrofit) {
                    final BusinessRegistrationResponse businessRegistrationResponse = response.body();
                    if (businessRegistrationResponse.getSuccess() == true) {
                        final Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), businessRegistrationResponse.getMessage(), Toast.LENGTH_LONG).show();
                                handler.removeCallbacksAndMessages(true);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        };
                        handler.postDelayed(runnable, 100);
                    } else {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Review not Posted", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            dialog.dismiss();
            new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Unauthorized")
                    .setContentText("You Are not Logged In Please Log In to Register a Business")
                    .setConfirmText("OK")
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.cancel();
                        }
                    })
                    .show();
        }
    }

    private void getCategory() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Loading...");
        dialog.show();

        RetrofitLynk apiService = ApiClient.getLynkClientForThanaAndCategory().create(RetrofitLynk.class);
        Call<CategoryResponse> call = apiService.getCategoryResponse();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(final Response<CategoryResponse> response, Retrofit retrofit) {
                final CategoryResponse categoryResponse = response.body();
                final CategoryData[] categoryData = categoryResponse.getCategoryData();
                if (categoryData.length > 0) {
                    categoryArray = new String[categoryData.length];
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            for (int i = 0; i < categoryData.length; i++)
                                categoryArray[i] = categoryData[i].getCat_name();
                            handler.removeCallbacksAndMessages(true);
                            mTextViewBusinessCategory.setThreshold(1);
                            mTextViewBusinessCategory.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, categoryArray));
                        }
                    };
                    handler.postDelayed(runnable, 100);
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Review not Posted", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void getThana() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Loading...");
        dialog.show();

        RetrofitLynk apiService = ApiClient.getLynkClientForThanaAndCategory().create(RetrofitLynk.class);
        Call<ThanaResponse> call = apiService.getThanaResponse();
        call.enqueue(new Callback<ThanaResponse>() {
            @Override
            public void onResponse(final Response<ThanaResponse> response, Retrofit retrofit) {
                final ThanaResponse thanaResponse = response.body();
                final ThanaData[] thanaData = thanaResponse.getThanaData();
                if (thanaData.length > 0) {
                    thanaArray = new String[thanaData.length];
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            for (int i = 0; i < thanaData.length; i++)
                                thanaArray[i] = thanaData[i].getThana();
                            handler.removeCallbacksAndMessages(true);
                            mTextViewThana.setThreshold(1);
                            mTextViewThana.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, thanaArray));
                        }
                    };
                    handler.postDelayed(runnable, 100);
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Review not Posted", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
