package com.example.ui.Pages.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Pizza;
import com.example.ui.Pages.FoodDetails;
import com.example.ui.R;

import java.util.List;

public class FastFoodAdapter extends RecyclerView.Adapter<FastFoodAdapter.View_food_Holder>{
    Context context;
    List<Pizza>pizzas;
    LayoutInflater layoutInflater;

    public FastFoodAdapter(Context context, List<Pizza> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    @NonNull
    @Override
    public View_food_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.pizza_layout,parent,false);
        return new View_food_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_food_Holder holder, int position) {
        Pizza pizza= pizzas.get(position);

        holder.imageView.setImageResource(pizza.getImageResource());
        holder.productname.setText(pizza.getName());
        holder.outlets.setText(pizza.getOutlets());
        holder.rating.setText(pizza.getRatings());
        holder.price.setText(pizza.getPrice());
        holder.description.setText(pizza.getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FoodDetails.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class View_food_Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView productname,price,rating,outlets,description;
        ImageView imageView;

        public View_food_Holder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.maincard_fastfood);
            productname=itemView.findViewById(R.id.product_text_fastfood);
            price=itemView.findViewById(R.id.product_price_fastfood);
            rating=itemView.findViewById(R.id.ratingtext_fastfood);
            outlets=itemView.findViewById(R.id.outlets_fastfood);
            description=itemView.findViewById(R.id.product_description_fastfood);
            imageView=itemView.findViewById(R.id.product_image_fastfood);
        }
    }
}
