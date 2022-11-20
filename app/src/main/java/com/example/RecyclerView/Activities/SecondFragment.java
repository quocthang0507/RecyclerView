package com.example.RecyclerView.Activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.RecyclerView.Classes.Dataset;
import com.example.RecyclerView.Classes.FoodItem;
import com.example.RecyclerView.R;
import com.example.RecyclerView.databinding.FragmentSecondBinding;
import com.google.android.material.snackbar.Snackbar;

public class SecondFragment extends Fragment implements View.OnClickListener {

    public final static String EXTRA_ACTION_CODE = "com.example.RecyclerView.ACTION"; // -1 to create new, otherwise to edit

    private ImageView imgFood;
    private EditText editText_id, editText_name, editText_price, editText_unit;
    private Button button_save;
    private int action_code = -1;
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(container.getContext().getPackageName(), "Called onCreateView() in SecondFragment");

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        action_code = getArguments().getInt(EXTRA_ACTION_CODE);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.d(getContext().getPackageName(), "onViewCreated SecondFragment");

        super.onViewCreated(view, savedInstanceState);

        initViews();

        if (action_code != -1) {
            FoodItem selectedItem = Dataset.getInstance().getById(action_code);
            editText_id.setText(String.valueOf(selectedItem.ID));
            editText_name.setText(selectedItem.Name);
            editText_price.setText(String.valueOf(selectedItem.Price));
            editText_unit.setText(selectedItem.Unit);
            int imgId = getResources().getIdentifier(selectedItem.ImgThumbPath, "drawable", getActivity().getPackageName());
            imgFood.setImageResource(imgId);
            imgFood.setTag(selectedItem.ImgThumbPath);
        } else {
            editText_id.setText(String.valueOf(Dataset.getInstance().getMaxId() + 1));
            imgFood.setTag("");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Initialize views in second fragment
     */
    private void initViews() {
        imgFood = getActivity().findViewById(R.id.imageView_food_2);
        editText_name = getActivity().findViewById(R.id.edit_text_name);
        editText_id = getActivity().findViewById(R.id.edit_text_id);
        editText_price = getActivity().findViewById(R.id.edit_text_price);
        editText_unit = getActivity().findViewById(R.id.edit_text_unit);
        button_save = getActivity().findViewById(R.id.button_save);
        // An another way to assign click event handler for button
        button_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Get values
        String name = editText_name.getText().toString();
        String unit = editText_unit.getText().toString();
        int price = Integer.parseInt(editText_price.getText().toString());
        String imgPath = imgFood.getTag().toString();

        // Update an existing item
        if (action_code != -1) {
            Dataset.getInstance().update(new FoodItem(action_code, name, price, imgPath, unit));
            Snackbar.make(getActivity().findViewById(R.id.button_save), R.string.msg_save_successfully, Snackbar.LENGTH_LONG).show();
        } else {
            // Create a new item
            Dataset.getInstance().add(new FoodItem(action_code, name, price, imgPath, unit));
            Snackbar.make(getActivity().findViewById(R.id.button_save), R.string.msg_save_unsuccessfully, Snackbar.LENGTH_LONG).show();
        }

        // Go back to first fragment
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }
}