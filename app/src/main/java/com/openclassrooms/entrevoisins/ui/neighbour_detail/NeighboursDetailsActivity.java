package com.openclassrooms.entrevoisins.ui.neighbour_detail;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighboursDetailsActivity extends AppCompatActivity {

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
    public TextView facebookTextView;

    @BindView(R.id.FAB_detail)
    public FloatingActionButton mFab;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

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

        setGraphicElement();

        fabColor();
    }

    public void setGraphicElement () {
        mNeighbour = getIntent().getExtras().getParcelable("Neighbour item");

        nameImage.setText(mNeighbour.getName());
        nameCard.setText(mNeighbour.getName());
        localisation.setText(mNeighbour.getAddress());
        phoneNumber.setText(mNeighbour.getPhoneNumber());
        aboutMe.setText(mNeighbour.getAboutMe());
        facebookTextView.setText("www.facebook.fr/"+mNeighbour.getName());
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(imageDetails);
    }

    private void setToolbarAttribute () {

        // Toolbar Transparent
        mToolbar.setBackgroundColor(Color.TRANSPARENT);

        setSupportActionBar(mToolbar);
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
        mNeighbour.setIsFavorite(!mNeighbour.isFavorite());
        fabColor();
    }

    private void fabColor() {
        mFab.setColorFilter((mNeighbour.isFavorite()) ? Color.YELLOW : Color.GRAY);
    }
}
