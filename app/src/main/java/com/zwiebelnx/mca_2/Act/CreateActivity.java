package com.zwiebelnx.mca_2.Act;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zwiebelnx.mca_2.R;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create);

        Button BackBtn = findViewById(R.id.BackButton_Create);
        Button ShareBtn = findViewById(R.id.ShareButton_Create);
        Button PlayBtn = findViewById(R.id.PlayButton_Create);

        Button ItemShapeBtn = findViewById(R.id.ItemShapeButton);
        Button ItemColorBtn = findViewById(R.id.ItemColorButton);
        Button ItemBrightBtn = findViewById(R.id.ItemBrightButton);
        Button ItemPitchBtn = findViewById(R.id.ItemPitchButton);
    }
}
