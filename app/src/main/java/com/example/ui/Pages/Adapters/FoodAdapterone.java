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

import com.example.ui.Pages.Adapters.Lists.Food;
import com.example.ui.Pages.FastFoods_Activity;
import com.example.ui.R;

import java.util.List;

public class FoodAdapterone extends RecyclerView.Adapter<FoodAdapterone.View_one_Holder>{
    Context context;
    LayoutInflater layoutInflater;
    private List<Food> foods;

    public FoodAdapterone(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public View_one_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.food_layout,parent,false);
        return new View_one_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_one_Holder holder, int position) {
            Food food= foods.get(position);

            holder.foodname.setText(food.getName());
            holder.imageView.setImageResource(food.getImageResource());
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
        return foods.size();
    }

    public static class View_one_Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView foodname;
        public View_one_Holder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.cardview_food);
            imageView=itemView.findViewById(R.id.imageview_food);
            foodname=itemView.findViewById(R.id.foodname_text);

        }
    }
}
