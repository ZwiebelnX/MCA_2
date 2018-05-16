package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Bean.Line;
import Bean.MusicItem;
import Biz.TestBiz;

public class DrawItemVIew extends View {
    List<MusicItem> Mlist;
    List<Integer> Slist;
    List<Line> Llist = new ArrayList<>();
    private Paint paint;
    public static int DrawMode;//1=放置物品 2=画线
    public static final int IMG_RANGE=100;
    private TestBiz testBiz = new TestBiz();

    static boolean  OnItem = false;
    int StartItemIndex = -1;
    int EndItemIndex = -1;
    float DownX,DownY;
    float UpX,UpY;
    String Warning ="";

    /*
     实现父类的三个构造器 在xml解析中使用
     初始化paint的属性
     */
    public DrawItemVIew(Context context){
        super(context);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    public DrawItemVIew(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    public DrawItemVIew(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {//绘画模式
        super.onDraw(canvas);
        switch (DrawMode){
            /*
             初始化界面和布置随机图形
             在TestActivity的OnCreate ResetBtn的OnClick中调用
             */
            case 1:{
                for(MusicItem m:Mlist){
                    canvas.drawBitmap(m.getImg(),m.getX(),m.getY(),paint);
                }
            }
            break;

            /*
             绘制图形和连线
             在类内的TouchEvent和RedoBtn的OnClick中调用
             */
            case 2:{
                canvas.drawLine(DownX, DownY, UpX, UpY, paint);
                if(!Llist.isEmpty()){
                    for(Line l:Llist){
                        canvas.drawLine(Mlist.get(l.getStartItemIndex()).getcX(), Mlist.get(l.getStartItemIndex()).getcY(),
                                Mlist.get(l.getEndItemIndex()).getcX(), Mlist.get(l.getEndItemIndex()).getcY(), paint);
                    }
                }
                for(int i = 0; i<Mlist.size(); i++){
                    canvas.drawBitmap(Mlist.get(i).getImg(),((float)Mlist.get(i).getX()),((float)Mlist.get(i).getY()),paint);
                }
                canvas.drawText(Warning,520,1000,paint);
            }
            break;
        }

    }

    public void setMlist(List<MusicItem> Mlist){
        this.Mlist = Mlist;
    }

    /*
     判断是否触摸在图形上
     */
    public int OnItem(float X, float Y){
        for(int i = 0 ; i<Mlist.size();i++){
            float imgX = (float)Mlist.get(i).getcX();
            float imgY = (float)Mlist.get(i).getcY();
            double range = Math.sqrt((X-imgX)*(X-imgX)+(Y-imgY)*(Y-imgY));
            if(range <= IMG_RANGE){
                return i;
            }
        }
        return -1;
    }

    /**
     * DrawItemView的触摸事件：
     * 处理用户按下、移动、抬起时 画布上连线以及各种限制
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{//按下操作
                DownX = event.getX();
                DownY = event.getY();
                StartItemIndex = OnItem(DownX, DownY);
                if(!testBiz.isAvilable("Size", StartItemIndex,Llist)){//若已经存在6条连接线 则拒绝用户操作
                    Warning = "全部结点已经被连接~";
                    DownX=DownY=UpX=UpY=0;
                    DrawMode = 2;
                    invalidate();
                }
                else if(testBiz.isAvilable("StartIndex",StartItemIndex,Llist)){
                    if(!Llist.isEmpty()){
                        if(Llist.get(Llist.size()-1).getEndItemIndex()!= StartItemIndex){//限制 必须按顺序连接
                            Warning = "请按顺序连接~";
                            DownX=DownY=UpX=UpY=0;
                            DrawMode = 2;
                            invalidate();
                        }
                        else{
                            Warning = "";
                            if(StartItemIndex == -1){
                                OnItem = false;
                            }
                            else{
                                OnItem = true;

                            }
                            return true;
                        }
                    }
                    else{
                        Warning = "";
                        if(StartItemIndex == -1){
                            OnItem = false;
                        }
                        else{
                            OnItem = true;

                        }
                        return true;
                    }
                }
                else{//限制 一个结点只能作为起点和终点各一次
                    DownX=DownY=UpX=UpY=0;
                    Warning = "该结点已经被连接，请重新选择~";
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
                    DrawMode = 2;
                    invalidate();
                }
                return true;
            }

            case MotionEvent.ACTION_UP:{//抬起操作
                if(OnItem){
                    EndItemIndex = OnItem(UpX,UpY);
                    if(StartItemIndex == -1|| EndItemIndex == -1 || StartItemIndex==EndItemIndex){//起点 终点不是图形或者起点等于终点时不允许连线
                        OnItem = false;
                        DownX=DownY=UpX=UpY=0;
                        invalidate();
                    }
                    else{
                        if(testBiz.isAvilable("EndIndex", EndItemIndex, Llist)){//连接两个节点 将连线加入连线列表
                            Warning = "";
                            UpX = Mlist.get(EndItemIndex).getcX();
                            UpY = Mlist.get(EndItemIndex).getcY();
                            OnItem = false;
                            Line l = new Line(StartItemIndex, EndItemIndex);
                            Llist.add(l);
                            DrawMode = 2;
                            invalidate();
                        }
                        else{
                            DownX=DownY=UpX=UpY=0;
                            Warning = "该结点已经被连接，请重新选择！";
                            DrawMode = 2;
                            invalidate();
                        }
                    }
                    return true;
                }
            }
        }
        return true;
    }
}