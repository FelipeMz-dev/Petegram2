package com.mz_dev.petagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mz_dev.petagram.R;
import com.mz_dev.petagram.adapter.PetAdapter;
import com.mz_dev.petagram.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by FelipeMz on 25/09/2023
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvMainPetsList;
    private final ArrayList<Pet> pets;

    public HomeFragment(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);
        initManager();
        initAdapter();
        return view;
    }

    public ArrayList<Pet> getRvMainPetsList(){
        PetAdapter petAdapter = (PetAdapter) rvMainPetsList.getAdapter();
        return petAdapter.getFavoritePets();
    }

    private void initUI(View view){
        rvMainPetsList = view.findViewById(R.id.rvMainPetsList);
    }

    private void initManager(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMainPetsList.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter(){
        PetAdapter rvAdapter = new PetAdapter(pets, getActivity());
        rvMainPetsList.setAdapter(rvAdapter);
    }
}