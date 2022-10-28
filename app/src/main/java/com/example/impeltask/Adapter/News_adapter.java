package com.example.impeltask.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.titleText.setText(response.title);
        holder.headlineText.setText(response.description);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText, headlineText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleText = itemView.findViewById(R.id.titleText);
            headlineText = itemView.findViewById(R.id.headlineText);

        }
    }
}
