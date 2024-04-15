package com.example.ui.Pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui.Pages.Adapters.Lists.Orders;
import com.example.ui.Pages.Adapters.UpcomingAdapter;
import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;


public class Upcoming_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_upcoming_,container,false);

        RecyclerView recyclerView=view.findViewById(R.id.recycle_upcoming_new);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        List<Orders>orders=new ArrayList<>();
        orders.add(new Orders("Jimmy John's",R.drawable.jimmy,"20 Jun, 10:30","3 Items","$17.10"));
        orders.add(new Orders("Subway",R.drawable.subway_icon,"19 Jun, 11:50","2 Items","$20.50"));
        UpcomingAdapter upcomingAdapter=new UpcomingAdapter(getContext(),orders);
        recyclerView.setAdapter(upcomingAdapter);

        return view;


    }
}