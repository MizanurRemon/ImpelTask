package com.example.impeltask.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.impeltask.Model.Top_headline_response;
import com.example.impeltask.R;

import java.util.List;

public class News_adapter extends RecyclerView.Adapter<News_adapter.ViewHolder>{
    List<Top_headline_response.Article> itemList;

    public News_adapter(List<Top_headline_response.Article> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public News_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull News_adapter.ViewHolder holder, int position) {
        Top_headline_response.Article response = itemList.get(position);
        holder.titleText.setText(response.author);
        holder.headlineText.setText(response.title);

        Glide.with(holder.itemView.getContext()).load(response.urlToImage).placeholder(R.drawable.loader)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        return false;
                    }
                }).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText, headlineText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleText = itemView.findViewById(R.id.titleText);
            headlineText = itemView.findViewById(R.id.headlineText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
