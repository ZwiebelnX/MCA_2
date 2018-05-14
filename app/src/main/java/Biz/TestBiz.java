package Biz;

import android.widget.ImageView;

import com.zwiebelnx.mca_2.R;

import java.util.ArrayList;
import java.util.List;

import Bean.MusicItem;

public class TestBiz {

    public List<MusicItem> GenItemList(){
        List<MusicItem> Mlist = new ArrayList<>();
        MusicItem musicItem;
        for(int i=0; i<7; i++){
            musicItem = new MusicItem();
            Mlist.add(musicItem);
        }
        return Mlist;
    }


}
