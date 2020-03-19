package com.openclassrooms.entrevoisins.service;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.squareup.picasso.Picasso;

import java.security.Key;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighboursDetails extends AppCompatActivity {

    @BindView(R.id.image_details)
    public ImageView imageDetails;
    @BindView(R.id.name_in_image)
    public TextView nameImage;
    @BindView(R.id.name_in_card)
    public TextView nameCard;
    @BindView(R.id.localisation_txt)
    public TextView localisation;
    @BindView(R.id.phone_number)
    public TextView phoneNumber;
    @BindView(R.id.about_me_details)
    public TextView aboutMe;
    @BindView(R.id.web_address)
    public TextView facebook;

    @BindView(R.id.FAB_detail)
    public FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbours_details);
        ButterKnife.bind(this);

        setToolbarAttribute();

        Intent intent = getIntent();
        Neighbour mNeighbour = intent.getParcelableExtra("Neighbour item");

        String name = mNeighbour.getName();
        String avatarUrl = mNeighbour.getAvatarUrl();
        String address = mNeighbour.getAddress();
        String phone_Number = mNeighbour.getPhoneNumber();
        String about_Me = mNeighbour.getAboutMe();
        final boolean[] favorite = {mNeighbour.getFavorite()};

        nameImage.setText(name);
        nameCard.setText(name);
        localisation.setText(address);
        phoneNumber.setText(phone_Number);
        aboutMe.setText(about_Me);
        facebook.setText("www.facebook.fr/"+name);
        Picasso.get().load(avatarUrl).into(imageDetails); //use picasso to load image url in the imageview.

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite[0] = true;

            }
        });

    }

    private void setToolbarAttribute () {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Toolbar Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
