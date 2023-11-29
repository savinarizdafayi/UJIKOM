package com.example.ujikom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.databinding.ActivityHomeScreenBinding;
import com.example.ujikom.pegawai.EditData;

public class HomeScreen extends AppCompatActivity {
    private ActivityHomeScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = new Intent(HomeScreen.this, MainActivity.class);

        binding.button4.setOnClickListener(v -> {
            startActivity(intent);
        });


    }
}