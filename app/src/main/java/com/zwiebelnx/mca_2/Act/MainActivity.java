package com.zwiebelnx.mca_2.Act;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zwiebelnx.mca_2.R;

import java.io.File;


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


        if(Build.VERSION.SDK_INT >= 23){
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            };
            for(String str: permissions){
                if(this.checkSelfPermission (str) != PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }
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
