package com.zwiebelnx.mca_2.Biz;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zwiebelnx.mca_2.Bean.Line;
import com.zwiebelnx.mca_2.Bean.MusicItem;
import com.zwiebelnx.mca_2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chen Sicong on 2018/5/16.\
 *
 */

public class CreateBiz {

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

        imgMap.put(111, R.drawable.bar_y_1);
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
                }
                default: return false;
            }
        }
        else{
            return true;
        }
    }
}
