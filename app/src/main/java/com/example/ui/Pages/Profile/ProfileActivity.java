package com.example.ui.Pages.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.ui.Pages.Adapters.VariableBag;
import com.example.ui.Pages.HomeActivity;
import com.example.ui.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    ImageButton Back;
    ImageView profileImageView;
    TextInputEditText name, email;
    TextView nameee;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Views
        profileImageView = findViewById(R.id.profile_image_prof);
        Back=findViewById(R.id.back_btn_profile);
        name=findViewById(R.id.full_name_profile_edit);
        email=findViewById(R.id.email_profile_edit);

        name.setText(VariableBag.namee);
        email.setText(VariableBag.emaill);
        // Set OnClickListener for Back button

        nameee=findViewById(R.id.name_text_profile_name);
        nameee.setText(VariableBag.namee);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

      String  profileimagenew = VariableBag.path;
        Glide.with(this)
                .load(profileimagenew) // Pass the image path
                .placeholder(R.drawable.square_img) // Placeholder image while loading
                .error(R.drawable.square_img) // Image to display if loading fails
                .into(profileImageView);
    }

    // Method to load profile image from Firebase Storage
    private void loadProfileImage() {
        // Get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Get reference to the profile image URL in Firebase Realtime Database
            DatabaseReference profileImageRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("profileImageUrl");
            profileImageRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String profileImageUrl = dataSnapshot.getValue(String.class);
                        // Load profile image into ImageView using Glide
                        Glide.with(ProfileActivity.this)
                                .load(profileImageUrl)
                                .placeholder(R.drawable.square_img) // Placeholder image while loading
                                .error(R.drawable.square_img) // Image to display in case of error
                                .into(profileImageView);
                    } else {
                        // Handle case where profile image URL doesn't exist
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }
    }
}