package com.example.admin.nihongotaisaku.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.api.APIUrl;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.ResultUser;
import com.example.admin.nihongotaisaku.models.User;
import com.example.admin.nihongotaisaku.api.APIService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.input_name) TextView _nameText;
    @BindView(R.id.input_email) TextView _emailText;
    @BindView(R.id.input_password) TextView _passwordText;
    @BindView(R.id.input_password_confirmation) TextView _confirmPasswordText;
    @BindView(R.id.btn_signup) Button _btnSignup;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()){
                    onSignupFailed();
                    return;
                }
                _btnSignup.setEnabled(false);

                userSignup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent_login);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    private void userSignup(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sign Up...");
        progressDialog.show();

        String name = _nameText.getText().toString().trim();
        String email = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();
        String password_confirmation = _confirmPasswordText.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //defining retrofit api service
        APIService service = retrofit.create(APIService.class);
        //defining the user object as we need to pass it with the call
        User user = new User(name, email, password, password_confirmation);
        //defining the call
        Call<ResultUser> call = service.createUser(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPassword_confirmation()
        );


        //calling the api
        call.enqueue(new Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                Log.d("###", response.body().getMessage());
                //hiding progress dialog
                progressDialog.dismiss();

                //displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                //if there is no error
                if (!response.body().getError()) {
                    //starting profile activity
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

//check validate
    public boolean validate(){
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirm_password = _confirmPasswordText.getText().toString();

        if (name.isEmpty() || name.length() <3){
            _nameText.setError("At least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("Enter a valaid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() <6 || password.length() >15){
            _passwordText.setError("Between 6 and 15 alphanumeric character");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (confirm_password.isEmpty() || confirm_password.length() <4 || confirm_password.length() >10
                || !(confirm_password.equals(password))){
            _confirmPasswordText.setError("Password do not match");
            valid = false;
        } else {
            _confirmPasswordText.setError(null);
        }
        return valid;
    }

    public void onSignupSuccess(){
        _btnSignup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    //message signup failed
    public void onSignupFailed(){
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _btnSignup.setEnabled(true);
    }
}
