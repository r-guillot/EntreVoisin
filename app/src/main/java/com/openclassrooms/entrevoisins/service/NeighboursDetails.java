package com.openclassrooms.entrevoisins.service;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment.EXTRA_ADDRESS;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment.EXTRA_AVATAR;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment.EXTRA_NAME;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment.EXTRA_PHONENUMBER;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment.EXTRA_RESUME;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbours_details);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Toolbar Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String avatarUrl = intent.getStringExtra(EXTRA_AVATAR);
        String address = intent.getStringExtra(EXTRA_ADDRESS);
        String phone_Number = intent.getStringExtra(EXTRA_PHONENUMBER);
        String about_Me = intent.getStringExtra(EXTRA_RESUME);

        nameImage.setText(name);
        nameCard.setText(name);
        localisation.setText(address);
        phoneNumber.setText(phone_Number);
        aboutMe.setText(about_Me);
        facebook.setText("www.facebook.fr/"+name);
        Picasso.get().load(avatarUrl).into(imageDetails); //use picasso to load image url in the imageview.

    }

}
