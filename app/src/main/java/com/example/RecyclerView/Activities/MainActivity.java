package com.example.RecyclerView.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment =
                        (NavHostFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.nav_host_fragment_content_main);

                // Pass value between activity and fragment
                Bundle bundle = new Bundle();
                bundle.putInt(SecondFragment.EXTRA_ACTION_CODE, -1);

                // Navigate from first fragment (home screen) to second fragment (inserting/updating screen)
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });

        generateDataset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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