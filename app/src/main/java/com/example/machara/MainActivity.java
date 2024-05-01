package com.example.machara;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.machara.adapter.CategoryAdapter;
import com.example.machara.adapter.ItemAdapter;
import com.example.machara.model.Category;
import com.example.machara.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, itemRecycler;
    CategoryAdapter categoryAdapter;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "films"));
        categoryList.add(new Category(2, "events"));
        categoryList.add(new Category(3, "museums"));
        categoryList.add(new Category(4, "concerts"));
        categoryList.add(new Category(5, "theatre"));
        setCategoryRecycler(categoryList);

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "poor_things", "poor things",  "The film explores themes of identity, power, and morality in a darkly humorous and surreal manner. Starring Emma Stone, Mark Ruffalo, and Willem Dafoe, \"Poor Things\" promises to be a thought-provoking and visually captivating cinematic experience.", "#E0E07B"));
        itemList.add(new Item(2, "interstellar", "interstellar", "\"The film explores themes of love, sacrifice, and the resilience of the human spirit. With stunning visuals, an emotional and thought-provoking narrative \"Interstellar\" is a cinematic journey through space and time that will leave audiences in awe.", "#F3AE82"));
        itemList.add(new Item(3, "parasite", "parasite",  "The film delves into themes of social class, inequality, and the consequences of greed. \"Parasite\" received critical acclaim for its sharp storytelling, strong performances, and social commentary, ultimately winning the Palme d'Or at the Cannes Film Festival.", "#BBBB9B"));
        itemList.add(new Item(4, "spider_man", "spider man:\nbeyond the spider verse",  "Miles Morales navigates the multiverse with different Spider-Men. Unique animation style and thrilling action sequences make \"Across the Spider-Verse\" is a visually stunning addition to the Spider-Man franchise.", "#E32636"));
        itemList.add(new Item(5, "revolting", "revolting rhymes",  "The film combines different well-known fairy tales, such as Snow White, Little Red Riding Hood, and The Three Little Pigs, into a humorous and twisted story. The animation style is visually stunning and the story is full of unexpected twists finish.", "#FF7591"));

        serItemRecycler(itemList);



    }

    private void serItemRecycler(List<Item> itemList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        itemRecycler = findViewById(R.id.itemRecycler);
        itemRecycler.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(this, itemList);
        itemRecycler.setAdapter(itemAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
}