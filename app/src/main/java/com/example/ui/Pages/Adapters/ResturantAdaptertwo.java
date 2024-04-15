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

import com.example.ui.Pages.Adapters.Lists.Resturant;
import com.example.ui.Pages.FastFoods_Activity;
import com.example.ui.R;

import java.util.List;

public class ResturantAdaptertwo extends RecyclerView.Adapter<ResturantAdaptertwo.View_two_Holder>{

    Context context;
    LayoutInflater layoutInflater;
    List<Resturant> resturants;

    public ResturantAdaptertwo(Context context, List<Resturant> resturants) {
        this.context = context;
        this.resturants = resturants;

    }

    @NonNull
    @Override
    public View_two_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.resturants,parent,false);
        return new View_two_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_two_Holder holder, int position) {

        Resturant resturant =resturants.get(position);

        holder.imageView.setImageResource(resturant.getImageResource());
        holder.restuantname.setText(resturant.getName());
        holder.outlets.setText(resturant.getOutlets());
        holder.ratings.setText(resturant.getRatings());

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
        return resturants.size();
    }

    public static class View_two_Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView restuantname,ratings,outlets;
        ImageView imageView;
        public View_two_Holder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview_restaurant);
            restuantname=itemView.findViewById(R.id.restaurant_name);
            ratings=itemView.findViewById(R.id.ratings_text);
            outlets=itemView.findViewById(R.id.outlets_text);
            imageView=itemView.findViewById(R.id.restaurant_image);
        }
    }
}
