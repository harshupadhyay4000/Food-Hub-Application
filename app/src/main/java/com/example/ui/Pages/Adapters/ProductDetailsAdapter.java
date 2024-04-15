package com.example.ui.Pages.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Details;
import com.example.ui.Pages.Cart.CartActivity;
import com.example.ui.R;

import java.util.List;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.View_details_Holder>{
    Context context;
    List<Details>details;
    LayoutInflater layoutInflater;

    public ProductDetailsAdapter(Context context, List<Details> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public View_details_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.details,parent,false);
        return new View_details_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_details_Holder holder, int position) {

        Details details1=details.get(position);

        holder.productname.setText(details1.getName());
        holder.outlets.setText(details1.getOutlets());
        holder.rating.setText(details1.getRatings());
        holder.price.setText(details1.getPrice());
        holder.description.setText(details1.getDescription());

        holder.circle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.circle1.setBackgroundResource(R.drawable.color_circle);
            }
        });
        holder.circle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.circle2.setBackgroundResource(R.drawable.color_circle);
            }
        });
        holder.circle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.circle3.setBackgroundResource(R.drawable.color_circle);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = holder.quantity.getText().toString();
                int currentValue = Integer.parseInt(currentText);
                currentValue++;
                holder.quantity.setText(String.valueOf(currentValue));

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentTextminus = holder.quantity.getText().toString();
                int currentValueminus = Integer.parseInt(currentTextminus);

                if (currentValueminus > 1) {
                    currentValueminus--;
                    holder.quantity.setText(String.valueOf(currentValueminus));
                }
                else {
                    holder.quantity.setText("0");
                }
            }
        });

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, CartActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class View_details_Holder extends RecyclerView.ViewHolder {
        ImageButton addtocart, plus, minus, circle1, circle2, circle3;
        TextView quantity, price, description, productname, rating, outlets;

        public View_details_Holder(@NonNull View itemView) {
            super(itemView);

            rating=itemView.findViewById(R.id.rating_text_description);
            outlets=itemView.findViewById(R.id.outlets_details);
            productname=itemView.findViewById(R.id.details_name);
            plus=itemView.findViewById(R.id.plus_btn_details);
            minus=itemView.findViewById(R.id.minus_btn_details);
            quantity=itemView.findViewById(R.id.quantity_text_details);
            price=itemView.findViewById(R.id.price_details);
            addtocart=itemView.findViewById(R.id.add_to_cart_btn);
            description=itemView.findViewById(R.id.item_description_details);
            circle1=itemView.findViewById(R.id.circle_button_details);
            circle2=itemView.findViewById(R.id.circle_buttontwo_details);
            circle3=itemView.findViewById(R.id.circle_buttonthree_details);
        }
    }
}
