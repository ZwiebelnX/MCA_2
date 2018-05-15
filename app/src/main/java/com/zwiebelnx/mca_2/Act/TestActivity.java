package com.zwiebelnx.mca_2.Act;

import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zwiebelnx.mca_2.R;

import java.util.List;

import Bean.MusicItem;
import Biz.TestBiz;
import View.DrawItemVIew;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        Button SubmitBtn = findViewById(R.id.SubmitButton_Test);
        FloatingActionButton BackBtn = findViewById(R.id.BackButton_Test);
        FloatingActionButton ShareBtn = findViewById(R.id.ShareButton_Test);
        DrawItemVIew MainWin = findViewById(R.id.MainWinTest);

        //产生图形
        TestBiz tbiz = new TestBiz();
        List<MusicItem> Mlist = tbiz.GenItemList();
        Mlist = tbiz.getImg(Mlist, getResources());
        MainWin.setMlist(Mlist);

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

    }


}
