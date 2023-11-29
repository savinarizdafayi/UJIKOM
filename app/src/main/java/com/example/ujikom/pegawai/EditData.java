package com.example.ujikom.pegawai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.databinding.ActivityEditDataBinding;


public class EditData extends AppCompatActivity {
    private ActivityEditDataBinding binding;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        String nama = bun.getString("Nama");
        String nip = bun.getString("NIP");
        String date = bun.getString("Date");
        String gender = bun.getString("Gender");
        String pob = bun.getString("ttl");

        byte[] byteArray = getIntent().getByteArrayExtra("foto");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        binding.imageView2.setImageBitmap(bmp);

        binding.inputnama.setText(nama);
        binding.inputnip.setText(nip);
        binding.inputdate.setText(date);
        binding.inputgender.setText(gender);
        binding.alamatt.setText(pob);

        EditText edNama = binding.inputnama;
        EditText edNIP = binding.inputnip;
        EditText edDate = binding.inputdate;
        EditText edGender = binding.inputgender;
        EditText Place = binding.alamatt;

        ImageButton editImage = binding.imageButton;
        editImage.setOnClickListener(view -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE);
        });



        binding.switch1.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                edNama.setEnabled(true);
                edNIP.setEnabled(true);
                edDate.setEnabled(true);
                edGender.setEnabled(true);
                Place.setEnabled(true);
            } else {
                edNama.setEnabled(false);
                edNIP.setEnabled(false);
                edDate.setEnabled(false);
                edGender.setEnabled(false);
                Place.setEnabled(false);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                assert data != null;
                binding.imageView2.setImageURI(data.getData());
            }
        }
    }
}