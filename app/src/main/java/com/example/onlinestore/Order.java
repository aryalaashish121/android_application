package com.example.onlinestore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Order extends AppCompatActivity {

    ImageView itemImage;
    EditText itemQuantity;
    TextView itemPrice,itemName,itemTotal;
    Button placeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        itemImage = findViewById(R.id.itemImage);
        itemName= findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        itemQuantity = findViewById(R.id.orderQuantity);
        itemTotal = findViewById(R.id.totalPrice);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            itemName.setText(bundle.getString("productName"));
            itemPrice.setText("RS "+(bundle.getString("productPrice")));

            String itemImages = bundle.getString("productImage");
            Picasso.with(this).load(itemImages).into(itemImage);

        }

    }
}
