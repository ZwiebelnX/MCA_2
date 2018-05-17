package com.zwiebelnx.mca_2.Act;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwiebelnx.mca_2.R;
import com.zwiebelnx.mca_2.Anim.AllAnim;

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
        ImageView LogoMain = findViewById(R.id.LogoMain);


        /*
        Android 6.0 以上动态权限的申请
         */
        if(Build.VERSION.SDK_INT >= 23) {
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
        文件夹的创建
         */
        File cacheDir = new File("/storage/emulated/0/MCA/");
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
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

        LogoMain.startAnimation(AllAnim.showUp(0,2000, AllAnim.DIRECTION_FROM_CENTER));
        CreateBtn.startAnimation(AllAnim.showUp(200, 2000, AllAnim.DIRECTION_FROM_RIGHT));
        TestBtn.startAnimation(AllAnim.showUp(400, 2000, AllAnim.DIRECTION_FROM_RIGHT));
        InfoBtn.startAnimation(AllAnim.showUp(600, 2000, AllAnim.DIRECTION_FROM_RIGHT));
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.layout_main);
        Button CreateBtn = findViewById(R.id.CreateButton);
        Button TestBtn = findViewById(R.id.TestButton);
        FloatingActionButton InfoBtn = findViewById(R.id.InfoButton);
        ImageView LogoMain = findViewById(R.id.LogoMain);

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

        /*
        动画处理
         */
        LogoMain.startAnimation(AllAnim.showUp(0, 2000,AllAnim.DIRECTION_FROM_CENTER));
        CreateBtn.startAnimation(AllAnim.showUp(200, 2000, AllAnim.DIRECTION_FROM_RIGHT));
        TestBtn.startAnimation(AllAnim.showUp(400, 2000, AllAnim.DIRECTION_FROM_RIGHT));
        InfoBtn.startAnimation(AllAnim.showUp(600, 2000, AllAnim.DIRECTION_FROM_RIGHT));
    }
}
