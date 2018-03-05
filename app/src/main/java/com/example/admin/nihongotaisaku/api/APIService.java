package com.example.admin.nihongotaisaku.api;

import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.ResultUser;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    //The register call
    @FormUrlEncoded
    @POST("api/registrations.json")
    Call<ResultUser> createUser(
        @Field("user[name]") String name,
        @Field("user[email]") String email,
        @Field("user[password]") String password,
        @Field("user[password_confirmation]") String password_confirmation
    );

    //The signin call
    @FormUrlEncoded
    @POST("api/sessions.json")
    Call<ResultUser> userLogin(
            @Field("user[email]") String email,
            @Field("user[password]") String password
    );

    //The logout call
    @FormUrlEncoded
    @POST("api/sessions/{id}.json")
    Call<ResultUser> userLogout(
            @Path("id") int id,
            @Field("user[email]") String email,
            @Field("user[authentication_token]") String authentication_token,
            @Field("_method") String _method
    );
}
