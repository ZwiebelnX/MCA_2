package com.zwiebelnx.mca_2.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.zwiebelnx.mca_2.Bean.Line;
import com.zwiebelnx.mca_2.Bean.MusicItem;
import com.zwiebelnx.mca_2.Biz.CreateBiz;
import com.zwiebelnx.mca_2.Biz.TestBiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Sicong on 2018/5/16.
 *
 */

public class DrawItemViewCreate extends View {
    private Paint paint;

    private List<MusicItem> Mlist = new ArrayList<>();
    private List<Integer> Slist = new ArrayList<>();
    private List<Line> Llist = new ArrayList<>();
    private static int DrawMode;//1=放置物品 2=画线
    private String TestResult;

    private static final int IMG_RANGE = 100;

    /*
    实现父类的三个构造器 在xml解析中使用
    初始化paint的属性
    */
    public DrawItemViewCreate(Context context) {
        super(context);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public DrawItemViewCreate(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public DrawItemViewCreate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    /* END */

    /*
    Getter And Setter
    */
    public void setMlist(List<MusicItem> Mlist) {
        this.Mlist = Mlist;
    }

    public void setTestResult(String testResult) {
        TestResult = testResult;
    }

    public static void setDrawMode(int drawMode) {
        DrawMode = drawMode;
    }

    public List<MusicItem> getMlist() {
        return Mlist;
    }

    public List<Integer> getSlist() {
        return Slist;
    }

    public List<Line> getLlist() {
        return Llist;
    }

    public String getTestResult() {
        return TestResult;
    }

    public static int getDrawMode() {
        return DrawMode;
    }

    /* END */

    @Override
    protected void onDraw(Canvas canvas) {//绘画模式
        super.onDraw(canvas);
        switch (DrawMode) {
            /*
             初始化界面和布置随机图形
             在TestActivity的OnCreate ResetBtn的OnClick中调用
             */
            case 1: {
                if (!Llist.isEmpty()) {
                    for (Line l : Llist) {
                        canvas.drawLine(Mlist.get(l.getStartItemIndex()).getcX(), Mlist.get(l.getStartItemIndex()).getcY(),
                                Mlist.get(l.getEndItemIndex()).getcX(), Mlist.get(l.getEndItemIndex()).getcY(), paint);
                    }
                }
                for (int i = 0; i < Mlist.size(); i++) {
                    canvas.drawBitmap(Mlist.get(i).getImg(), ((float) Mlist.get(i).getX()), ((float) Mlist.get(i).getY()), paint);
                }
            }
            break;

            /*
             绘制图形和连线
             在类内的TouchEvent和RedoBtn的OnClick中调用
             */
            case 2: {
                canvas.drawLine(DownX, DownY, UpX, UpY, paint);
                if (!Llist.isEmpty()) {
                    for (Line l : Llist) {
                        canvas.drawLine(Mlist.get(l.getStartItemIndex()).getcX(), Mlist.get(l.getStartItemIndex()).getcY(),
                                Mlist.get(l.getEndItemIndex()).getcX(), Mlist.get(l.getEndItemIndex()).getcY(), paint);
                    }
                }
                for (int i = 0; i < Mlist.size(); i++) {
                    canvas.drawBitmap(Mlist.get(i).getImg(), ((float) Mlist.get(i).getX()), ((float) Mlist.get(i).getY()), paint);
                }
            }
            break;

            default:
                break;
        }

    }

    private static boolean OnItem = false;
    private int StartItemIndex = -1;
    private int EndItemIndex = -1;
    private static boolean isLongpress = false;
    private float DownX, DownY;
    private float UpX, UpY;

    public int OnItem(float X, float Y) {
        for (int i = 0; i < Mlist.size(); i++) {
            float imgX = (float) Mlist.get(i).getcX();
            float imgY = (float) Mlist.get(i).getcY();
            double range = Math.sqrt((X - imgX) * (X - imgX) + (Y - imgY) * (Y - imgY));
            if (range <= IMG_RANGE) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> SetSlist(List<Line> Llist, List<MusicItem> Mlist) {
        if(!Mlist.isEmpty()){
            Slist.add(Mlist.get(Llist.get(0).getStartItemIndex()).getSound());
            Slist.add(Mlist.get(Llist.get(0).getEndItemIndex()).getSound());
            for (int i = 1; i < Llist.size(); i++) {
                Slist.add(Mlist.get(Llist.get(i).getEndItemIndex()).getSound());
            }
        }
        return Slist;
    }

    /**
     * DrawItemView的触摸事件：
     * 处理用户按下、移动、抬起时 画布上连线以及各种限制
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        long DownTime = 0;
        long MoveTime = 0;
        MediaPlayer player = new MediaPlayer();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                DownX = event.getX();
                DownY = event.getY();
                StartItemIndex = OnItem(DownX, DownY);
                if (CreateBiz.isAvilable("StartIndex", StartItemIndex, Llist)) {
                    if (!Llist.isEmpty()) {
                        if (Llist.get(Llist.size() - 1).getEndItemIndex() != StartItemIndex) {//限制 必须按顺序连接
                            Toast.makeText(super.getContext(), "请按顺序连接~", Toast.LENGTH_SHORT).show();
                            DownX = DownY = UpX = UpY = 0;
                            DrawMode = 2;
                            invalidate();
                            OnItem = false;
                            return true;
                        }
                    }
                    if(StartItemIndex != -1){
                        OnItem = true;
                        DownTime = System.currentTimeMillis();
                        try {
                            player.setDataSource(Mlist.get(StartItemIndex).getMusicFlieUrl());
                            player.prepare();
                            player.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        OnItem = false;
                    }

                    return true;
                }
                else{//限制 一个结点只能作为起点和终点各一次
                    DownX=DownY=UpX=UpY=0;
                    Toast.makeText(super.getContext(),"该结点已被链接 请重新选择~", Toast.LENGTH_SHORT).show();
                    DrawMode = 2;
                    invalidate();
                }
            }
            case MotionEvent.ACTION_MOVE:{//移动操作 连线跟随用户的手
                if(OnItem){
                    DownX = Mlist.get(StartItemIndex).getcX();
                    DownY = Mlist.get(StartItemIndex).getcY();
                    UpX = event.getX();
                    UpY = event.getY();
                    MoveTime = System.currentTimeMillis();
                    if(Math.abs(DownX-UpX) <=70 && Math.abs(DownY-UpY) <=70&& !isLongpress){
                        if(Math.abs(DownTime-MoveTime) > 500){
                            Toast.makeText(super.getContext(), "移动模式", Toast.LENGTH_SHORT).show();
                            isLongpress = true;
                        }
                        else{
                            isLongpress = false;
                        }
                    }
                    if(isLongpress){
                        Mlist.get(StartItemIndex).MoveXY((int)UpX, (int)UpY);
                        DrawMode = 1;
                        invalidate();
                    }
                    else{
                        DrawMode = 2;
                        invalidate();
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_UP:{
                if(isLongpress){
                    isLongpress = false;
                    return true;
                }
                else{
                    if(OnItem){
                        UpX = event.getX();
                        UpY = event.getY();
                        EndItemIndex = OnItem(UpX,UpY);
                        if(StartItemIndex == -1|| EndItemIndex == -1 || StartItemIndex==EndItemIndex){//起点 终点不是图形或者起点等于终点时不允许连线
                            OnItem = false;
                            DownX=DownY=UpX=UpY=0;
                            invalidate();
                        }
                        else{
                            if(TestBiz.isAvilable("EndIndex", EndItemIndex, Llist)){//连接两个节点 将连线加入连线列表
                                if(TestBiz.isAvilable("Loop", EndItemIndex, Llist)){
                                    UpX = Mlist.get(EndItemIndex).getcX();
                                    UpY = Mlist.get(EndItemIndex).getcY();
                                    OnItem = false;
                                    Llist.add(new Line(StartItemIndex,EndItemIndex));
                                    DrawMode = 2;
                                    invalidate();
                                }
                                else{
                                    DownX=DownY=UpX=UpY=0;
                                    Toast.makeText(super.getContext(),"不可以成环喔~", Toast.LENGTH_SHORT).show();
                                    DrawMode = 2;
                                    invalidate();
                                }

                            }
                            else{
                                DownX=DownY=UpX=UpY=0;
                                Toast.makeText(super.getContext(),"该结点已经被连接 请重新选择~", Toast.LENGTH_SHORT).show();
                                DrawMode = 2;
                                invalidate();
                            }
                        }
                        return true;
                    }
                    else{
                        DownX=DownY=UpX=UpY=0;
                        DrawMode = 2;
                        invalidate();
                    }
                }
            }
        }
        return true;
    }

}