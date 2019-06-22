package com.example.onlinestore.Controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinestore.ItemDescription;
import com.example.onlinestore.Model.ItemsDetail;
import com.example.onlinestore.R;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<ItemsDetail> itemsDetailList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    public ItemAdapter(List<ItemsDetail> itemsDetailList, Context context) {
        this.itemsDetailList = itemsDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemsample,viewGroup,false);
        return new ItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolader, int i) {

        final ItemsDetail itemsDetail =itemsDetailList.get(i);
        itemViewHolader.itemName.setText(itemsDetail.getItemName());
        itemViewHolader.itemPrice.setText("RS: "+itemsDetail.getItemPrice());

        StrictMode();
        String path = BASE_URL+"images/"+itemsDetail.getItemImage();
        try {
            URL url = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            itemViewHolader.itemImage.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        itemViewHolader.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String destination_path = BASE_URL+"images/"+itemsDetail.getItemImage();
                Intent itemDetails = new Intent(context, ItemDescription.class);
                itemDetails.putExtra("itemName",itemsDetail.getItemName());
                itemDetails.putExtra("itemPrice",itemsDetail.getItemPrice());
                itemDetails.putExtra("itemImageName",destination_path);
                itemDetails.putExtra("itemDescription",itemsDetail.getItemDescription());
                itemDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("image", "onClick: "+itemsDetail.getItemImage());

                context.startActivity(itemDetails);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsDetailList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView itemName,itemPrice;
    public ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.productName);
            itemPrice= itemView.findViewById(R.id.productPrie);
            itemImage = itemView.findViewById(R.id.productImage);
        }
    }
    private void StrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
