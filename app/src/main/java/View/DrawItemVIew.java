package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import Bean.MusicItem;

public class DrawItemVIew extends View {
    List<MusicItem> Mlist;
    private Paint paint;

    DrawItemVIew(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }
    DrawItemVIew(Context context){
        super(context);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }
    DrawItemVIew(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void setMlist(List<MusicItem> Mlist){
        this.Mlist = Mlist;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i<Mlist.size(); i++){
            canvas.drawBitmap(Mlist.get(i).getImg(),((float)Mlist.get(i).getX()),((float)Mlist.get(i).getY()),paint);
        }
    }
}