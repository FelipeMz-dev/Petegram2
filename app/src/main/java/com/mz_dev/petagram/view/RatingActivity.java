package com.mz_dev.petagram.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static com.mz_dev.petagram.view.MainActivity.PETS_OBJ;

import com.mz_dev.petagram.OptionsMenuHandler;
import com.mz_dev.petagram.adapter.PetAdapter;
import com.mz_dev.petagram.R;
import com.mz_dev.petagram.pojo.Pet;

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (OptionsMenuHandler.handleOptionsItemSelected(item, this)) return true;
        return super.onOptionsItemSelected(item);
    }

    private void initUI(){
        rvRatingPetsList = findViewById(R.id.rvRatingPetsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRatingPetsList.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter(){
        Collections.reverse(pets);
        PetAdapter adapter = new PetAdapter(pets, this);
        rvRatingPetsList.setAdapter(adapter);
        adapter.setRatingEnabled(true);
    }
}