package Biz;

import android.widget.ImageView;

import com.zwiebelnx.mca_2.R;

import java.util.ArrayList;
import java.util.List;

import Bean.MusicItem;

public class TestBiz {

    public List<ImageView> GenItemImageView(){
        List<MusicItem> Mlist = new ArrayList<>();
        List<ImageView> out = new ArrayList<>();
        MusicItem musicItem;
        ImageView img;
        for(int i=0; i<7; i++){
            musicItem = new MusicItem();
            Mlist.add(musicItem);
        }
        for(int i = 0;i<Mlist.size();i++){
            switch (Mlist.get(i).getShapeIndex()){
                case 1:img = new ImageView(null);
                    img.setImageResource(R.drawable.bar);
                    out.add(img);
                    break;
                case 2:img = new ImageView(null);
                    img.setImageResource(R.drawable.blank);
                    out.add(img);
                    break;
                case 3:img = new ImageView(null);
                    img.setImageResource(R.drawable.circle);
                    out.add(img);
                    break;
            }
        }
        return out;
    }
}
