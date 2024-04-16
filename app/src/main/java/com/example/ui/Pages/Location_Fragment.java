package com.example.ui.Pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.ui.R;


public class Location_Fragment extends Fragment {

    private Spinner spinnerStateLocation;
    private Spinner spinnerCityLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_, container, false);

        spinnerStateLocation = view.findViewById(R.id.spinner_state_location);
        spinnerCityLocation = view.findViewById(R.id.spinner_city_location);

        String[] statesOfIndia = getResources().getStringArray(R.array.states_of_india);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, statesOfIndia);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerStateLocation.setAdapter(adapter);

        ImageButton backButton = view.findViewById(R.id.back_btn_location);

        // Set onClick listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomePageFragment (index 0 in ViewPager)
                ((ViewPager) requireActivity().findViewById(R.id.viewpager_home)).setCurrentItem(0);
            }
        });
        spinnerStateLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedState = (String) parent.getItemAtPosition(position);

                loadCitiesForState(selectedState);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void loadCitiesForState(String state) {

        String[] cities = getCitiesForState(state);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCityLocation.setAdapter(cityAdapter);
    }

    // This method should return an array of cities for the given state
    private String[] getCitiesForState(String state) {
        // Dummy implementation, replace with your logic
        if (state.equals("Gujarat")) {
            return getResources().getStringArray(R.array.cities_state1);
        } else if (state.equals("Maharashtra")) {
            return getResources().getStringArray(R.array.cities_state2);
        } else {
            // Return an empty array or handle invalid state
            return new String[]{};
        }
    }
}