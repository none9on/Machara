package com.example.machara;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.machara.model.Order;

import org.w3c.dom.Text;

public class itemPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_page);

        ConstraintLayout itemBg = findViewById(R.id.itemPageBg);
        ImageView itemImage = findViewById(R.id.itemPageImage);
        TextView itemTitle = findViewById(R.id.itemPageText);
        TextView itemDescription = findViewById(R.id.itemPageDescroption);
        TextView itemParticipants = findViewById(R.id.itemParticipants);
        TextView itemRD = findViewById(R.id.itemRD);

        itemBg.setBackgroundColor(getIntent().getIntExtra("itemBg", 0));
        itemImage.setImageResource(getIntent().getIntExtra("itemImage", 0));
        itemDescription.setText(getIntent().getStringExtra("itemDescription"));
        itemParticipants.setText(getIntent().getStringExtra("itemParticipants"));
        itemTitle.setText(getIntent().getStringExtra("itemTitle"));
        itemRD.setText(getIntent().getStringExtra("itemRD"));



    }


    public void addToCart(View view){
        int item_id = getIntent().getIntExtra("itemId", 0);
        Order.items_id.add(item_id);
        Toast.makeText(this, "Added to cart", Toast.LENGTH_LONG).show();

    }
}