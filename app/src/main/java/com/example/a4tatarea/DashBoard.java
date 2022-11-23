package com.example.a4tatarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a4tatarea.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        mAuth = FirebaseAuth.getInstance();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.promocion:
                    replaceFragment(new PromFragment());
                    break;
                case R.id.buscar:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.perfil:
                    replaceFragment(new PerfilFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    public void Salir(View view) {
        mAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
