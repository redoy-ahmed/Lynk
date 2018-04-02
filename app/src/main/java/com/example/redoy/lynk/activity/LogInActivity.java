package com.example.redoy.lynk.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.application.LynkApplication;
import com.example.redoy.lynk.service.CustomSharedPreference;
import com.example.redoy.lynk.util.ConnectionStatus;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements ValidationListener {

    @BindView(R.id.login_button)
    Button mButtonLogIn;

    @NotEmpty
    @BindView(R.id.login_edit_text_widget_email)
    EditText mEditTextEmail;

    @Password(scheme = Password.Scheme.ALPHA_NUMERIC, message = "Password should be Alpha Numeric")
    @NotEmpty
    @BindView(R.id.login_edit_text_widget_password)
    EditText mEditTextPassword;

    @BindView(R.id.login_text_view_forgot_password_button)
    TextView mTextViewForgotPassword;

    @BindView(R.id.remember_log_in_checkBox)
    CheckBox mCheckBoxRememberLogIn;

    String strEmail;
    String strPassword;
    private Validator validator;
    private CustomSharedPreference shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initializeWidgets();
    }

    private void initializeWidgets() {
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        shared = LynkApplication.getShared(getApplicationContext());

        if (shared.getIsRemember()) {
            mCheckBoxRememberLogIn.setChecked(true);
            mEditTextEmail.setText(shared.getRememberEmail().toString());
            mEditTextPassword.setText(shared.getRememberPassword().toString());
        }

        validator = new Validator(this);
        validator.setValidationListener(this);

        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    LogInActivity.this.validator.validate();
                }
            }
        });

        mTextViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForgotPassword();
            }
        });
    }

    private void getForgotPassword() {
        final EditText taskEditText;
        taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.reset_password_dialog_title)
                .setMessage(R.string.reset_password_email_address_label)
                .setView(taskEditText)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = String.valueOf(taskEditText.getText());
                        if (email.length() == 0) {
                            Toast.makeText(getApplicationContext(), "Please Check and Enter a valid Email Address", Toast.LENGTH_LONG).show();
                        } else {
                            resetPassword(email);
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private void resetPassword(String email) {
        Toast.makeText(getApplicationContext(), "Password reset Email sent", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidationSucceeded() {
        strEmail = mEditTextEmail.getText().toString();
        strPassword = mEditTextPassword.getText().toString();

        if (mCheckBoxRememberLogIn.isChecked()) {
            shared.saveIsRemember(true);
            shared.saveRemember(strEmail, strPassword);
        } else {
            shared.saveIsRemember(false);
        }

        if (ConnectionStatus.getInstance(this).isOnline()) {
            getLogIn();
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

    private void getLogIn() {
        shared.saveIsLogin(true);
        shared.saveLogin(strEmail, strPassword);
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
