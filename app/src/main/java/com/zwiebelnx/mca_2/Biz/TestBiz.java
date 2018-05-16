package com.zwiebelnx.mca_2.Biz;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.zwiebelnx.mca_2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zwiebelnx.mca_2.Bean.Line;
import com.zwiebelnx.mca_2.Bean.MusicItem;

public class TestBiz {

    /*
     * 产生数量为7的图形链表
     */
    public static List<MusicItem> GenItemList(){
        int x=250, y=600;
        List<MusicItem> Mlist = new ArrayList<>();
        MusicItem musicItem;
        for(int i=0; i<7; i++){
            musicItem = new MusicItem();
            Mlist.add(musicItem);
        }
        for(int i=0; i<Mlist.size();i++){   //  图形不相互覆盖 这里采取的是简单平铺的方法
            if(i<4){
                Mlist.get(i).setX(Mlist.get(i).getX()+x*i);
            }
            else{
                Mlist.get(i).setX(Mlist.get(i).getX()+x*(i-4));
                Mlist.get(i).setY(Mlist.get(i).getY()+y);
            }
        }
        return Mlist;
    }

    /**
     * TestBiz.getImg:
     * 根据编号获取图形图片
     * ABC格式 A=形状 B=颜色 C=亮度
     * A 1=bar 2=rec 3=cir
     * B 1=黄 2=绿 3=蓝
     * C 1=深 2=中 3=浅
     */
    public static List<MusicItem> getImg(List<MusicItem> Mlist, Resources res){

        Integer index;
        HashMap<Integer, Integer> imgMap = new HashMap<>();
        Bitmap bitmap;
        int id;

        imgMap.put(111,R.drawable.bar_y_1);
        imgMap.put(112,R.drawable.bar_y_2);
        imgMap.put(113,R.drawable.bar_y_3);
        imgMap.put(121,R.drawable.bar_g_1);
        imgMap.put(122,R.drawable.bar_g_2);
        imgMap.put(123,R.drawable.bar_g_3);

        imgMap.put(211,R.drawable.rec_y_1);
        imgMap.put(212,R.drawable.rec_y_2);
        imgMap.put(213,R.drawable.rec_y_3);
        imgMap.put(221,R.drawable.rec_g_1);
        imgMap.put(222,R.drawable.rec_g_2);
        imgMap.put(223,R.drawable.rec_g_3);

        imgMap.put(311,R.drawable.circle_y_1);
        imgMap.put(312,R.drawable.circle_y_2);
        imgMap.put(313,R.drawable.circle_y_3);
        imgMap.put(321,R.drawable.circle_g_1);
        imgMap.put(322,R.drawable.circle_g_2);
        imgMap.put(323,R.drawable.circle_g_3);
        imgMap.put(331,R.drawable.circle_b_1);
        imgMap.put(332,R.drawable.circle_b_2);
        imgMap.put(333,R.drawable.circle_b_3);

        for(int i=0; i<Mlist.size(); i++){
            index = Mlist.get(i).getShapeIndex()*100
                    +Mlist.get(i).getColorIndex()*10
                    +Mlist.get(i).getBrightIndex();
            id = imgMap.get(index);
            bitmap = BitmapFactory.decodeResource(res, id);
            Mlist.get(i).setImg(bitmap);
        }
        return Mlist;
    }

    /*
     判断选中的结点是否可以被连接
     */
    public static boolean isAvilable(String Mode, int index, List<Line> Llist){
        if(!Llist.isEmpty()){
            switch (Mode){
                case "StartIndex":{ //是否可以作为开始结点
                    for(Line l:Llist){
                        if(index == l.getStartItemIndex()){
                            return false;
                        }
                    }
                    return true;
                }
                case "EndIndex":{ //是否可以作为结束结点
                    for(Line l:Llist){
                        if(index == l.getEndItemIndex()){
                            return false;
                        }
                    }
                    return true;
                }
                case "Size":{
                    return (Llist.size() != 6);
                }
                case "Loop":{
                    for(Line l:Llist){ //不允许成环
                        if(index == l.getStartItemIndex()){
                            return false;
                        }
                    }
                    return true;
                }
                default: return false;
            }
        }
        else{
            return true;
        }
    }

    /*
    分析算法
     */
    public static String getTest(List<Integer> pitchs,Resources r,int MID_PITCH) {
        int id=0;
        int break0=(int)Math.round(pitchs.size()*0.35);
        int break1=(int)Math.round(pitchs.size()*0.65);
        int count_l_f=0,count_l_m=0,count_l_b=0;
        int count_h_f=0,count_h_m=0,count_h_b=0;
        for(int i=0;i<break0;i++) {
            if(pitchs.get(i)<MID_PITCH) {
                count_l_f++;
            }else {
                count_h_f++;
            }
        }
        for(int i=break0;i<break1;i++) {
            if(pitchs.get(i)<MID_PITCH) {
                count_l_m++;
            }else {
                count_h_m++;
            }
        }
        for(int i=break1;i<pitchs.size();i++) {
            if(pitchs.get(i)<MID_PITCH) {
                count_l_b++;
            }else {
                count_h_b++;
            }
        }
        if(count_l_f>=count_l_m+count_l_b) {
            id=0;
        }else if(count_l_m>=count_l_f+count_l_b) {
            id=1;
        }else {
            id=2;
        }
        if(count_h_f>=count_h_m+count_h_b) {
            id=0;
        }else if(count_h_m>=count_h_f+count_h_b) {
            id+=3;
        }else {
            id+=6;
        }
        return r.getStringArray(R.array.Ana)[id];
    }

}
