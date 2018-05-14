package com.zwiebelnx.mca_2.Act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zwiebelnx.mca_2.R;

import java.util.List;

import Bean.MusicItem;
import Biz.TestBiz;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        Button SubmitBtn = findViewById(R.id.SubmitButton_Test);
        FloatingActionButton BackBtn = findViewById(R.id.BackButton_Test);
        FloatingActionButton ShareBtn = findViewById(R.id.ShareButton_Test);
        LinearLayout MainWin = findViewById(R.id.MainWinTest);

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackToMain = new Intent("com.zwiebelnx.mca.BACKTOMAIN");
                startActivity(BackToMain);
            }
        });

        TestBiz tb = new TestBiz();
        List<MusicItem> Mlist = tb.GenItemList();
        //SetMusicItems(Mlist);
        Canvas canvas = new Canvas();
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bar_1),250,300,new Paint());
    }


    /*
    private void SetMusicItems(List<MusicItem> Mlist){
        ImageView img;
        LinearLayout MainWin = findViewById(R.id.MainWinTest);
        for(int i = 0;i<Mlist.size();i++){
            switch (Mlist.get(i).getShapeIndex()){
                case 1:img = new ImageView(this);
                    img.setImageResource(R.drawable.bar_1);
                    MainWin.addView(img);
                    break;
                case 2:img = new ImageView(this);
                    img.setImageResource(R.drawable.circle_1);
                    MainWin.addView(img);
                    break;
                case 3:img = new ImageView(this);
                    img.setImageResource(R.drawable.rec_1);
                    MainWin.addView(img);
                    break;
            }
        }

    }*/
}
