package com.will.android.faurecia;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Form form;
    private PpmFragment ppmFragment;
    private DleFragment dleFragment;


    public Form getForm() {
        return form;
    }

    public PpmFragment getPpmFragment() {
        return ppmFragment;
    }

    public DleFragment getDleFragment() {
        return dleFragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        form = new Form();
        ppmFragment = new PpmFragment(this);
        dleFragment = new DleFragment(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ppmFragment)
                .replace(R.id.fragment_container_2, dleFragment)
                .hide(dleFragment)
                .commit();
    }



    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    if (item.getItemId() == R.id.nav_PPM) {
                        ft.hide(dleFragment)
                                .show(ppmFragment)
                                .commit();

                    } else if (item.getItemId() == R.id.nav_DLE) {
                        ft.hide(ppmFragment)
                                .show(dleFragment)
                                .commit();

                    }
                    return true;
                }
            };
}

