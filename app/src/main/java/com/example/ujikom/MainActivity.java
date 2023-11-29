package com.example.ujikom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.databinding.ActivityMainBinding;
import com.example.ujikom.pegawai.EditData;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    ImageView image;
    private TextView selectedDateTV;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Intent
        binding.button2.setOnClickListener(view -> {

            binding.imageView.buildDrawingCache();
            Bitmap foto = binding.imageView.getDrawingCache();
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            foto.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] byteArray = bStream.toByteArray();
            RadioButton male = binding.radioButton;
            RadioButton female = binding.radioButton2;
            RadioGroup radioGroup = binding.radio;
            // get selected radio button from radioGroup
            int selectedId = radioGroup.getCheckedRadioButtonId();

            //RadioButton radioButton = (RadioButton) findViewById(selectedId);

            String passname = String.valueOf(binding.editTextTextPersonName2.getText());
            String passnip = String.valueOf(binding.editTextTextPersonName3.getText());
            String date = String.valueOf(binding.dateview.getText());
            String alamat = String.valueOf(binding.pob.getText());

            //Intent
            Intent passData = new Intent(MainActivity.this, EditData.class);
            Bundle bun = new Bundle();

            // find the radiobutton by returned id
            if (selectedId == male.getId()) {
                bun.putString("Gender", "Male");
                Log.d("radio", "male");
            } else if (selectedId == female.getId()) {
                bun.putString("Gender", "Female");
                Log.d("radio", "Female");
            }

            //put bundle to send data
            bun.putString("Nama", passname);
            bun.putString("NIP", passnip);
            bun.putString("Date", date);
            bun.putString("ttl", alamat);
            passData.putExtras(bun);
            startActivity(passData.putExtra("foto", byteArray));
        });

        Button pickDateBtn = binding.button;
        selectedDateTV = binding.dateview;

        //Date
        pickDateBtn.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view1, year1, month1, day1) -> selectedDateTV.setText(day1 + "-" + (month1 + 1) + "-" + year1),
                    year, month, day);
            datePickerDialog.show();
        });

        //Image
        image = binding.imageView;
        Button getimage = binding.button3;
        getimage.setOnClickListener(view -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE);
        });

        //RadioButton
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                assert data != null;
                image.setImageURI(data.getData());
            }
        }
    }
}