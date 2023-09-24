package com.mz_dev.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import static com.mz_dev.petagram.MainActivity.PETS_OBJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class RatingActivity extends AppCompatActivity {

    private ArrayList<Pet> pets;
    private RecyclerView rvRatingPetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Toolbar myToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pets = (ArrayList<Pet>) bundle.getSerializable(PETS_OBJ);
        }
        if (pets == null) pets = new ArrayList<>();
        initUI();
        initAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void initUI(){
        rvRatingPetsList = findViewById(R.id.rvRatingPetsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRatingPetsList.setLayoutManager(linearLayoutManager);
        ImageButton btnStar = findViewById(R.id.btnStar);
        btnStar.setVisibility(View.GONE);
    }

    private void initAdapter(){
        Collections.reverse(pets);
        PetAdapter adapter = new PetAdapter(pets, this);
        rvRatingPetsList.setAdapter(adapter);
        adapter.setRatingEnabled(true);
    }
}