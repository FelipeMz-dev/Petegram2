package com.mz_dev.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mz_dev.petagram.R;
import com.mz_dev.petagram.pojo.ImageProfile;

import java.util.ArrayList;

public class ProfilePetAdapter extends RecyclerView.Adapter<ProfilePetAdapter.PetProfileViewHolder> {

    private ArrayList<ImageProfile> images;

    public ProfilePetAdapter(ArrayList<ImageProfile> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public PetProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image_pet_profile, parent, false);
        return new PetProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetProfileViewHolder holder, int position) {
        ImageProfile image = images.get(position);
        holder.ivProfilePet.setImageResource(image.getImage());
        holder.tvProfileRatingImage.setText(String.valueOf(image.getRating()));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class PetProfileViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfilePet;
        TextView tvProfileRatingImage;

        public PetProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePet = itemView.findViewById(R.id.ivProfilePet);
            tvProfileRatingImage = itemView.findViewById(R.id.tvProfileRatingImage);
        }
    }
}
