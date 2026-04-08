package com.example.camera_gallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ImageDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textDetails;
    Button btnDelete;

    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = findViewById(R.id.imageView);
        textDetails = findViewById(R.id.textDetails);
        btnDelete = findViewById(R.id.btnDelete);

        image = getIntent().getIntExtra("image", 0);

        imageView.setImageResource(image);

        textDetails.setText("Sample Image\nPath: emulator\nSize: N/A\nDate: N/A");

        btnDelete.setOnClickListener(v -> {
            Toast.makeText(this, "Image Deleted (Demo)", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}