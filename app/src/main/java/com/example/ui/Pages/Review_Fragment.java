package com.example.ui.Pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui.Pages.Adapters.Lists.Reviewlist;
import com.example.ui.Pages.Adapters.ReviewAdapter;
import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;


public class Review_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review_, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recycle_review_bar);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Reviewlist>reviewlists=new ArrayList<>();
        reviewlists.add(new Reviewlist("Alyce Lambo",R.drawable.review_two_img,"Really convenient and the points system helps benefit loyalty. Some mild glitches here and there, but nothing too egregious. Obviously need to roll out to more remote.","25/06/2020"));
        reviewlists.add(new Reviewlist("Gonela Solom",R.drawable.icon_for_review,"Been a life saver for keeping our sanity during the pandemic, although they could improve some of their ui and how they handle specials as it often is unclear how to use them or everything is sold out so fast it feels a bit bait and switch. Still I'd be stir crazy and losing track of days without so...","22/06/2020"));
        reviewlists.add(new Reviewlist("Brian C",R.drawable.review_three_img,"Got an intro offer of 50% off first order that did not work..... I have scaled the app to find a contact us button but only a spend with us button available.","21/06/2020"));
        reviewlists.add(new Reviewlist("Helsmar E",R.drawable.review_four_img,"\nAmet minim mollit non deserunt ullamco est sit aliqua dolor do amet Sint. Velit officia consequat duis. Amet minim mollit non eserunt ullamco est sit.","20/06/2020"));
        ReviewAdapter reviewAdapter=new ReviewAdapter(getContext(),reviewlists);
        recyclerView.setAdapter(reviewAdapter);
        return view;

    }
}