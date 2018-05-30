package com.example.admin.nihongotaisaku.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.activities.AlphabetImageActivity;
import com.example.admin.nihongotaisaku.adapters.AlphabetImageAdapter;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.AlphabetModel;
import com.example.admin.nihongotaisaku.models.HistoryModel;
import com.example.admin.nihongotaisaku.models.ResultUserAlphabet;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlphabetFragment extends Fragment{
    AlphabetImageAdapter listAlphabetImageAdapter;
    RecyclerView rclAlphabets;;
    private int classify;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_alphabets, container, false);
        classify = getArguments().getInt("classify");

        rclAlphabets = (RecyclerView) view.findViewById(R.id.rclAlphabets);
        rclAlphabets.setHasFixedSize(true);
        rclAlphabets.setLayoutManager(new GridLayoutManager(getContext(), 5));
        listAlphabetImageAdapter = new AlphabetImageAdapter();

        getListAlphabetLocal(classify);

        return view;
    }

    private void getListAlphabetLocal(int classify){
        Call<ArrayList<AlphabetModel>> call_alphabets = (new APIRetrofit()).getAPIService().getAlphabetsService(
                SharedPrefManager.getInstance(getContext()).getUser().getEmail(),
                SharedPrefManager.getInstance(getContext()).getUser().getAuthentication_token(),
                classify
        );
        call_alphabets.enqueue(new Callback<ArrayList<AlphabetModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AlphabetModel>> call, Response<ArrayList<AlphabetModel>> response) {
                listAlphabetImageAdapter = new AlphabetImageAdapter((Activity) getActivity(), response.body(), classify);
                rclAlphabets.setAdapter(listAlphabetImageAdapter);

                LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(
                        getContext(), R.anim.layout_slide_from_bottom);
                rclAlphabets.setLayoutAnimation(animationController);
                rclAlphabets.getAdapter().notifyDataSetChanged();
                rclAlphabets.scheduleLayoutAnimation();

                listAlphabetImageAdapter.setOnItemClick(new AlphabetImageAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        if (classify == 0 || classify == 1) {
                            if ((position != 36) && (position != 38)
                                    && (position != 46) && (position != 47)
                                    && (position != 48) && (position != 50)
                                    && (position != 51) && (position != 52)
                                    && (position != 53)) {
                                createUserAlphabetLocal(response.body().get(position).getId());
                                createHistoryAlphabetLocal(response.body().get(position).getId(),
                                        response.body().get(position).getJapanese());
                                Intent intent_to_hiragana = new Intent(getContext(), AlphabetImageActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("alphabet_id_data", response.body().get(position).getId());
                                bundle.putString("alphabet_japanese_data", response.body().get(position).getJapanese());
                                intent_to_hiragana.putExtra("alphabet_data", bundle);
                                startActivity(intent_to_hiragana);
                            }
                        } else if (classify == 2 || classify == 3){
                            if ((position != 26) && (position != 28)
                                    && (position != 31) && (position != 33)
                                    && (position != 36) && (position != 38)
                                    && (position != 41) && (position != 43)
                                    && (position != 46) && (position != 48)
                                    && (position != 51) && (position != 53)
                                    && (position != 56) && (position != 58)
                                    && (position != 61) && (position != 63)
                                    && (position != 66) && (position != 68)
                                    && (position != 71) && (position != 73)
                                    && (position != 76) && (position != 78)) {
                                createUserAlphabetLocal(response.body().get(position).getId());
                                createHistoryAlphabetLocal(response.body().get(position).getId(),
                                        response.body().get(position).getJapanese());
                                Intent intent_to_hiragana = new Intent(getContext(), AlphabetImageActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("alphabet_id_data", response.body().get(position).getId());
                                bundle.putString("alphabet_japanese_data", response.body().get(position).getJapanese());
                                intent_to_hiragana.putExtra("alphabet_data", bundle);
                                startActivity(intent_to_hiragana);
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<AlphabetModel>> call, Throwable t) {

            }
        });
    }

    // Create user_alphabet
    private void createUserAlphabetLocal(int alphabetID){
        Call<ResultUserAlphabet> call_user_alphabet = (new APIRetrofit()).getAPIService().createUserAlphabetService(
                SharedPrefManager.getInstance(getContext()).getUser().getEmail(),
                SharedPrefManager.getInstance(getContext()).getUser().getAuthentication_token(),
                alphabetID);
        call_user_alphabet.enqueue(new Callback<ResultUserAlphabet>() {
            @Override
            public void onResponse(Call<ResultUserAlphabet> call, Response<ResultUserAlphabet> response) {

            }

            @Override
            public void onFailure(Call<ResultUserAlphabet> call, Throwable t) {

            }
        });
    }

    // Create history alphabet
    private void createHistoryAlphabetLocal(int alphabetID, String alphabet_content){
        Call<HistoryModel> call_history_lesson = (new APIRetrofit()).getAPIService().createHistoryService(
                SharedPrefManager.getInstance(getContext()).getUser().getEmail(),
                SharedPrefManager.getInstance(getContext()).getUser().getAuthentication_token(),
                "alphabet", alphabetID, alphabet_content);
        call_history_lesson.enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {

            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {

            }
        });
    }
}
