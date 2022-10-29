package com.example.impeltask.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.impeltask.R;
import com.example.impeltask.databinding.ActivityNewsDetailsBinding;

import java.text.SimpleDateFormat;
import java.util.Date;


public class News_details_activity extends AppCompatActivity {

    ActivityNewsDetailsBinding binding;

    String imageUrl, title, author, publishedDate, content, source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init_view();

        load_data();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void load_data() {
        Glide.with(this).load(imageUrl).placeholder(R.drawable.loader)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        binding.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        return false;
                    }
                }).into(binding.imageView);

        binding.titleText.setText(title);

        binding.contentText.setText(content);
        String formattedDate = "";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
        try {
            Date date = inputFormat.parse(publishedDate);
            formattedDate = outputFormat.format(date);

            Log.d("dataxx", formattedDate);
        } catch (Exception e) {

            Log.d("dataxx", e.getMessage());
        }


        binding.authorText.setText(author+", "+formattedDate);
        binding.sourceText.setText(source);
    }

    private void init_view() {
        imageUrl = getIntent().getStringExtra("imageurl");
        author = getIntent().getStringExtra("author");
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        publishedDate = getIntent().getStringExtra("date");
        source = getIntent().getStringExtra("source");
    }
}