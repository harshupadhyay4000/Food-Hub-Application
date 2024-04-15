package com.example.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ui.Pages.FoodItem_Fragment;
import com.example.ui.Pages.Resturant_Fragment;

public class ViewPagerFavAdpater extends FragmentStateAdapter {
    public ViewPagerFavAdpater(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FoodItem_Fragment();
            case 1:
                return new Resturant_Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
