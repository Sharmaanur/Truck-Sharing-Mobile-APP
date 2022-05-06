package com.suman.trucksharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.suman.trucksharing.DB.Data;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView sender, pickuptime, receiver, dropofftime, weight, type, width, height, length, qty;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        img = findViewById(R.id.imageView);
        sender = findViewById(R.id.txt_sender);
        pickuptime = findViewById(R.id.txt_pickup_time);
        receiver = findViewById(R.id.txt_receiver);
        dropofftime = findViewById(R.id.txt_drop_off_time);
        weight = findViewById(R.id.txt_weight);
        type = findViewById(R.id.txt_type);
        width = findViewById(R.id.txt_width);
        height = findViewById(R.id.txt_height);
        length = findViewById(R.id.txt_length);
        qty = findViewById(R.id.txt_quantity);
        byte[] decodedString = Base64.decode(Data.image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        img.setImageBitmap(decodedByte);
        Intent intent = getIntent();
        try {
            sender.setText("From "+intent.getStringExtra("name"));
            pickuptime.setText(intent.getStringExtra("time"));
            receiver.setText("To Me");
            //dropofftime.setText(intent.getStringExtra(""));
            weight.setText("Weight\n"+intent.getStringExtra("weight"));
            type.setText("Goods Type\n"+intent.getStringExtra("type"));
            width.setText("Width\n"+intent.getStringExtra("width"));
            height.setText("Height\n"+intent.getStringExtra("height"));
            length.setText("Length\n"+intent.getStringExtra("length"));
            qty.setText("Quantity\n1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}