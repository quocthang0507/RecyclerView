package com.example.RecyclerView.Activities;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.RecyclerView.Classes.Dataset;
import com.example.RecyclerView.Classes.FoodItem;
import com.example.RecyclerView.R;
import com.example.RecyclerView.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> {
            NavHostFragment navHostFragment =
                    (NavHostFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.nav_host_fragment_content_main);

            // Pass value between activity and fragment
            Bundle bundle = new Bundle();
            bundle.putInt(SecondFragment.EXTRA_ACTION_CODE, -1);

            // Navigate from first fragment (home screen) to second fragment (inserting/updating screen)
            NavController navController1 = navHostFragment.getNavController();
            navController1.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
        });

        generateDataset();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Populate the dataset
     */
    private void generateDataset() {
        Dataset.getInstance().clear();
        Dataset.getInstance().add(new FoodItem(1, "BBQ", 120000, "bbq", "pieces"));
        Dataset.getInstance().add(new FoodItem(2, "Chicken", 60000, "chicken", "combo"));
        Dataset.getInstance().add(new FoodItem(3, "Hot dog", 60000, "hotdog", "pack"));
    }
}