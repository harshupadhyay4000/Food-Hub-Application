package com.example.ui.Pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui.Pages.Adapters.FoodItemAdapter;
import com.example.ui.Pages.Adapters.Lists.Fooditems;
import com.example.ui.Pages.Adapters.Lists.Pizza;
import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;


public class FoodItem_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_food_item_, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recycle_food_item);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Fooditems>fooditems=new ArrayList<>();
        fooditems.add(new Fooditems("Jalapeno Hawaiian",R.drawable.pizza_one_img,"4.5","(27+)","₹350","Jalapeno, Cheese and Pineapple"));
        fooditems.add(new Fooditems("Red Paprika",R.drawable.pizza_two_img,"4.7","(23+)","₹470","Paprica, Cheese and Tomatoes"));
        fooditems.add(new Fooditems("Italian Hawaiian",R.drawable.pizza_one_img,"4.3","(20+)","₹350","Jalapeno and Cheese"));
        fooditems.add(new Fooditems("Mexican Pizza",R.drawable.pizza_two_img,"4.2","(19+)","₹350","Pickle, Cheese and Tomatoes"));
        FoodItemAdapter foodItemAdapter=new FoodItemAdapter(getContext(),fooditems);
        recyclerView.setAdapter(foodItemAdapter);


        return view;
    }
}