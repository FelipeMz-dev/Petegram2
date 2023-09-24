package com.mz_dev.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pet> pets;
    private RecyclerView rvMainPetsList;
    private PetAdapter rvAdapter;
    private FloatingActionButton fabCamera;
    private ImageButton btnStar;
    public static final String PETS_OBJ = "Pets_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        initUI();
        initPetList();
        initAdapter();
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void initUI(){
        fabCamera = findViewById(R.id.fabCamera);
        rvMainPetsList = findViewById(R.id.rvMainPetsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMainPetsList.setLayoutManager(linearLayoutManager);
        btnStar = findViewById(R.id.btnStar);
    }

    private void initAdapter(){
        rvAdapter = new PetAdapter(pets, this);
        rvMainPetsList.setAdapter(rvAdapter);
    }

    private void initPetList(){
        pets = new ArrayList<>();
        pets.add(new Pet("Simon", R.drawable.pet1, 20));
        pets.add(new Pet("Negra", R.drawable.pet2, 5));
        pets.add(new Pet("Lulu", R.drawable.pet3, 6));
        pets.add(new Pet("Luna", R.drawable.pet4, 5));
        pets.add(new Pet("Midas", R.drawable.pet5, 12));
    }

    private void initListener(){
        fabCamera.setOnClickListener(v -> {
            //TODO: on click camera
        });
        btnStar.setOnClickListener(v -> goRatingActivity());
    }

    private void goRatingActivity(){
        ArrayList<Pet> favoritePets = rvAdapter.getFavoritePets();
        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra(PETS_OBJ, favoritePets);
        startActivity(intent);
    }
}