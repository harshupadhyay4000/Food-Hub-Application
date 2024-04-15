package com.example.ui.Pages;

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
import com.example.ui.ViewPagerFavAdpater;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class Favourites_Fragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourites_, container, false);
        viewPager = view.findViewById(R.id.viewPagerFav);
        tabLayout = view.findViewById(R.id.tabLayoutFav);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Create an instance of your ViewPager adapter
        ViewPagerFavAdpater adapter = new ViewPagerFavAdpater(requireActivity());
        // Set the adapter to the ViewPager
        viewPager.setAdapter(adapter);

        // Link the TabLayout with the ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Food Items");
                            break;
                        case 1:
                            tab.setText("Restaurants");
                            break;
                    }
                }
        ).attach();
    }
}