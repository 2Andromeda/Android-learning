package com.proto.proj9_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MyGraphicView(this), paramsG);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout lay1 = new LinearLayout(this);
        lay1.setOrientation(LinearLayout.VERTICAL);
        setContentView(lay1, params);

        LinearLayout.LayoutParams btnparams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btn1 = new Button(this);
        btn1.setText("그림판으로 넘어가기");
        lay1.addView(btn1, btnparams1);
        lay1.addView(new MyGraphicView(this), btnparams1);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), p9_1.class);
                startActivity(intent);
            }
        });
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint =new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawLine(10, 10, 300, 10, paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5);
            canvas.drawLine(10, 30, 300, 30, paint);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);

            paint.setStyle(Paint.Style.FILL);
            Rect rect1 = new Rect(10, 40, 10+100, 50+100);
            canvas.drawRect(rect1, paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect rect2 = new Rect(130, 50, 130+100, 50+100);
            canvas.drawRect(rect2, paint);

            RectF rect3 = new RectF(250, 50, 250+100, 50+100);
            canvas.drawRoundRect(rect3, 20, 20, paint);

            canvas.drawCircle(60, 220, 50, paint);

            paint.setStrokeWidth(5);
            Path path1 = new Path();
            path1.moveTo(10, 290);
            path1.lineTo(10 + 50, 290 + 50);
            path1.lineTo(10 + 100, 290 + 50);
            path1.lineTo(10 + 200, 290);
            canvas.drawPath(path1, paint);

            paint.setStrokeWidth(0);
            paint.setTextSize(30);
            canvas.drawText("안드로이드", 10, 390, paint);

            paint.setStrokeWidth(5);
            paint.setTextSize(30);
            canvas.drawText("안드로이드", 10, 490, paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            Rect rect4 = new Rect(10, 550, 10+100, 550+20);
            canvas.drawRect(rect4, paint);

            RectF rect5 = new RectF(10, 600, 10+100, 600+20);
            canvas.drawRoundRect(rect5, 20, 20, paint);

            RectF rect6 = new RectF(10, 650, 10+100, 650+20);
            canvas.drawOval(rect6, paint);

            float startAngle = 70f, sweepAngle = 90f;

            RectF arc = new RectF(130, 650, 130+50, 650+50);
            canvas.drawArc(arc, startAngle, sweepAngle, true, paint);

            paint.setColor(Color.argb(255, 255, 0, 0 ));
            Rect rect7 = new Rect(250, 700, 250+200, 700+200);
            canvas.drawRect(rect7, paint);

            paint.setColor(Color.argb(150, 0, 0, 255));
            Rect rect8 = new Rect(200, 650, 200+200, 650+200);
            canvas.drawRect(rect8, paint);
        }

    }

}