package com.example.ui.Pages.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.View_cart_Holder>{
    Context context;
    List<Products>products;
    LayoutInflater layoutInflater;

    public CartAdapter(Context context, List<Products> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View_cart_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cart,parent,false);
        return new View_cart_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_cart_Holder holder, int position) {
        Products products1=products.get(position);

        holder.name.setText(products1.getName());
        holder.imageView.setImageResource(products1.getImageResource());
        holder.description.setText(products1.getDescription());
        holder.price.setText(products1.getPrice());
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

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class View_cart_Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton plus, minus;
        TextView name, quantity, price, description;
        public View_cart_Holder(@NonNull View itemView) {
            super(itemView);

            plus=itemView.findViewById(R.id.plus_btn_cart);
            minus=itemView.findViewById(R.id.minus_btn_cart);
            quantity=itemView.findViewById(R.id.quantity_text_cart);
            name=itemView.findViewById(R.id.product_name_cart);
            description=itemView.findViewById(R.id.description_cart);
            price=itemView.findViewById(R.id.price_cart_adapter);
            imageView=itemView.findViewById(R.id.cart_img);
        }
    }
}
