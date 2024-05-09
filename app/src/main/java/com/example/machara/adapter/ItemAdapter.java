package com.example.machara.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.machara.R;
import com.example.machara.itemPage;
import com.example.machara.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context context;
    List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View items = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new ItemAdapter.ItemViewHolder(items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemBg.setCardBackgroundColor(Color.parseColor(items.get(position).getColor()));
        int imageid = context.getResources().getIdentifier(items.get(position).getImg(), "drawable", context.getOpPackageName());
        holder.itemImage.setImageResource(imageid);
        holder.itemTitle.setText(items.get(position).getTitle());
        holder.itemDescription.setText(items.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, itemPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                        new Pair<View, String>(holder.itemImage, "itemImage"));

                intent.putExtra("itemBg", Color.parseColor(items.get(holder.getAdapterPosition()).getColor()));
                intent.putExtra("itemTitle",items.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("itemImage", imageid);
                intent.putExtra("itemDescription", items.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("itemParticipants", items.get(holder.getAdapterPosition()).getParticipants());
                intent.putExtra("itemRD", items.get(holder.getAdapterPosition()).getRD());
                intent.putExtra("itemId", items.get(holder.getAdapterPosition()).getId());



                context.startActivity(intent, options.toBundle());
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{

        CardView itemBg;
        ImageView itemImage;
        TextView itemTitle, itemDescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBg = itemView.findViewById(R.id.itemBg);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescroption);
        }
    }


}
