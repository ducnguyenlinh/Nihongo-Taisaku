package com.example.admin.nihongotaisaku.api;

import com.example.admin.nihongotaisaku.models.AlphabetImageModel;
import com.example.admin.nihongotaisaku.models.AlphabetModel;
import com.example.admin.nihongotaisaku.models.AlphabetWritingModel;
import com.example.admin.nihongotaisaku.models.FeedbackModel;
import com.example.admin.nihongotaisaku.models.HistoryModel;
import com.example.admin.nihongotaisaku.models.LessonModel;
import com.example.admin.nihongotaisaku.models.ResultUser;
import com.example.admin.nihongotaisaku.models.ResultUserLesson;
import com.example.admin.nihongotaisaku.models.SentenceModel;
import com.example.admin.nihongotaisaku.models.VocabularyModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    //GET alphabets
    @GET("api/alphabets.json")
    Call<ArrayList<AlphabetModel>> getAlphabetsService(
            @Query("user_email") String email,
            @Query("user_token]") String token,
            @Query("classify") int classify
    );

    //GET alphabet image
    @GET("api/alphabet_images/{id}.json")
    Call<AlphabetImageModel> getAlphabetImagesService(
            @Path("id") int id,
            @Query("user_email") String email,
            @Query("user_token") String token
    );

    //GET alphabet writing
    @GET("api/alphabet_writings/{id}.json")
    Call<AlphabetWritingModel> getAlphabetWritingService(
            @Path("id") int id,
            @Query("user_email") String email,
            @Query("user_token") String token
    );

    //POST history
    @FormUrlEncoded
    @POST("api/histories.json")
    Call<HistoryModel> createHistoryService(
            @Field("user_email") String email,
            @Field("user_token") String token,
            @Field("object_class") String object_class,
            @Field("object_id") int object_id,
            @Field("object_content") String object_content
    );

    //GET histories
    @GET("api/histories.json")
    Call<ArrayList<HistoryModel>> getHistoriesService(
            @Query("user_email") String email,
            @Query("user_token") String token,
            @Query("object_class") String object_class
    );

    //GET lessons
    @GET("api/lessons.json")
    Call<ArrayList<LessonModel>> getLessonsService(
            @Query("user_email") String email,
            @Query("user_token") String token,
            @Query("classify") int classify
    );

    //POST user_lessons
    @FormUrlEncoded
    @POST("api/user_lessons.json")
    Call<ResultUserLesson> createUserLessonService(
        @Field("user_email") String email,
        @Field("user_token]") String token,
        @Field("lesson_id") int lesson_id
    );

    //INDEX vocabularies
    @GET("api/vocabularies.json")
    Call<ArrayList<VocabularyModel>> getVocabulariesService(
            @Query("user_email") String email,
            @Query("user_token") String token,
            @Query("lesson_id") int lesson_id
    );

    //UPDATE vocabulary
    @Multipart
    @PUT("api/vocabularies/{id}.json")
    Call<VocabularyModel> updateVocabularyService(
            @Path("id") int id,
            @Query("user_email") String email,
            @Query("user_token") String token,
            @Part("is_learn") int is_learn
    );

    //SHOW vocabulary
    @GET("api/vocabularies/{id}.json")
    Call<VocabularyModel> getVocabularyService(
            @Path("id") int id,
            @Query("user_email") String email,
            @Query("user_token") String token
    );

    //INDEX sentences
    @GET("api/sentences.json")
    Call<ArrayList<SentenceModel>> getSentencesService(
            @Query("user_email") String email,
            @Query("user_token") String token,
            @Query("vocabulary_id") int vocabulary_id
    );

    //POST history
    @FormUrlEncoded
    @POST("api/feedbacks.json")
    Call<FeedbackModel> createFeedbackService(
            @Field("user_email") String email,
            @Field("user_token") String token,
            @Field("object_class") String object_class,
            @Field("object_id") int object_id,
            @Field("content") String content
    );

    //GET feedbacks
    //GET histories
    @GET("api/feedbacks.json")
    Call<ArrayList<FeedbackModel>> getFeedbacksService(
            @Query("user_email") String email,
            @Query("user_token") String token,
            @Query("object_class") String object_class,
            @Query("object_id") int object_id
    );
}
