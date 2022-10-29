package com.example.impeltask.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.impeltask.Adapter.News_adapter;
import com.example.impeltask.Model.Top_headline_response;
import com.example.impeltask.R;
import com.example.impeltask.Utils.API.APIService;
import com.example.impeltask.Utils.API.AppConfig;
import com.example.impeltask.Utils.Const.Constants;
import com.example.impeltask.Utils.Const.Urls;
import com.example.impeltask.View.Activity.News_details_activity;
import com.example.impeltask.databinding.FragmentNewsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class Fragment_news extends Fragment implements News_adapter.OnItemClickListener{

    FragmentNewsBinding binding;
    String apiKey;
    Constants constants;
    String country, category;
    APIService apiService;

    List<Top_headline_response.Article> itemList;
    News_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init_view(view);

        get_data();

        return view;
    }

    private void get_data() {
        apiService.getTopHeadLines(country, category, apiKey).enqueue(new Callback<Top_headline_response>() {
            @Override
            public void onResponse(Call<Top_headline_response> call, Response<Top_headline_response> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(getContext(), response.body().status, Toast.LENGTH_SHORT).show();
                    itemList = new ArrayList<>();
                    itemList = response.body().articles;
                    adapter = new News_adapter(itemList);
                    adapter.setOnItemClickListener(Fragment_news.this::OnItemClick);
                    binding.itemView.setAdapter(adapter);
                } else {

                }
            }

            @Override
            public void onFailure(Call<Top_headline_response> call, Throwable t) {

            }
        });
    }

    private void init_view(View view) {
        constants = new Constants();
        apiKey = constants.API_KEY;
        country = constants.country;
        category = constants.category;

        apiService = AppConfig.getRetrofit().create(APIService.class);

        binding.itemView.setHasFixedSize(true);
        binding.itemView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.itemView.setItemViewCacheSize(150);

    }

    @Override
    public void OnItemClick(int position) {
        Top_headline_response.Article response = itemList.get(position);
        //getActivity().getSupportFragmentManager().beginTransaction().replace()
        Intent intent = new Intent(getActivity(), News_details_activity.class);
        intent.putExtra("imageurl", response.urlToImage);
        intent.putExtra("author", response.author);
        intent.putExtra("title", response.title);
        intent.putExtra("content", response.content);
        intent.putExtra("date", response.publishedAt);
        intent.putExtra("source", response.source.name);
        startActivity(intent);
    }
}