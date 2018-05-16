package com.zwiebelnx.mca_2.Bean;

import android.graphics.Bitmap;
import android.util.Log;

import com.zwiebelnx.mca_2.Biz.Midi.MidiUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 音乐图形属性类：
 * 包含音乐图形的形状、颜色、亮度
 * 在画布上的座标、图形中心的修正坐标
 * 图形的位图资源
 * 在DrawItemView中调用
 */
public class MusicItem{
    private static int index = 0;
    private int ShapeIndex;
    private int ColorIndex;
    private int BrightIndex;
    private int sound;
    private int X,Y; //图形坐标 在左上角
    private int cX, cY; //修正坐标
    private Bitmap img;
    private String MusicFlieUrl;

    public MusicItem() {
        Random rand = new Random();
        sound = rand.nextInt(21)+1+0x30;//生成音调1-21
        ShapeIndex = rand.nextInt(3)+1;//生成形状号码 1-3
        if(ShapeIndex != 3){
            ColorIndex = rand.nextInt(2)+1; //圆形只有两种颜色
        }
        else{
            ColorIndex = rand.nextInt(3)+1;
        }

        if(sound<=7){   //不同音调对应不同的颜色深浅
            BrightIndex = 1;
        }
        else if(sound<=16){
            BrightIndex = 2;
        }
        else{
            BrightIndex = 3;
        }
        X = rand.nextInt(100)+1;
        Y = rand.nextInt(100)+1;

        currectXY();
        MusicFlieUrl = GenerateFlieAndUrl();
        index++;
    }

    /*
    Getter And Setter
     */

    public int getShapeIndex() {
        return ShapeIndex;
    }

    public int getColorIndex() {
        return ColorIndex;
    }

    public int getSound() {
        return sound;
    }

    public int getBrightIndex() {
        return BrightIndex;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Bitmap getImg() {
        return img;
    }

    public static int getIndex() {
        return index;
    }

    public String getMusicFlieUrl() {
        return MusicFlieUrl;
    }

    public int getcX() {
        return cX;
    }

    public int getcY() {
        return cY;
    }

    public void setShapeIndex(int shapeIndex) {
        ShapeIndex = shapeIndex;
    }

    public void setColorIndex(int colorIndex) {
        ColorIndex = colorIndex;
    }

    public void setSound(int sound) {
        this.sound = sound;
        MusicFlieUrl = GenerateFlieAndUrl();
    }

    public void setBrightIndex(int brightIndex) {
        BrightIndex = brightIndex;
    }

    public void setX(int x) {
        X = x;
        currectXY();
    }

    public void setY(int y) {
        Y = y;
        currectXY();
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }


    /* END */

    /*
     * 图形修正坐标函数
     */
    private void currectXY(){
        if(getShapeIndex()==1){
            cX = X + 40;
            cY = Y + 150;
        }
        else{
            cX = X + 60;
            cY = Y + 60;
        }
    }

    /*
    产生单音节声音资源 为后面播放提供资源调用
     */
    private String GenerateFlieAndUrl(){
        List<Integer> pitch = new ArrayList<>();
        pitch.add(sound);
        String url = "/storage/emulated/0/MCA/Item"+index+"pitch.mid";
        try{
            FileOutputStream fos=new FileOutputStream(url);
            byte[] data= MidiUtils.Generate(pitch, 0);
            fos.write(data);
            fos.flush();
            fos.close();
        } catch(Exception e){
            e.printStackTrace();
            Log.d("Debug","Create .mid error!");
        }
        return url;
    }
}
