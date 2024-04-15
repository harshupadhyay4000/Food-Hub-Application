package com.example.ui.Pages.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Orders;
import com.example.ui.R;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.View_upcoming_Holder>{
    Context context;
    LayoutInflater layoutInflater;
    List<Orders>orders;

    public UpcomingAdapter(Context context, List<Orders> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public View_upcoming_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.upcoming_orders,parent,false);
        return new View_upcoming_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_upcoming_Holder holder, int position) {
        Orders orders1=orders.get(position);

        holder.name.setText(orders1.getName());
        holder.imageView.setImageResource(orders1.getImageResource());
        holder.date.setText(orders1.getDate());
        holder.price.setText(orders1.getPrice());
        holder.item.setText(orders1.getItems());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class View_upcoming_Holder extends RecyclerView.ViewHolder {
        Button rate, reorders;
        ImageView imageView;
        TextView date, name, price, item;
        public View_upcoming_Holder(@NonNull View itemView) {
            super(itemView);

            rate=itemView.findViewById(R.id.rate_btn);
            reorders=itemView.findViewById(R.id.reorder_btn);
            imageView=itemView.findViewById(R.id.image_brand);
            name=itemView.findViewById(R.id.brand_name);
            date=itemView.findViewById(R.id.time_text);
            price=itemView.findViewById(R.id.price_text_upcoming);
            item=itemView.findViewById(R.id.items_text_adapter);
        }
    }
}
