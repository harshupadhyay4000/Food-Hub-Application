package com.example.ui.Pages;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.ui.Login.Login;
import com.example.ui.Pages.Adapters.FoodAdapterone;
import com.example.ui.Pages.Adapters.Lists.Food;
import com.example.ui.Pages.Adapters.Lists.Item;
import com.example.ui.Pages.Adapters.Lists.Resturant;
import com.example.ui.Pages.Adapters.PopularitemAdapter;
import com.example.ui.Pages.Adapters.ResturantAdaptertwo;
import com.example.ui.Pages.Adapters.VariableBag;
import com.example.ui.Pages.Profile.ProfileActivity;
import com.example.ui.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class HomePage_Fragment extends Fragment {

    ImageView Logimageview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page_, container, false);


        DrawerLayout drawerLayout = view.findViewById(R.id.drawerLayout);
        ImageButton hamburgericon = view.findViewById(R.id.hamburgericon_home);

        hamburgericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        ImageView profileicon = view.findViewById(R.id.home_frag_usericon);
        String profileImagePath = VariableBag.path;
        Glide.with(this)
                .load(profileImagePath) // Pass the image path
                .placeholder(R.drawable.square_img) // Placeholder image while loading
                .error(R.drawable.square_img)// Image to display if loading fails
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(profileicon);

        profileicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        NavigationView navigationView = view.findViewById(R.id.sideNavigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_bar_new) {

                    startActivity(new Intent(getActivity(), ProfileActivity.class));
                } else if (id == R.id.navigation_profile_new) {

                    startActivity(new Intent(getActivity(), ProfileActivity.class));
                } else if (id == R.id.navigation_delivery_new) {

                    startActivity(new Intent(getActivity(), FastFoods_Activity.class));
                } else if (id == R.id.navigation_wallet_new) {

                    startActivity(new Intent(getActivity(), FastFoods_Activity.class));
                } else if (id == R.id.navigation_contact_new) {

                    startActivity(new Intent(getActivity(), FastFoods_Activity.class));
                } else if (id == R.id.navigation_settings_new) {

                    startActivity(new Intent(getActivity(), FastFoods_Activity.class));
                } else if (id == R.id.navigation_help_new) {

                    startActivity(new Intent(getActivity(), FastFoods_Activity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        // Get the header view
        View headerView = navigationView.getHeaderView(0); // 0 index assuming there's only one header
        // Find the user icon view within the header layout
        ImageButton userIcon = headerView.findViewById(R.id.user_img_sidebar);
        TextView nameing = headerView.findViewById(R.id.name_text_sidebar);
        TextView emailiD = headerView.findViewById(R.id.email_header_view);

        nameing.setText(VariableBag.namee);
        emailiD.setText(VariableBag.emaill);

        String profileImage = VariableBag.path;
        Glide.with(this)
                .load(profileImage) // Pass the image path
                .placeholder(R.drawable.square_img) // Placeholder image while loading
                .error(R.drawable.square_img)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))// Image to display if loading fails
                .into(userIcon);

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);


            }
        });


        RecyclerView recyclerViewone = view.findViewById(R.id.recycle_food_home);
        RecyclerView recyclerViewtwo = view.findViewById(R.id.home_recycleRestaurants);
        RecyclerView recyclerViewthree = view.findViewById(R.id.home_recyclePopular);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewone.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewtwo.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewthree.setLayoutManager(layoutManager2);


        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Burger", R.drawable.burger_icon));
        foods.add(new Food("Donut", R.drawable.donut_icon));
        foods.add(new Food("Pizza", R.drawable.pizza_icon));
        foods.add(new Food("Mexican", R.drawable.mexican_icon));
        foods.add(new Food("Asian", R.drawable.asian_icon));
        FoodAdapterone foodAdapterone = new FoodAdapterone(getContext(), foods);
        recyclerViewone.setAdapter(foodAdapterone);


        List<Resturant> resturants = new ArrayList<>();
        resturants.add(new Resturant("McDonald", R.drawable.macd, "4.5", "(25+)"));
        resturants.add(new Resturant("Starbucks", R.drawable.starbucks, "4.7", "(28+)"));
        ResturantAdaptertwo resturantAdaptertwo = new ResturantAdaptertwo(getContext(), resturants);
        recyclerViewtwo.setAdapter(resturantAdaptertwo);


        List<Item> items = new ArrayList<>();
        items.add(new Item("Noodles", R.drawable.noodles, "4.3", "(30+)", "₹250", "Speciality"));
        items.add(new Item("Pizza", R.drawable.pizza, "4.5", "(40+)", "₹450", "Pan Pizza"));
        items.add(new Item("Pasta", R.drawable.noodles, "4.3", "(30+)", "₹250", "Speciality"));
        items.add(new Item("Pizza", R.drawable.pizza, "4.5", "(40+)", "₹450", "Pan Pizza"));

        PopularitemAdapter popularitemAdapter = new PopularitemAdapter(getContext(), items);
        recyclerViewthree.setAdapter(popularitemAdapter);

        Logimageview = view.findViewById(R.id.logout_frag);
        Logimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is signed in with Facebook
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedInWithFacebook = accessToken != null && !accessToken.isExpired();

                if (isLoggedInWithFacebook) {
                    // If logged in with Facebook, log out from Facebook first
                    LoginManager.getInstance().logOut();
                }

                // Perform Firebase logout and revoke access
                logoutUser();
            }
        });


        return view;

    }

    private void logoutUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Sign out the user
            mAuth.signOut();
            // Redirect or show login screen
            Intent intent = new Intent(getActivity(), Login.class);
            intent.putExtra("force_login", true); // Indicate that login should be forced
            startActivity(intent);
            getActivity().finish(); // Close the current activity
        } else {
            // No user signed in, redirect to login screen
            Intent intent = new Intent(getActivity(), Login.class);
            intent.putExtra("force_login", true); // Indicate that login should be forced
            startActivity(intent);
            getActivity().finish(); // Close the current activity
        }
    }

}