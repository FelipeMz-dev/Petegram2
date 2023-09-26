package com.mz_dev.petagram.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mz_dev.petagram.OptionsMenuHandler;
import com.mz_dev.petagram.adapter.PageAdapter;
import com.mz_dev.petagram.R;
import com.mz_dev.petagram.fragment.HomeFragment;
import com.mz_dev.petagram.fragment.ProfileFragment;
import com.mz_dev.petagram.pojo.ImageProfile;
import com.mz_dev.petagram.pojo.Pet;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<Pet> pets;
    private ArrayList<ImageProfile> images;
    private ImageButton btnStar;
    private Toolbar myToolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fabCamera;
    public static final String PETS_OBJ = "Pets_object";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListener();
        initPetList();
        initImagesProfile();
        setUpViewPager();

        setSupportActionBar(myToolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
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

    private ArrayList<Fragment> addFragments(){
        fragments.add(new HomeFragment(pets));
        fragments.add(new ProfileFragment(pets.get(0), images));
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_pet);
    }

    private void initUI(){
        myToolBar = findViewById(R.id.myToolBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viePager);
        btnStar = findViewById(R.id.btnStar);
        btnStar.setVisibility(View.VISIBLE);
        fabCamera = findViewById(R.id.fabCamera);
    }

    private void initListener(){
        btnStar.setOnClickListener(v -> goRatingActivity());
        fabCamera.setOnClickListener(v -> {
            //TODO: on click camera
        });
    }

    private void goRatingActivity(){
        HomeFragment homeFragment = (HomeFragment) fragments.get(0);
        ArrayList<Pet> favoritePets = homeFragment.getRvMainPetsList();
        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra(PETS_OBJ, favoritePets);
        startActivity(intent);
    }

    private void initPetList(){
        pets = new ArrayList<>();
        pets.add(new Pet("Simon", R.drawable.pet1, 20));
        pets.add(new Pet("Negra", R.drawable.pet2, 5));
        pets.add(new Pet("Lulu", R.drawable.pet3, 6));
        pets.add(new Pet("Luna", R.drawable.pet4, 5));
        pets.add(new Pet("Midas", R.drawable.pet5, 12));
    }

    private void initImagesProfile(){
        images = new ArrayList<>();
        images.add(new ImageProfile(R.drawable.pet1, 12));
        images.add(new ImageProfile(R.drawable.pet1, 8));
        images.add(new ImageProfile(R.drawable.pet1, 2));
        images.add(new ImageProfile(R.drawable.pet1, 20));
        images.add(new ImageProfile(R.drawable.pet1, 7));
        images.add(new ImageProfile(R.drawable.pet1, 17));
        images.add(new ImageProfile(R.drawable.pet1, 21));
        images.add(new ImageProfile(R.drawable.pet1, 14));
        images.add(new ImageProfile(R.drawable.pet1, 5));
        images.add(new ImageProfile(R.drawable.pet1, 2));
    }
}