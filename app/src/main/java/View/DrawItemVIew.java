package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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

    DrawItemVIew(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    DrawItemVIew(Context context){
        super(context);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    DrawItemVIew(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (DrawMode){
            case 1:{
                for(MusicItem m:Mlist){
                    canvas.drawBitmap(m.getImg(),m.getX(),m.getY(),paint);
                }
            }
            break;
            case 2:{
                for(int i = 0; i<Mlist.size(); i++){
                    canvas.drawBitmap(Mlist.get(i).getImg(),((float)Mlist.get(i).getX()),((float)Mlist.get(i).getY()),paint);
                }
                if(!Llist.isEmpty()){
                    for(Line l:Llist){
                        canvas.drawLine(Mlist.get(l.getStartItemIndex()).getcX(), Mlist.get(l.getStartItemIndex()).getcY(),
                                Mlist.get(l.getEndItemIndex()).getcX(), Mlist.get(l.getEndItemIndex()).getcY(), paint);
                    }
                }
                canvas.drawLine(DownX, DownY, UpX, UpY, paint);
                canvas.drawText(Warning,520,1000,paint);
            }
            break;
        }

    }

    public void setMlist(List<MusicItem> Mlist){
        this.Mlist = Mlist;
    }

    /*
     * 判断是否在图形上
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                Log.d("Debug","Down");
                DownX = event.getX();
                DownY = event.getY();
                StartItemIndex = OnItem(DownX, DownY);
                if(testBiz.isAvilable("StartIndex",StartItemIndex,Llist)){
                    Warning = "";
                    if(StartItemIndex == -1){
                        OnItem = false;
                    }
                    else{
                        OnItem = true;

                    }
                    return true;
                }
                else{
                    DownX=DownY=UpX=UpY=0;
                    Warning = "该结点已经被连接，请重新选择！";
                    DrawMode = 2;
                    invalidate();
                }
            }
            case MotionEvent.ACTION_MOVE:{
                Log.d("Debug","Move");
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
            case MotionEvent.ACTION_UP:{
                Log.d("Debug","Up");
                if(OnItem){
                    EndItemIndex = OnItem(UpX,UpY);
                    if(StartItemIndex == -1|| EndItemIndex == -1 || StartItemIndex==EndItemIndex){
                        OnItem = false;
                        DownX=DownY=UpX=UpY=0;
                        invalidate();
                    }
                    else{
                        if(testBiz.isAvilable("EndIndex", EndItemIndex, Llist)){
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