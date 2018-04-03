package com.example.redoy.lynk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.util.ConnectionStatus;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessInformationRegistrationActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
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
    TextView mTextViewConfirmPassword;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_business_name)
    TextView mTextViewBusinessName;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_business_category)
    TextView mTextViewBusinessCategory;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_google_location)
    TextView mTextViewGoogleLocation;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_business_location)
    TextView mTextViewBusinessLocation;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_phone)
    TextView mTextViewPhone;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_verified_phone)
    TextView mTextViewVerifiedPhone;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_thana)
    TextView mTextViewThana;

    @NotEmpty
    @BindView(R.id.registration_edit_text_widget_description)
    TextView mTextViewDescription;

    @BindView(R.id.submit_button)
    Button mButtonSubmit;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_information_registration);
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
        startActivity(new Intent(this, MainActivity.class));
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
