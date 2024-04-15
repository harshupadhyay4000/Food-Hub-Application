package com.example.ui.Pages;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.Login.Login;
import com.example.ui.Pages.Adapters.FoodAdapterone;
import com.example.ui.Pages.Adapters.Lists.Food;
import com.example.ui.Pages.Adapters.Lists.Item;
import com.example.ui.Pages.Adapters.Lists.Resturant;
import com.example.ui.Pages.Adapters.PopularitemAdapter;
import com.example.ui.Pages.Adapters.ResturantAdaptertwo;
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

        ImageButton profileicon = view.findViewById(R.id.home_frag_usericon);
        profileicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        NavigationView navigationView = view.findViewById(R.id.sideNavigation);
        // Get the header view
        View headerView = navigationView.getHeaderView(0); // 0 index assuming there's only one header
        // Find the user icon view within the header layout
        ImageButton userIcon = headerView.findViewById(R.id.user_img_sidebar);
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
            currentUser.delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign out the user
                            mAuth.signOut();
                            // Redirect or show login screen
                            Intent intent = new Intent(getActivity(), Login.class);
                            intent.putExtra("force_login", true); // Indicate that login should be forced
                            startActivity(intent);
                            getActivity().finish(); // Close the current activity
                        } else {
                            // Deleting user failed
                            Log.w(TAG, "deleteUser:failure", task.getException());
                            // Handle failure
                        }
                    });
        } else {
            // No user signed in, redirect to login screen
            Intent intent = new Intent(getActivity(), Login.class);
            intent.putExtra("force_login", true); // Indicate that login should be forced
            startActivity(intent);
            getActivity().finish(); // Close the current activity
        }
    }
}