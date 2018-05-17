package com.zwiebelnx.mca_2.Act;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zwiebelnx.mca_2.Anim.AllAnim;
import com.zwiebelnx.mca_2.Bean.MusicItem;
import com.zwiebelnx.mca_2.Biz.CreateBiz;
import com.zwiebelnx.mca_2.Biz.Midi.MidiUtils;
import com.zwiebelnx.mca_2.R;
import com.zwiebelnx.mca_2.View.DrawItemViewCreate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class CreateActivity extends AppCompatActivity {
    private static int ShapeIndex = 1;
    private static int ColorIndex = 1;
    private static int BrightIndex = 1;
    private static int PitchIndex = 0;
    private static String SoundFileUrl = "";

    private static boolean ShapeExtend = false;
    private static boolean ColorExtend = false;
    private static boolean BrightExtend = false;
    private static boolean PitchExtend = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create);

        File CreateChache = new File("/storage/emulated/0/MCA/cacheCreate/");
        if(!CreateChache.exists()){
            CreateChache.mkdirs();
            for(int i = 0;i <7; i++){
                try{
                    String url = "/storage/emulated/0/MCA/cacheCreate/"+i+"pitch.mid";
                    List<Integer> Slist = new ArrayList<>();
                    Slist.add(0x40+i*2);
                    FileOutputStream fos = new FileOutputStream(url);
                    byte[] data = MidiUtils.Generate(Slist, 0);
                    fos.write(data);
                    fos.flush();
                    fos.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

        FloatingActionButton BackBtn = findViewById(R.id.BackButton_Create);
        Button ShareBtn = findViewById(R.id.ShareButton_Create);
        Button PlayBtn = findViewById(R.id.PlayButton_Create);
        ImageView PreView = findViewById(R.id.PreView);

        Button ItemShapeBtn = findViewById(R.id.ItemShapeButton);
        Button ItemColorBtn = findViewById(R.id.ItemColorButton);
        Button ItemBrightBtn = findViewById(R.id.ItemBrightButton);
        Button ItemPitchBtn = findViewById(R.id.ItemPitchButton);

        final DrawItemViewCreate MainWin = findViewById(R.id.MainWinCreate);

        ItemShapeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ShapeExtend){
                    findViewById(R.id.shape1).setAnimation(AllAnim.showUp(0,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.shape2).setAnimation(AllAnim.showUp(200,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.shape3).setAnimation(AllAnim.showUp(400,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.shape3).setVisibility(View.VISIBLE);
                    findViewById(R.id.shape2).setVisibility(View.VISIBLE);
                    findViewById(R.id.shape1).setVisibility(View.VISIBLE);
                    ShapeExtend = true;
                }
                else{
                    findViewById(R.id.shape3).setAnimation(AllAnim.showOut(0, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.shape2).setAnimation(AllAnim.showOut(200, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.shape1).setAnimation(AllAnim.showOut(400, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.shape1).setVisibility(View.GONE);
                    findViewById(R.id.shape2).setVisibility(View.GONE);
                    findViewById(R.id.shape3).setVisibility(View.GONE);
                    ShapeExtend = false;
                }
            }
        });
        ItemColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ColorExtend){
                    findViewById(R.id.color1).setAnimation(AllAnim.showUp(0,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.color2).setAnimation(AllAnim.showUp(200,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.color3).setAnimation(AllAnim.showUp(400,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.color3).setVisibility(View.VISIBLE);
                    findViewById(R.id.color2).setVisibility(View.VISIBLE);
                    findViewById(R.id.color1).setVisibility(View.VISIBLE);
                    ColorExtend = true;
                }
                else{
                    findViewById(R.id.color3).setAnimation(AllAnim.showOut(0, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.color2).setAnimation(AllAnim.showOut(200, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.color1).setAnimation(AllAnim.showOut(400, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.color1).setVisibility(View.GONE);
                    findViewById(R.id.color2).setVisibility(View.GONE);
                    findViewById(R.id.color3).setVisibility(View.GONE);
                    ColorExtend = false;
                }
            }
        });

        ItemBrightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BrightExtend){
                    findViewById(R.id.bright1).setAnimation(AllAnim.showUp(0,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.bright2).setAnimation(AllAnim.showUp(200,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.bright3).setAnimation(AllAnim.showUp(400,300,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.bright3).setVisibility(View.VISIBLE);
                    findViewById(R.id.bright2).setVisibility(View.VISIBLE);
                    findViewById(R.id.bright1).setVisibility(View.VISIBLE);
                    BrightExtend = true;
                }
                else{
                    findViewById(R.id.bright3).setAnimation(AllAnim.showOut(0, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.bright2).setAnimation(AllAnim.showOut(200, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.bright1).setAnimation(AllAnim.showOut(400, 300, AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.bright1).setVisibility(View.GONE);
                    findViewById(R.id.bright2).setVisibility(View.GONE);
                    findViewById(R.id.bright3).setVisibility(View.GONE);
                    BrightExtend = false;
                }
            }
        });
        ItemPitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!PitchExtend){
                    findViewById(R.id.pitch1).setAnimation(AllAnim.showUp(0,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch2).setAnimation(AllAnim.showUp(100,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch3).setAnimation(AllAnim.showUp(200,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch4).setAnimation(AllAnim.showUp(300,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch5).setAnimation(AllAnim.showUp(400,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch6).setAnimation(AllAnim.showUp(500,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch7).setAnimation(AllAnim.showUp(600,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch7).setVisibility(View.VISIBLE);
                    findViewById(R.id.pitch6).setVisibility(View.VISIBLE);
                    findViewById(R.id.pitch5).setVisibility(View.VISIBLE);
                    findViewById(R.id.pitch4).setVisibility(View.VISIBLE);
                    findViewById(R.id.pitch3).setVisibility(View.VISIBLE);
                    findViewById(R.id.pitch2).setVisibility(View.VISIBLE);
                    findViewById(R.id.pitch1).setVisibility(View.VISIBLE);
                    PitchExtend = true;
                }
                else{
                    findViewById(R.id.pitch7).setAnimation(AllAnim.showOut(0,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch6).setAnimation(AllAnim.showOut(100,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch5).setAnimation(AllAnim.showOut(200,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch4).setAnimation(AllAnim.showOut(300,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch3).setAnimation(AllAnim.showOut(400,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch2).setAnimation(AllAnim.showOut(500,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch1).setAnimation(AllAnim.showOut(600,150,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.pitch7).setVisibility(View.GONE);
                    findViewById(R.id.pitch6).setVisibility(View.GONE);
                    findViewById(R.id.pitch5).setVisibility(View.GONE);
                    findViewById(R.id.pitch4).setVisibility(View.GONE);
                    findViewById(R.id.pitch3).setVisibility(View.GONE);
                    findViewById(R.id.pitch2).setVisibility(View.GONE);
                    findViewById(R.id.pitch1).setVisibility(View.GONE);
                    PitchExtend = false;
                }
            }
        });

        Button[] ShapeBtnSet = {
                findViewById(R.id.shape1),
                findViewById(R.id.shape2),
                findViewById(R.id.shape3),
        };
        MainWin.setClickable(false);
        for(int i = 1; i <= 3; i++){
            ShapeBtnSet[i-1].setTag(i);
            ShapeBtnSet[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int)v.getTag();
                    if(tag == 2){
                        findViewById(R.id.color3).setClickable(false);
                        Toast.makeText(CreateActivity.this, "矩形没有蓝色哟", Toast.LENGTH_LONG).show();
                    }
                    else{
                        findViewById(R.id.color3).setClickable(true);
                    }
                    ShapeIndex = tag;
                    CreateBiz.refreshPreView(ShapeIndex, ColorIndex, BrightIndex, (ImageView)findViewById(R.id.PreView));
                }
            });
        }

        Button[] ColorBtnSet = {
                findViewById(R.id.color1),
                findViewById(R.id.color2),
                findViewById(R.id.color3)
        };
        for(int i = 1; i<=3; i++){
            ColorBtnSet[i-1].setTag(i);
            ColorBtnSet[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int)v.getTag();
                    ColorIndex = tag;
                    CreateBiz.refreshPreView(ShapeIndex, ColorIndex, BrightIndex,(ImageView)findViewById(R.id.PreView));
                }
            });
        }

        Button[] BrightBtnSet = {
                findViewById(R.id.bright1),
                findViewById(R.id.bright2),
                findViewById(R.id.bright3),
        };
        for(int i = 1; i <= 3; i++){
            BrightBtnSet[i-1].setTag(i);
            BrightBtnSet[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int)v.getTag();
                    BrightIndex = tag;
                    CreateBiz.refreshPreView(ShapeIndex, ColorIndex, BrightIndex, (ImageView)findViewById(R.id.PreView));
                }
            });
        }

        Button[] PitchBtnSet = {
                findViewById(R.id.pitch1),
                findViewById(R.id.pitch2),
                findViewById(R.id.pitch3),
                findViewById(R.id.pitch4),
                findViewById(R.id.pitch5),
                findViewById(R.id.pitch6),
                findViewById(R.id.pitch7)
        };
        for(int i = 0; i < 7;i++){
            PitchBtnSet[i].setTag(i);
            PitchBtnSet[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int)v.getTag();
                    PitchIndex = 0x40+tag*2;
                    try{
                        MediaPlayer player = new MediaPlayer();
                        SoundFileUrl = "/storage/emulated/0/MCA/cacheCreate/"+tag+"pitch.mid";
                        player.setDataSource(SoundFileUrl);
                        player.prepare();
                        player.start();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        PreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundFileUrl = "/storage/emulated/0/MCA/cacheCreate/"+PitchIndex+"pitch.mid";
                MusicItem musicItem = new MusicItem(ShapeIndex, ColorIndex, BrightIndex, PitchIndex, SoundFileUrl, getResources());
                MainWin.getMlist().add(musicItem);
                MainWin.setDrawMode(2);
                MainWin.invalidate();
            }
        });
        PlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.SetSlist(MainWin.getLlist(),MainWin.getMlist());
                if(MainWin.getSlist().isEmpty()){
                    Toast.makeText(CreateActivity.this, "请先连接再试听喔", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        FileOutputStream fos = new FileOutputStream("/storage/emulated/0/MCA/cacheCreate/Result.mid");
                        byte[] data = MidiUtils.Generate(MainWin.getSlist(), 0);
                        fos.write(data);
                        fos.flush();
                        fos.close();
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource("/storage/emulated/0/MCA/cacheCreate/Result.mid");
                        player.prepare();
                        player.start();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
