package com.zwiebelnx.mca_2.Act;

import android.content.Intent;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zwiebelnx.mca_2.Anim.AllAnim;
import com.zwiebelnx.mca_2.Biz.Midi.MidiUtils;
import com.zwiebelnx.mca_2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.zwiebelnx.mca_2.Bean.MusicItem;
import com.zwiebelnx.mca_2.Biz.TestBiz;
import com.zwiebelnx.mca_2.View.DrawItemViewTest;



public class TestActivity extends AppCompatActivity {
    private static boolean isRedoExtend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Button SubmitBtn = findViewById(R.id.SubmitButton_Test);
        Button PlayBtn = findViewById(R.id.PlayButton_Test);
        FloatingActionButton BackBtn = findViewById(R.id.BackButton_Test);
        FloatingActionButton ShareBtn = findViewById(R.id.ShareButton_Test);
        FloatingActionButton RedoBtn = findViewById(R.id.RedoButtonTest);
        final DrawItemViewTest MainWin = findViewById(R.id.MainWinTest);
        isRedoExtend = false;


        /*
         产生初始化图形
         */
        MainWin.setDrawMode(1);
        List<MusicItem> Mlist = TestBiz.GenItemList();
        Mlist = TestBiz.getImg(Mlist, getResources());
        MainWin.setMlist(Mlist);
        MainWin.invalidate();

