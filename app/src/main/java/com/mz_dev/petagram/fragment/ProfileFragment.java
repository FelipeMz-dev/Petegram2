package com.mz_dev.petagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mz_dev.petagram.R;
import com.mz_dev.petagram.adapter.PetAdapter;
import com.mz_dev.petagram.adapter.ProfilePetAdapter;
import com.mz_dev.petagram.pojo.ImageProfile;
import com.mz_dev.petagram.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by FelipeMz on 25/09/2023
 */
public class ProfileFragment extends Fragment {

    private final Pet petProfile;
    private final ArrayList<ImageProfile> images;
    private ImageView ivPetProfile;
    private TextView tvPetNameProfile;
    private RecyclerView rvProfileImages;

    public ProfileFragment(Pet petProfile, ArrayList<ImageProfile> images) {
        this.petProfile = petProfile;
        this.images = images;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initUI(view);
        initData();
        initManager();
        initAdapter();
        return view;
    }

    private void initUI(View view){
        ivPetProfile = (ImageView) view.findViewById(R.id.ivPetProfile);
        tvPetNameProfile = view.findViewById(R.id.tvPetNameProfile);
        rvProfileImages = view.findViewById(R.id.rvProfileImages);
    }

    private void initData(){
        ivPetProfile.setImageResource(petProfile.getImage());
        tvPetNameProfile.setText(petProfile.getName());
    }

    private void initManager(){
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3);;
        rvProfileImages.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter(){
        ProfilePetAdapter rvAdapter = new ProfilePetAdapter(images);
        rvProfileImages.setAdapter(rvAdapter);
    }
}