package com.example.uas_0128.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.uas_0128.ApiClient;
import com.example.uas_0128.ApiService;
import com.example.uas_0128.Match;
import com.example.uas_0128.MatchResponse;
import com.example.uas_0128.NextMatchAdapter;
import com.example.uas_0128.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextMatchFragment extends Fragment {

    private ArrayList<Match> listMatch;
    private RecyclerView rvMatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next_match, container, false);
        rvMatch = view.findViewById(R.id.rv_nextmatch);
        rvMatch.setHasFixedSize(true);
        rvMatch.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<MatchResponse> call = service.getNextMatch("4328");
        call.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {

                listMatch = response.body().getEvents();

                NextMatchAdapter nextmatchAdapter = new NextMatchAdapter(listMatch);
                rvMatch.setAdapter(nextmatchAdapter);
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {

            }
        });



        return view;
    }
}