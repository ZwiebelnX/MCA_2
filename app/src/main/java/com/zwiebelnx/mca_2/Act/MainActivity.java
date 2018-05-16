package com.zwiebelnx.mca_2.Act;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zwiebelnx.mca_2.R;


/**
 * 只允许存在一个MainActivity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Button CreateBtn = findViewById(R.id.CreateButton);
        Button TestBtn = findViewById(R.id.TestButton);
        FloatingActionButton InfoBtn = findViewById(R.id.InfoButton);

        /*
         创建按键监听器
         跳转至相应Act
         */
        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToCreate = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(ToCreate);
            }
        });
        TestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToTest = new Intent(MainActivity.this, TestActivity.class);
                startActivity(ToTest);
            }
        });
        InfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ButtonShow();
    }


    /*
     动画创建
     */

    private void ButtonShow(){
        ObjectAnimator alphaCreate = ObjectAnimator.ofFloat(findViewById(R.id.CreateButton),"alpha",0f,1f);
        ObjectAnimator alphaTest = ObjectAnimator.ofFloat(findViewById(R.id.TestButton),"alpha", 0f, 1f);
        alphaCreate.setDuration(1500);
        alphaTest.setDuration(1500);
        alphaTest.setStartDelay(300);
        alphaCreate.start();
        alphaTest.start();
    }
}
