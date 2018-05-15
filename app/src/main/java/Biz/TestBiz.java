package Biz;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.zwiebelnx.mca_2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Bean.Line;
import Bean.MusicItem;

public class TestBiz {

    public List<MusicItem> GenItemList(){
        int x=250, y=600;
        List<MusicItem> Mlist = new ArrayList<>();
        MusicItem musicItem;
        for(int i=0; i<7; i++){
            musicItem = new MusicItem();
            Mlist.add(musicItem);
        }
        for(int i=0; i<Mlist.size();i++){   //  图形不相互覆盖
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

    public List<MusicItem> getImg(List<MusicItem> Mlist, Resources res){
        /*
         * ABC格式 A=形状 B=颜色 C=亮度
         * A 1=bar 2=rec 3=cir
         * B 1=黄 2=绿 3=蓝
         * C 1=深 2=中 3=浅
         */
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

    public boolean isAvilable(String Mode, int index, List<Line> Llist){
        if(!Llist.isEmpty()){
            switch (Mode){
                case "StartIndex":{
                    for(Line l:Llist){
                        if(index == l.getStartItemIndex()){
                            return false;
                        }
                    }
                    return true;
                }
                case "EndIndex":{
                    for(Line l:Llist){
                        if(index == l.getEndItemIndex()){
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
}
