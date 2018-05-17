package com.zwiebelnx.mca_2.Act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
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
import com.zwiebelnx.mca_2.Biz.TestBiz;
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
    private static boolean isRedoExtend = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create);

        for (int i = 0; i < 7; i++) {
            try {
                String url = "/storage/emulated/0/MCA/" + i + "pitch.mid";
                List<Integer> Slist = new ArrayList<>();
                Slist.add(0x40 + i * 2);
                FileOutputStream fos = new FileOutputStream(url);
                byte[] data = MidiUtils.Generate(Slist, 0);
                fos.write(data);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


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
                        ColorIndex = 1;
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
                    ColorIndex = (int)v.getTag();
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
                    BrightIndex = (int)v.getTag();
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
                    try{
                        PitchIndex = tag;
                        MediaPlayer player = new MediaPlayer();
                        SoundFileUrl = "/storage/emulated/0/MCA/"+tag+"pitch.mid";
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
                SoundFileUrl = "/storage/emulated/0/MCA/"+PitchIndex+"pitch.mid";
                MusicItem musicItem = new MusicItem(ShapeIndex, ColorIndex, BrightIndex, PitchIndex, SoundFileUrl, getResources());
                MainWin.getMlist().add(musicItem);
                MainWin.setDrawMode(1);
                MainWin.invalidate();
            }
        });
        PlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.GenSlist();
                if(MainWin.getSlist().isEmpty()){
                    Toast.makeText(CreateActivity.this, "请先连接再试听喔", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        FileOutputStream fos = new FileOutputStream("/storage/emulated/0/MCA/ResultCreate.mid");
                        byte[] data = MidiUtils.Generate(MainWin.getSlist(), 0);
                        fos.write(data);
                        fos.flush();
                        fos.close();
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource("/storage/emulated/0/MCA/ResultCreate.mid");
                        player.prepare();
                        player.start();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ResultCreateImg;
                Uri ResultCreateMidi;
                View dView = getWindow().getDecorView();
                dView.setDrawingCacheEnabled(true);
                dView.destroyDrawingCache();
                dView.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
                try{
                    FileOutputStream fos2=new FileOutputStream("/storage/emulated/0/MCA/ResultCreateImg.png");
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,fos2);
                    fos2.flush();
                    fos2.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                if(Build.VERSION.SDK_INT>=24){
                    ResultCreateImg = FileProvider.getUriForFile(CreateActivity.this,getPackageName()+".provider",new File("/storage/emulated/0/MCA/ResultCreateImg.png"));
                    ResultCreateMidi = FileProvider.getUriForFile(CreateActivity.this,getPackageName()+".provider",new File("/storage/emulated/0/MCA/ResultCreate.mid"));
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }else {
                    ResultCreateImg = Uri.fromFile(new File("/storage/emulated/0/MCA/ResultCreateImg.png"));
                    ResultCreateMidi = Uri.fromFile(new File("/storage/emulated/0/MCA/ResultCreate.mid"));
                }
                ArrayList<Uri> uris=new ArrayList();
                uris.add(ResultCreateImg);
                uris.add(ResultCreateMidi);
                intent.setType("*/*");
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                try {
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.Redo_Reverst_Create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.ReverstLlist();
            }
        });
        findViewById(R.id.Redo_Cencel_Create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.CancelLlist();
            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackToMain = new Intent("com.zwiebelnx.mca.BACKTOMAIN");
                startActivity(BackToMain);
            }
        });

        findViewById(R.id.RedoButtonCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRedoExtend){
                    findViewById(R.id.Redo_Reverst_Create).startAnimation(AllAnim.showOut(0,100,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.Redo_Cencel_Create).startAnimation(AllAnim.showOut(50,100,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.Redo_Reverst_Create).setVisibility(View.GONE);
                    findViewById(R.id.Redo_Cencel_Create).setVisibility(View.GONE);
                    isRedoExtend = false;
                }
                else {
                    findViewById(R.id.Redo_Reverst_Create).setVisibility(View.VISIBLE);
                    findViewById(R.id.Redo_Cencel_Create).setVisibility(View.VISIBLE);
                    findViewById(R.id.Redo_Reverst_Create).startAnimation(AllAnim.showUp(50,100,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.Redo_Cencel_Create).startAnimation(AllAnim.showUp(0,100,AllAnim.DIRECTION_FROM_BUTTOM));
                    isRedoExtend = true;
                }
            }
        });
        Button BtnBarSet[] = {ItemShapeBtn, ItemColorBtn, ItemBrightBtn, ItemPitchBtn, PlayBtn, ShareBtn};
        for(int i = 0; i < 6; i++){
            BtnBarSet[i].startAnimation(AllAnim.showUp(100*i,200,AllAnim.DIRECTION_FROM_BUTTOM));
        }
        BackBtn.startAnimation(AllAnim.showUp(0,800,AllAnim.DIRECTION_FROM_LEFT));
        PreView.startAnimation(AllAnim.showUp(0,800,AllAnim.DIRECTION_FROM_TOP));
        findViewById(R.id.RedoButtonCreate).startAnimation(AllAnim.showUp(0,800,AllAnim.DIRECTION_FROM_RIGHT));

    }
}
