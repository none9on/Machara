package com.example.machara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


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
    static ItemAdapter itemAdapter;
    static List<Item> itemList = new ArrayList<>();
    static List<Item> fullItemList = new ArrayList<>();


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


        itemList.add(new Item(1, "poor_things", "poor things",  "The film explores themes of identity, power, and morality in a darkly humorous and surreal manner. Starring Emma Stone, Mark Ruffalo, and Willem Dafoe, \"Poor Things\" promises to be a thought-provoking and visually captivating cinematic experience.", "#E0E07B", "harry styles", 1, "17 may"));
        itemList.add(new Item(2, "interstellar", "interstellar", "\"The film explores themes of love, sacrifice, and the resilience of the human spirit. With stunning visuals, an emotional and thought-provoking narrative \"Interstellar\" is a cinematic journey through space and time that will leave audiences in awe.", "#F3AE82", "olivia rodrigo", 1, "17 may"));
        itemList.add(new Item(3, "parasite", "parasite",  "The film delves into themes of social class, inequality, and the consequences of greed. \"Parasite\" received critical acclaim for its sharp storytelling, strong performances, and social commentary, ultimately winning the Palme d'Or at the Cannes Film Festival.", "#BBBB9B", "one direction", 1, "17 may"));
        itemList.add(new Item(4, "spider_man", "spider man:\nbeyond the spider verse",  "Miles Morales navigates the multiverse with different Spider-Men. Unique animation style and thrilling action sequences make \"Across the Spider-Verse\" is a visually stunning addition to the Spider-Man franchise.", "#E32636", "1d", 1, "17 may"));
        itemList.add(new Item(5, "revolting", "revolting rhymes",  "The film combines different well-known fairy tales, such as Snow White, Little Red Riding Hood, and The Three Little Pigs, into a humorous and twisted story. The animation style is visually stunning and the story is full of unexpected twists finish.", "#FF7591", "bts", 1, "17 may"));
        itemList.add(new Item(6, "anna", "anna karenina", "a well known play", "#B5D1E8", "actors", 5, "1989 16 may"));
        itemList.add(new Item(7, "black_swan", "black_swan", "a well known play", "#E7F0F8", "ballet", 5, "1989 16 may"));
        itemList.add(new Item(8, "candle", "candle concert", "listen to the most beautiful music pieces in the world while looking at beautiful candles.", "#EDE697", "music people", 4, "1989 16 may"));
        itemList.add(new Item(9, "guts", "GUTS TOUR!", "one of the most anticipating tours! by the big pop star olivia rodrigo!", "#CE97ED", "olivia rodrigo", 4, "1989 16 may"));
        itemList.add(new Item(10, "love_on_tour", "LOVE ON TOUR", "harry styles is coming to your town!!", "#ED97CF", "harry styles", 4, "1989 16 may"));
        itemList.add(new Item(11, "mama_mia", "MAMA MIA", "a well known play", "#E7F0F8", "ballet", 5, "1989 16 may"));




        fullItemList.addAll(itemList);
        serItemRecycler(itemList);



    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
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


    public static void showItemByCategory(int category){

        itemList.clear();
        itemList.addAll(fullItemList);
        List<Item> filterItems = new ArrayList<>();
        for(Item c: itemList){
            if(c.getCategory() == category)
                filterItems.add(c);

        }
        itemList.clear();
        itemList.addAll(filterItems);

        itemAdapter.notifyDataSetChanged();

    }
}