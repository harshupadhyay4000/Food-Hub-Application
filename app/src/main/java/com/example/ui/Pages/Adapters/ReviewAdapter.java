package com.example.ui.Pages.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Reviewlist;
import com.example.ui.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.View_review_Holder>{
    Context context;
    List<Reviewlist>reviewlists;

    LayoutInflater layoutInflater;

    public ReviewAdapter(Context context, List<Reviewlist> reviewlists) {
        this.context = context;
        this.reviewlists = reviewlists;
    }

    @NonNull
    @Override
    public View_review_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.reviews,parent,false);
        return new View_review_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_review_Holder holder, int position) {
        Reviewlist reviewlist=reviewlists.get(position);

        holder.imageView.setImageResource(reviewlist.getImageResource());
        holder.reviewsdone.setText(reviewlist.getReviewdone());
        holder.name.setText(reviewlist.getName());
        holder.date.setText(reviewlist.getDate());

    }

    @Override
    public int getItemCount() {
        return reviewlists.size();
    }

    public static class View_review_Holder extends RecyclerView.ViewHolder {
        TextView reviewsdone, date, name;
        ImageView imageView;
        public View_review_Holder(@NonNull View itemView) {
            super(itemView);
            reviewsdone=itemView.findViewById(R.id.review_text_from_user);
            date=itemView.findViewById(R.id.date_text_reviews);
            imageView=itemView.findViewById(R.id.review_icon_user_for);
            name=itemView.findViewById(R.id.reviewer_name);

        }
    }
}
