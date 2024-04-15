package com.example.ui.Pages;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.ui.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewPager viewPager = findViewById(R.id.viewpager_home);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomePage_Fragment());
        adapter.addFragment(new Location_Fragment());
        adapter.addFragment(new Bag_Fragment());
        adapter.addFragment(new Favourites_Fragment());
        adapter.addFragment(new Review_Fragment());
        viewPager.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_bar) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.navigation_location) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.navigation_bag) {
                viewPager.setCurrentItem(2);
                return true;
            } else if (itemId == R.id.navigation_like) {
                viewPager.setCurrentItem(3);
                return true;
            } else if (itemId == R.id.navigation_notifications) {
                viewPager.setCurrentItem(4);
                return true;
            }
            return false;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_bar);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_location);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_bag);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_like);
                        break;
                    case 4:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}