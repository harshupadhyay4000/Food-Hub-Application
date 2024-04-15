package com.example.ui.Pages;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui.R;
import com.example.ui.ViewPagerBagAdapter;
import com.example.ui.ViewPagerFavAdpater;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class Bag_Fragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bag_, container, false);

        viewPager = view.findViewById(R.id.viewPagerbag);
        tabLayout = view.findViewById(R.id.tabLayoutbag);


        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPagerBagAdapter adapter1=new ViewPagerBagAdapter(requireActivity());
        // Set the adapter to the ViewPager
        viewPager.setAdapter(adapter1);

        // Link the TabLayout with the ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Upcoming");
                            break;
                        case 1:
                            tab.setText("History");
                            break;
                    }
                }
        ).attach();
    }
}