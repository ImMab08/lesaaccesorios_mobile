package com.example.lesamobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.example.almenumobile.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private String token;
    FragmentContainerView fcv_activityHome;
    BottomNavigationView bnv_homeapp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        token = getIntent().getStringExtra("Token");
        Log.i("HomeActivity", "Token recibido: " + token);
        fcv_activityHome = findViewById(R.id.fcv_activityHome);
        bnv_homeapp = findViewById(R.id.bnv_homeapp);

        replaceFragment(new HomeFragment(), token);

        bnv_homeapp.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.mnit_inicio) {
                    replaceFragment(new HomeFragment(), token);
                } else if (item.getItemId() == R.id.mnit_menu) {
                    replaceFragment(new MenuFragment(), token);
                    Log.i("Menu Fragment", "Token Enviado: " + token);
                } else if (item.getItemId() == R.id.mnit_configuracion) {
                    replaceFragment(new SettingsFragment(), token);
                }

                return true;

            }
        });
    }

    // Podemos crear un metodo si repeticos mucho una parte del codigo:
    public void replaceFragment(Fragment fragment, String token) {
        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
        Bundle objInfo = new Bundle();
        objInfo.putString("Token", token);
        Log.i("Token enviado del home", "token: " + token);
        fragment.setArguments(objInfo);
        tran.replace(R.id.fcv_activityHome, fragment);
        tran.commit();
    }
}