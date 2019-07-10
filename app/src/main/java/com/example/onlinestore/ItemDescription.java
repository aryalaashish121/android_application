package com.example.onlinestore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinestore.Interface.UsersApi;
import com.squareup.picasso.Picasso;

public class ItemDescription extends AppCompatActivity {

    TextView iPrice,iName,iDesc;
    ImageView iImage;
    Button placeorder;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        iImage = findViewById(R.id.itemImage);
        iPrice = findViewById(R.id.price);
        iName = findViewById(R.id.itemName);
        iDesc = findViewById(R.id.itemDescription);
        placeorder = findViewById(R.id.placeorder);

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context newcontext = view.getContext();
                Intent orders = new Intent(ItemDescription.this, Order.class);

                SharedPreferences preferences=getSharedPreferences("UserData",0);
                String userID = preferences.getString("uid",null);
                Bundle bundle = getIntent().getExtras();
                String productID = bundle.getString("itemID");
                String productName = bundle.getString("productName");
                String ProductPrice = bundle.getString("productPrice");

                orders.putExtra("userID",userID);
                orders.putExtra("productID",productID);
                orders.putExtra("productName",productName);
                orders.putExtra("productPrice",ProductPrice);
                orders.putExtra("productImage",bundle.getString("itemImageName"));
                orders.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                newcontext.startActivity(orders);
            }
        });


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
           iName.setText(bundle.getString("itemName"));
            iPrice.setText("RS "+(bundle.getString("itemPrice")));
            iDesc.setText(bundle.getString("itemDescription"));

            String itemImages = bundle.getString("itemImageName");
            Picasso.with(this).load(itemImages).into(iImage);

        }
    }
}
