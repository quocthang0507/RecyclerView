package com.example.RecyclerView.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.RecyclerView.Classes.Dataset;
import com.example.RecyclerView.Classes.FoodItem;
import com.example.RecyclerView.Classes.ListAdapter;
import com.example.RecyclerView.R;
import com.example.RecyclerView.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private ListAdapter foodAdapter; // Custom adapter for RecyclerView
    private FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(container.getContext().getPackageName(), "Called onCreateView() in MainFragment");

        binding = FragmentMainBinding.inflate(inflater, container, false);

        foodAdapter = new ListAdapter(getActivity(), Dataset.getInstance().getDataset());
        binding.foodList.setAdapter(foodAdapter);
        binding.foodList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.d(getContext().getPackageName(), "onViewCreated MainFragment");

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}