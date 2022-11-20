package com.example.RecyclerView.Classes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RecyclerView.Activities.SecondFragment;
import com.example.RecyclerView.R;

import java.util.ArrayList;

/**
 * The adapter for RecyclerView
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<FoodItem> dataset;
    private Context parentContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView_name, textView_price;
        private final ImageView imgFood;
        private final ImageButton btnEdit;

        public ViewHolder(View view) {
            super(view);

            textView_name = view.findViewById(R.id.textView_name);
            textView_price = view.findViewById(R.id.textView_price);
            imgFood = view.findViewById(R.id.imageView_food);
            btnEdit = view.findViewById(R.id.button_edit);
        }

        public TextView getTextView_name() {
            return textView_name;
        }

        public TextView getTextView_price() {
            return textView_price;
        }

        public ImageView getImgFood() {
            return imgFood;
        }

        public ImageButton getBtnEdit() {
            return btnEdit;
        }
    }

    public ListAdapter(Context context, ArrayList<FoodItem> dataset) {
        this.parentContext = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView_name().setText(dataset.get(position).Name);
        holder.getTextView_price().setText("Price: " + dataset.get(position).Price + " VND");
        int imgId = parentContext.getResources().getIdentifier(dataset.get(position).ImgThumbPath, "drawable", parentContext.getPackageName());
        holder.getImgFood().setImageResource(imgId);
        holder.getImgFood().setTag(imgId);
        holder.getBtnEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                NavHostFragment navHostFragment =
                        (NavHostFragment) activity.getSupportFragmentManager()
                                .findFragmentById(R.id.nav_host_fragment_content_main);

                Bundle bundle = new Bundle();
                bundle.putInt(SecondFragment.EXTRA_ACTION_CODE, holder.getAdapterPosition());

                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
