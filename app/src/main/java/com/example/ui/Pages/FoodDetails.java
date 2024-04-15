package com.example.ui.Pages;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.Lists.Details;
import com.example.ui.Pages.Adapters.ProductDetailsAdapter;
import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;

public class FoodDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ProductDetailsAdapter productDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.recycle_food_details);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Details>details=new ArrayList<>();

        details.add(new Details("Ground Tacos","4.5","(30+)","₹350","Brown Bread Better with extra cheese. Used 85% Veggies. Garlic - use fresh chopped. Spices - chili powder, cumin, onion powder."));
        productDetailsAdapter=new ProductDetailsAdapter(this,details);
        recyclerView.setAdapter(productDetailsAdapter);


    }
}