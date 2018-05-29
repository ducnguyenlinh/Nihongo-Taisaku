package com.example.admin.nihongotaisaku.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.activities.HomeActivity;
import com.example.admin.nihongotaisaku.adapters.TextWatcherAdapter;
import com.example.admin.nihongotaisaku.api.APIService;
import com.example.admin.nihongotaisaku.api.APIUrl;
import com.example.admin.nihongotaisaku.helper.Rotate;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.helper.TextSizeTransition;
import com.example.admin.nihongotaisaku.models.ResultUser;
import com.example.admin.nihongotaisaku.models.User;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import java.util.List;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.BindViews;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpFragment extends AuthFragment {

    @BindViews(value = {R.id.name_input_edit,
            R.id.email_input_edit,
            R.id.password_input_edit,
            R.id.confirm_password_edit})
    protected List<TextInputEditText> views;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null){
            view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_sign_up));
            caption.setText(getString(R.string.sign_up_label));
            for(TextInputEditText editText:views){
                if(editText.getId()==R.id.password_input_edit){
                    final TextInputLayout inputLayout= ButterKnife.findById(view,R.id.password_input);
                    final TextInputLayout confirmLayout=ButterKnife.findById(view,R.id.confirm_password);
                    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                    inputLayout.setTypeface(boldTypeface);
                    confirmLayout.setTypeface(boldTypeface);
                    editText.addTextChangedListener(new TextWatcherAdapter(){
                        @Override
                        public void afterTextChanged(Editable editable) {
                            inputLayout.setPasswordVisibilityToggleEnabled(editable.length()>0);
                            confirmLayout.setPasswordVisibilityToggleEnabled(editable.length()>0);
                        }
                    });
                }
                editText.setOnFocusChangeListener((temp,hasFocus)->{
                    if(!hasFocus){
                        boolean isEnabled=editText.getText().length()>0;
                        editText.setSelected(isEnabled);
                    }
                });

                caption.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validate(views.get(0).getText().toString(), views.get(1).getText().toString()
                                ,views.get(2).getText().toString(), views.get(3).getText().toString())){
                            return;
                        }

                        Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
                        Signup();
                    }
                });
            }
            caption.setVerticalText(true);
            foldStuff();
            caption.setTranslationX(getTextPadding());
        }
    }

    @Override
    public int authLayout() {
        return R.layout.sign_up_fragment;
    }

    @Override
    public void clearFocus() {
        for(View view:views) view.clearFocus();
    }

    @Override
    public void fold() {
        lock=false;
        Rotate transition = new Rotate();
        transition.setEndAngle(-90f);
        transition.addTarget(caption);
        TransitionSet set=new TransitionSet();
        set.setDuration(getResources().getInteger(R.integer.duration));
        ChangeBounds changeBounds=new ChangeBounds();
        set.addTransition(changeBounds);
        set.addTransition(transition);
        TextSizeTransition sizeTransition=new TextSizeTransition();
        sizeTransition.addTarget(caption);
        set.addTransition(sizeTransition);
        set.setOrdering(TransitionSet.ORDERING_TOGETHER);
        set.addListener(new Transition.TransitionListenerAdapter(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                caption.setTranslationX(getTextPadding());
                caption.setRotation(0);
                caption.setVerticalText(true);
                caption.requestLayout();

            }
        });
        TransitionManager.beginDelayedTransition(parent,set);
        foldStuff();
        caption.setTranslationX(-caption.getWidth()/8+getTextPadding());
    }

    private void foldStuff(){
        caption.setTextSize(TypedValue.COMPLEX_UNIT_PX,caption.getTextSize()/2f);
        caption.setTextColor(Color.WHITE);
        ConstraintLayout.LayoutParams params=getParams();
        params.rightToRight=ConstraintLayout.LayoutParams.UNSET;
        params.verticalBias=0.5f;
        caption.setLayoutParams(params);
    }

    private float getTextPadding(){
        return getResources().getDimension(R.dimen.folded_label_padding)/2.1f;
    }


    private void Signup(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Sign Up...");
        progressDialog.show();

        String name = views.get(0).getText().toString().trim();
        String email = views.get(1).getText().toString().trim();
        String password = views.get(2).getText().toString().trim();
        String password_confirmation = views.get(3).getText().toString().trim();

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
        call.enqueue(new retrofit2.Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                Log.d("###", response.body().getMessage());
                //hiding progress dialog
                progressDialog.dismiss();

                //displaying the message from the response as toast
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                //if there is no error
                if (!response.body().getError()) {
                    //starting profile activity
                    SharedPrefManager.getInstance(getContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getContext(), HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    //check validate
    public boolean validate(String name, String email, String password, String confirm_password){
        boolean valid = true;

        if (name.isEmpty() || name.length() <3){
            views.get(0).setError("At least 3 characters");
            valid = false;
        } else {
            views.get(0).setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            views.get(1).setError("Enter a valaid email address");
            valid = false;
        } else {
            views.get(1).setError(null);
        }

        if (password.isEmpty() || password.length() <6 || password.length() >15){
            views.get(2).setError("Between 6 and 15 alphanumeric character");
            valid = false;
        } else {
            views.get(2).setError(null);
        }

        if (confirm_password.isEmpty() || confirm_password.length() <4 || confirm_password.length() >10
                || !(confirm_password.equals(password))){
            views.get(3).setError("Password do not match");
            valid = false;
        } else {
            views.get(3).setError(null);
        }
        return valid;
    }
}
