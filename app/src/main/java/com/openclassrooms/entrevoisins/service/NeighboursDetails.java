package com.openclassrooms.entrevoisins.service;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.squareup.picasso.Picasso;

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

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbours_details);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();

        setToolbarAttribute();

        setListeners();

        mNeighbour = getIntent().getExtras().getParcelable("Neighbour item");

        nameImage.setText(mNeighbour.getName());
        nameCard.setText(mNeighbour.getName());
        localisation.setText(mNeighbour.getAddress());
        phoneNumber.setText(mNeighbour.getPhoneNumber());
        aboutMe.setText(mNeighbour.getAboutMe());
        facebook.setText("www.facebook.fr/"+mNeighbour.getName());
        Picasso.get().load(mNeighbour.getAvatarUrl()).into(imageDetails); //use picasso to load image url in the imageview.

        fabColor();
    }

    private void setToolbarAttribute () {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Toolbar Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setListeners() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFavoriteState();
            }
        });
    }

    private void changeFavoriteState() {
        mApiService.isFavorite(mNeighbour);
        mNeighbour.setIsfavorite(!mNeighbour.isfavorite());
        fabColor();
    }

    private void fabColor() {
        if (!mNeighbour.isfavorite()) {
            mFab.setColorFilter(Color.GRAY);
        }
        else if (mNeighbour.isfavorite()) {
            mFab.setColorFilter(Color.YELLOW);
        }
    }
}
