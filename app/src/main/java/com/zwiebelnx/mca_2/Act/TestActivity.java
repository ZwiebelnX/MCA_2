package com.zwiebelnx.mca_2.Act;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zwiebelnx.mca_2.R;

import FabricView.FabricView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        Button SubmitBtn = findViewById(R.id.SubmitButton_Test);
        FloatingActionButton BackBtn = findViewById(R.id.BackButton_Test);
        FloatingActionButton ShareBtn = findViewById(R.id.ShareButton_Test);
        MyView MainWin = findViewById(R.id.MainWin);

        Context con = ((LinearLayout)findViewById(R.id.MainWinBox)).getContext();

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackToMain = new Intent("com.zwiebelnx.mca.BACKTOMAIN");
                startActivity(BackToMain);
            }
        });

    }

    public class MyView extends View {
        private Paint paint;

        MyView(Context context, AttributeSet attrs){
            super(context,attrs);
            paint = new Paint();
            paint.setColor(Color.RED);
        }
        MyView(Context context){
            super(context);
            paint = new Paint();
            paint.setColor(Color.RED);
        }
        MyView(Context context,AttributeSet attrs,int defStyle){
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


}
