package com.will.android.faurecia;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static Form mForm = new Form();
    private final PpmFragment mPpmFragment = new PpmFragment();
    private final DleFragment mDleFragment = new DleFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_2, mDleFragment)
                .replace(R.id.fragment_container, mPpmFragment)
                .hide(mDleFragment)
                .show(mPpmFragment)
                .commit();

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in,
                            android.R.animator.fade_out);

                    if (item.getItemId() == R.id.nav_PPM) {
                        ft.hide(mDleFragment)
                                .show(mPpmFragment)
                                .commit();

                    } else if (item.getItemId() == R.id.nav_DLE) {
                        ft.hide(mPpmFragment)
                                .show(mDleFragment)
                                .commit();
                    }
                    return true;
                }
            };
}

