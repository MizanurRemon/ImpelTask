package com.example.impeltask.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.impeltask.R;
import com.example.impeltask.databinding.FragmentNewsBinding;
import com.example.impeltask.databinding.FragmentNewsDetailsBinding;

public class Fragment_news_details extends Fragment {

    FragmentNewsDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init_view(view);

        //get_data();

        return view;
    }

    private void init_view(View view) {
    }
}