package com.example.ui.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.Pages.Adapters.FastFoodAdapter;
import com.example.ui.Pages.Adapters.Lists.Pizza;
import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;

public class FastFoods_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FastFoodAdapter fastFoodAdapter;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fast_foods);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView=findViewById(R.id.reyclerview_fastfood);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        back=findViewById(R.id.back_btn_fastfood);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FastFoods_Activity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        List<Pizza> pizzas=new ArrayList<>();

        pizzas.add(new Pizza("Jalapeno Hawaiian",R.drawable.pizza_one_img,"4.5","(27+)","₹350","Jalapeno, Cheese and Pineapple"));
        pizzas.add(new Pizza("Red Paprika",R.drawable.pizza_two_img,"4.7","(23+)","₹470","Paprica, Cheese and Tomatoes"));
        pizzas.add(new Pizza("Italian Hawaiian",R.drawable.pizza_one_img,"4.3","(20+)","₹350","Jalapeno and Cheese"));
        pizzas.add(new Pizza("Mexican Pizza",R.drawable.pizza_two_img,"4.2","(19+)","₹350","Pickle, Cheese and Tomatoes"));
        fastFoodAdapter=new FastFoodAdapter(this,pizzas);
        recyclerView.setAdapter(fastFoodAdapter);
    }
}