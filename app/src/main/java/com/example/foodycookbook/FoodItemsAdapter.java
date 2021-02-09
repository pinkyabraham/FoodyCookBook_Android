package com.example.foodycookbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.FoodViewHolder> implements Filterable {
    private List<Food> foodList;
    private List<Food> foodListFull;
    static ArrayList<String> foodname = new ArrayList<>();

    Context context;
    public FoodItemsAdapter(Context context, List<Food> foodList) {
        this.foodList = foodList;
        foodListFull = new ArrayList<>(foodList);
        this.context=context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditems_recyclerview,
                parent, false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food currentItem = foodList.get(position);

        Glide.with(context).load(currentItem.getImageResource()).placeholder(R.drawable.ic_action_food).into(holder.imageView);
//        holder.imageView.setImage(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        foodname.add(currentItem.getText1());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Food> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(foodListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Food item : foodListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            foodList.clear();
            foodList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        Button favouritesBT;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.foodimage);
            textView1 = itemView.findViewById(R.id.foodname);
            favouritesBT = itemView.findViewById(R.id.favourites);
            favouritesBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Items added to favourites list", Toast.LENGTH_SHORT).show();
                    String name = foodname.get(getAdapterPosition());
                }
            });

        }

    }
}