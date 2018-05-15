package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawItemVIew extends View {
    private Paint paint;

    DrawItemVIew(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
    }
    DrawItemVIew(Context context){
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
    }
    DrawItemVIew(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(150,150,40,paint);
    }
}