        /*提交测试按钮监听器声明*/
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.setSlist(MainWin.getLlist(),MainWin.getMlist());
                List<Integer> Slist = MainWin.getSlist();
                String url = "/storage/emulated/0/MCA/Result.mid";
                if(Slist.size() != 7){
                    Toast.makeText(TestActivity.this,"要把七个全部连完喔", Toast.LENGTH_LONG).show();//当测试页面内七个图标没有连完时拒绝用户提交
                }
                else{
                    /*
                    生成连线的截图 测试结果的截图
                    以及连线的音频文件
                     */
                    try {
                        View dView = getWindow().getDecorView();
                        dView.setDrawingCacheEnabled(true);
                        dView.buildDrawingCache();
                        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
                        FileOutputStream fos1=new FileOutputStream("/storage/emulated/0/MCA/resultTest1.png");
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,fos1);
                        fos1.flush();
                        fos1.close();
                        MainWin.setTestResult(TestBiz.getTest(MainWin.getSlist(), getResources(), 0x4c));
                        MainWin.setDrawMode(3);
                        MainWin.invalidate();
                        FileOutputStream fos3 = new FileOutputStream(url);
                        byte[] data = MidiUtils.Generate(Slist, 0);
                        fos3.write(data);
                        fos3.flush();
                        fos3.close();
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(url);
                        player.prepare();
                        player.start();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    /*隐藏操作按钮*/
                    findViewById(R.id.ShareButton_Test).setVisibility(View.VISIBLE);
                    findViewById(R.id.Redo_Cencel_Test).setVisibility(View.GONE);
                    findViewById(R.id.Redo_ReCreate_Test).setVisibility(View.GONE);
                    findViewById(R.id.Redo_Reverst_Test).setVisibility(View.GONE);
                    findViewById(R.id.RedoButtonTest).setVisibility(View.GONE);
                    isRedoExtend = false;
                }
            }
        });
        /*提交按钮监听器声明结束*/

        /*播放按钮监听器声明*/
        PlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.setSlist(MainWin.getLlist(),MainWin.getMlist());
                List<Integer> Slist = MainWin.getSlist();
                String url = "/storage/emulated/0/MCA/Result.mid";
                if(Slist.isEmpty()){//没有连接元素时拒绝用户的播放操作
                    Toast.makeText(TestActivity.this,"请先至少链接一个元素~", Toast.LENGTH_LONG).show();
                }
                else{
                    /*
                    生成音频缓存文件
                    在这里音频个数没有限制 可以动态生成
                     */
                    try {
                        FileOutputStream fos = new FileOutputStream(url);
                        byte[] data = MidiUtils.Generate(Slist, 0);
                        fos.write(data);
                        fos.flush();
                        fos.close();
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(url);//播放当前连线的连续音频
                        player.prepare();
                        player.start();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        /*播放按钮监听器声明结束*/

        /*
        操作展开按钮监听器声明
        主要是动画方面
         */
        RedoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRedoExtend){
                    findViewById(R.id.Redo_Reverst_Test).startAnimation(AllAnim.showOut(0,200,AllAnim.DIRECTION_FROM_RIGHT));
                    findViewById(R.id.Redo_Cencel_Test).startAnimation(AllAnim.showOut(50,200,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.Redo_ReCreate_Test).setAnimation(AllAnim.showOut(100,200,AllAnim.DIRECTION_FROM_LEFT));
                    findViewById(R.id.Redo_Reverst_Test).setVisibility(View.GONE);
                    findViewById(R.id.Redo_Cencel_Test).setVisibility(View.GONE);
                    findViewById(R.id.Redo_ReCreate_Test).setVisibility(View.GONE);
                    isRedoExtend = false;
                }
                else {
                    findViewById(R.id.Redo_Reverst_Test).setVisibility(View.VISIBLE);
                    findViewById(R.id.Redo_Cencel_Test).setVisibility(View.VISIBLE);
                    findViewById(R.id.Redo_ReCreate_Test).setVisibility(View.VISIBLE);
                    findViewById(R.id.Redo_Reverst_Test).startAnimation(AllAnim.showUp(0,200,AllAnim.DIRECTION_FROM_RIGHT));
                    findViewById(R.id.Redo_Cencel_Test).startAnimation(AllAnim.showUp(50,200,AllAnim.DIRECTION_FROM_BUTTOM));
                    findViewById(R.id.Redo_ReCreate_Test).setAnimation(AllAnim.showUp(100,200,AllAnim.DIRECTION_FROM_LEFT));
                    isRedoExtend = true;
                }
            }
        });
        /*操作展开按钮监听器声明结束*/

        /*分享按钮监听器声明*/
        ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ResultTest1;
                Uri ResultTest2;
                Uri ResultMidi;
                View dView = getWindow().getDecorView();
                dView.setDrawingCacheEnabled(true);
                dView.destroyDrawingCache();
                dView.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
                try{
                    FileOutputStream fos2=new FileOutputStream("/storage/emulated/0/MCA/resultTest2.png");//获取测试结果截图
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,fos2);
                    fos2.flush();
                    fos2.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
                /*
                传递分享文件
                 */
                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                if(Build.VERSION.SDK_INT>=24){
                    ResultTest1 = FileProvider.getUriForFile(TestActivity.this,getPackageName()+".provider",new File("/storage/emulated/0/MCA/resultTest1.png"));
                    ResultTest2 = FileProvider.getUriForFile(TestActivity.this,getPackageName()+".provider",new File("/storage/emulated/0/MCA/resultTest2.png"));
                    ResultMidi = FileProvider.getUriForFile(TestActivity.this,getPackageName()+".provider",new File("/storage/emulated/0/MCA/Result.mid"));
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }else {
                    ResultTest1 = Uri.fromFile(new File("/storage/emulated/0/MCA/resultTest1.png"));
                    ResultTest2 = Uri.fromFile(new File("/storage/emulated/0/MCA/resultTest2.png"));
                    ResultMidi = Uri.fromFile(new File("/storage/emulated/0/MCA/Result.mid"));
                }
                ArrayList<Uri> uris=new ArrayList();
                uris.add(ResultTest1);
                uris.add(ResultTest2);
                uris.add(ResultMidi);
                intent.setType("*/*");
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                try {
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        /*分享按钮监听器声明结束*/

        /*撤销按钮监听器声明*/
        findViewById(R.id.Redo_Reverst_Test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.ReverstLlist();
            }
        });

        /*重新生成图形按钮监听器声明*/
        findViewById(R.id.Redo_ReCreate_Test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.ReCreateMlist();
                TestBiz.getImg(MainWin.getMlist(), getResources());//重新设置图像
                MainWin.setDrawMode(1);
                MainWin.invalidate();
            }
        });
        /*取消全部按钮按钮监听器声明*/
        findViewById(R.id.Redo_Cencel_Test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWin.CancelLlist();
            }
        });

        /*返回主页按钮监听器声明*/
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackToMain = new Intent("com.zwiebelnx.mca.BACKTOMAIN");
                startActivity(BackToMain);
            }
        });

        /*进入时的过渡动画*/
        RedoBtn.startAnimation(AllAnim.showUp(0,400,AllAnim.DIRECTION_FROM_BUTTOM));
        PlayBtn.startAnimation(AllAnim.showUp(100,400,AllAnim.DIRECTION_FROM_BUTTOM));
        SubmitBtn.startAnimation(AllAnim.showUp(200,400,AllAnim.DIRECTION_FROM_BUTTOM));
        BackBtn.startAnimation(AllAnim.showUp(100,500,AllAnim.DIRECTION_FROM_LEFT));

    }
}
