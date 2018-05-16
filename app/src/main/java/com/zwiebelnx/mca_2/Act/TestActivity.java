package com.zwiebelnx.mca_2.Act;

import android.content.Intent;

import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zwiebelnx.mca_2.Biz.Midi.MidiUtils;
import com.zwiebelnx.mca_2.R;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.zwiebelnx.mca_2.Bean.MusicItem;
import com.zwiebelnx.mca_2.Biz.TestBiz;
import com.zwiebelnx.mca_2.View.DrawItemViewTest;

import junit.framework.Test;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        Button SubmitBtn = findViewById(R.id.SubmitButton_Test);
        Button PlayBtn = findViewById(R.id.PlayButton_Test);
        FloatingActionButton BackBtn = findViewById(R.id.BackButton_Test);
        FloatingActionButton ShareBtn = findViewById(R.id.ShareButton_Test);
        final DrawItemViewTest MainWin = findViewById(R.id.MainWinTest);

        /*
         产生初始化图形
         */
        MainWin.setDrawMode(1);
        List<MusicItem> Mlist = TestBiz.GenItemList();
        Mlist = TestBiz.getImg(Mlist, getResources());
        MainWin.setMlist(Mlist);
        MainWin.invalidate();

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> Slist = MainWin.getSlist();
                String url = "/storage/emulated/0/MCA/Result.mid";
                try {
                    FileOutputStream fos = new FileOutputStream(url);
                    byte[] data = MidiUtils.Generate(Slist, 0);
                    fos.write(data);
                    fos.flush();
                    fos.close();
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(url);
                    player.prepare();
                    player.start();
                } catch (Exception e){
                    e.printStackTrace();
                }
                MainWin.setTestResult(TestBiz.getTest(MainWin.getSlist(), getResources(), 0x3c));
                MainWin.setDrawMode(3);
                MainWin.invalidate();
            }
        });

        PlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> Slist = MainWin.getSlist();
                String url = "/storage/emulated/0/MCA/Result.mid";
                try {
                    FileOutputStream fos = new FileOutputStream(url);
                    byte[] data = MidiUtils.Generate(Slist, 0);
                    fos.write(data);
                    fos.flush();
                    fos.close();
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(url);
                    player.prepare();
                    player.start();
                } catch (Exception e){
                    e.printStackTrace();
                }

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
