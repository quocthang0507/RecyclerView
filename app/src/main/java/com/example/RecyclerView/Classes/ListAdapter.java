package com.example.RecyclerView.Classes;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RecyclerView.Activities.SecondFragment;
import com.example.RecyclerView.R;

import java.io.File;
import java.util.ArrayList;

/**
 * The adapter for RecyclerView
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<FoodItem> dataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private final TextView textView_name, textView_price;
        private final ImageView imgFood;
        private final ImageButton btnEdit;
        private final Context context;
        private final ListAdapter adapter; // Notify when item is deleted

        public ViewHolder(Context context, View view, ListAdapter adapter) {
            super(view);

            textView_name = view.findViewById(R.id.textView_name);
            textView_price = view.findViewById(R.id.textView_price);
            imgFood = view.findViewById(R.id.imageView_food);
            btnEdit = view.findViewById(R.id.button_edit);
            this.context = context;
            this.adapter = adapter;

            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // Create a context menu
            MenuItem menuDelete = menu.add(R.string.menu_delete);
            menuDelete.setIcon(android.R.drawable.ic_menu_delete);
            menuDelete.setOnMenuItemClickListener(item -> {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confirm");
                alertDialogBuilder.setMessage(R.string.msg_delete_confirm);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setPositiveButton(R.string.menu_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dataset.getInstance().delete(getAdapterPosition());
                        Toast.makeText(context, R.string.msg_delete_successfully, Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged(); // Reload Recycler View
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
                alertDialogBuilder.create().show();
                return true;
            });
        }
    }

    public ListAdapter(Context context, ArrayList<FoodItem> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_row_item, parent, false);
        return new ViewHolder(context, view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem selectedItem = dataset.get(position);

        holder.textView_name.setText(selectedItem.Name);
        holder.textView_price.setText("Price: " + Utils.formatCurrency(selectedItem.Price));

        if (!selectedItem.ImgThumb.contains("content://")) { // If ImgThumb is a file name
            int imgId = context.getResources().getIdentifier(selectedItem.ImgThumb, "drawable", context.getPackageName());
            holder.imgFood.setImageResource(imgId);
        } else { // If ImgThumb is a Uri
            holder.imgFood.setImageURI(Uri.parse(selectedItem.ImgThumb));
        }
        holder.imgFood.setTag(selectedItem.ImgThumb);

        holder.btnEdit.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            NavHostFragment navHostFragment =
                    (NavHostFragment) activity.getSupportFragmentManager()
                            .findFragmentById(R.id.nav_host_fragment_content_main);

            Bundle bundle = new Bundle();
            bundle.putInt(SecondFragment.EXTRA_ACTION_CODE, holder.getAdapterPosition());

            NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }
}
