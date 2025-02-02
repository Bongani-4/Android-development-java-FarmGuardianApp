package com.example.farmguardian.views;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.example.farmguardian.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.search.SearchBar;


public class animalCaretaker extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {


    Fragment fragment = null;
    TextView noInternetTextView,heading;
    ImageView backHome;
    SearchBar  searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aniamal_caretaker);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(this);


        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.DarkGreen));

        noInternetTextView = findViewById(R.id.noInternet);
         backHome = findViewById(R.id.Animalcaretakerbackhome);
        heading = findViewById(R.id.heading);
        searchBar= findViewById(R.id.searchView);

        openAnimalCaretakerFragment();



        // Check internet connection
        if(isNetworkAvailable(this))
        {
          noInternetTextView.setVisibility(View.VISIBLE);
          return;

        }
        else {
            noInternetTextView.setVisibility(View.GONE);
        }



        backHome.setOnClickListener(v -> startActivity(new Intent(animalCaretaker.this,HomeActivity.class)));


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_layout, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_HireAnimalCareteker) {

            if(isNetworkAvailable((animalCaretaker.this))){
                noInternetTextView.setVisibility(View.VISIBLE);

            }
            else {
            noInternetTextView.setVisibility(View.GONE);
            }

            openAnimalCaretakerFragment();
            return true;

        }
        if(item.getItemId() == R.id.menu_becomeAnimalCaretaker)
        {
            BecomeCaretakerFragment();
            return true;



        }else if (item.getItemId() == R.id.menu_RequestDetails) {
            RequestDetailsFragment();

            return true;


        }
        return false;
    }

    public void loadingBAR()
    {

        ProgressBar loadingProgress = findViewById(R.id.Barprogress);
        loadingProgress.setIndeterminate(true);
        // Set the color of the ProgressBar to green
        loadingProgress.getIndeterminateDrawable().setColorFilter(
                ContextCompat.getColor(this, R.color.DarkGreen), android.graphics.PorterDuff.Mode.SRC_IN
        );


        loadingProgress.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            // Hide the loading ProgressBar after a delay
            loadingProgress.setVisibility(View.GONE);
        }, 3500);

    }





    private void openAnimalCaretakerFragment() {
        // Show loading progress bar
        loadingBAR();
        heading.setVisibility(View.VISIBLE);


        fragment = new hirefrgament();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();


        // Fragment transaction is complete, hide the loading progress bar
        getSupportFragmentManager().addOnBackStackChangedListener(this::hideLoadingBAR);
    }

    private void BecomeCaretakerFragment() {
        // Show loading progress bar and hiding unnecessary elements
        loadingBAR();

        heading.setVisibility(View.GONE);
        searchBar.setVisibility(View.GONE);
        fragment =  new NewAnimalcaretakerFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

        // Fragment transaction is complete, hide the loading progress bar
        getSupportFragmentManager().addOnBackStackChangedListener(this::hideLoadingBAR);
    }
    private void RequestDetailsFragment() {

        // Show loading progress bar

        loadingBAR();
        heading.setVisibility(View.GONE);
        searchBar.setVisibility(View.GONE);
        fragment =  new RequestDetailsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        // Fragment transaction is complete, hide the loading progress bar
        getSupportFragmentManager().addOnBackStackChangedListener(this::hideLoadingBAR);
    }



    private void hideLoadingBAR() {
        ProgressBar loadingProgress = findViewById(R.id.Barprogress);
        loadingProgress.setVisibility(View.GONE);
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        if (network == null) {
            return true;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
        if (networkCapabilities == null) {
            return true;
        }
        return !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }





}
