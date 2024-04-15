package com.example.ui.Pages.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Item;
import com.example.ui.Pages.FastFoods_Activity;
import com.example.ui.R;

import java.util.List;

public class PopularitemAdapter extends RecyclerView.Adapter<PopularitemAdapter.View_three_Holder>{
    Context context;
    List<Item>items;
    LayoutInflater layoutInflater;

    public PopularitemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View_three_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.items,parent,false);
        return new View_three_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_three_Holder holder, int position) {
        Item item=items.get(position);

        holder.imageView.setImageResource(item.getImageResource());
        holder.price.setText(item.getPrice());
        holder.name.setText(item.getName());
        holder.ratings.setText(item.getRatings());
        holder.outlets.setText(item.getOutlets());
        holder.description.setText(item.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FastFoods_Activity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class View_three_Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView price, ratings, outlets, name, description;
        ImageView imageView;
        public View_three_Holder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview_items);
            price=itemView.findViewById(R.id.itemprice_text);
            ratings=itemView.findViewById(R.id.ratings_text_items);
            outlets=itemView.findViewById(R.id.outlets_text_items);
            name=itemView.findViewById(R.id.item_name);
            description=itemView.findViewById(R.id.item_description_text);
            imageView=itemView.findViewById(R.id.item_image);
        }
    }
}
