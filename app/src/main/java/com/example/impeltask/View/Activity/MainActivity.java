package com.example.impeltask.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.impeltask.Adapter.ViewPageAdapter;
import com.example.impeltask.R;
import com.example.impeltask.View.Fragment.Fragment_bookmarks;
import com.example.impeltask.View.Fragment.Fragment_news;
import com.example.impeltask.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new Fragment_news(), "News");
        viewPageAdapter.addFragment(new Fragment_bookmarks(), "Bookmarks");

        binding.viewPager.setAdapter(viewPageAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.getTabAt(0).select();
    }


}