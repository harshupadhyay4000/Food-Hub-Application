package com.example.ui.Pages.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Fooditems;
import com.example.ui.R;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.View_new_Holder>{

    Context context;
    List<Fooditems>fooditems;
    LayoutInflater layoutInflater;

    public FoodItemAdapter(Context context, List<Fooditems> fooditems) {
        this.context = context;
        this.fooditems = fooditems;
    }

    @NonNull
    @Override
    public View_new_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.food_items,parent,false);
        return new View_new_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_new_Holder holder, int position) {
        Fooditems fooditems1=fooditems.get(position);

        holder.imageView.setImageResource(fooditems1.getImageResource());
        holder.productname.setText(fooditems1.getName());
        holder.outlets.setText(fooditems1.getOutlets());
        holder.rating.setText(fooditems1.getRatings());
        holder.price.setText(fooditems1.getPrice());
        holder.description.setText(fooditems1.getDescription());


    }

    @Override
    public int getItemCount() {
        return fooditems.size();
    }

    public static class View_new_Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView productname,price,rating,outlets,description;
        ImageView imageView;
        public View_new_Holder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.maincard_fooditems);
            productname=itemView.findViewById(R.id.product_text_fooditems);
            price=itemView.findViewById(R.id.product_price_fooditems);
            rating=itemView.findViewById(R.id.ratingtext_fooditems);
            outlets=itemView.findViewById(R.id.outlets_fooditems);
            description=itemView.findViewById(R.id.product_description_fooditems);
            imageView=itemView.findViewById(R.id.product_image_fooditems);

        }
    }
}
