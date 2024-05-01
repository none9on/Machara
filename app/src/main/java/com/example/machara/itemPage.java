package com.example.machara;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        itemBg.setBackgroundColor(getIntent().getIntExtra("itemBg", 0));
        itemImage.setImageResource(getIntent().getIntExtra("itemImage", 0));
        itemDescription.setText(getIntent().getStringExtra("itemDescription"));
        itemParticipants.setText(getIntent().getStringExtra("itemParticipants"));
        itemTitle.setText(getIntent().getStringExtra("itemTitle"));


    }
}