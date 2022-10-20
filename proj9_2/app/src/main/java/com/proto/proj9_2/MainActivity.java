package com.proto.proj9_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout lay1 = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lay1.setOrientation(LinearLayout.VERTICAL);

        setContentView(lay1, params);
        Button btn = new Button(this);
        Button btn2 = new Button(this);

        btn.setText("실습 9-2");
        btn2.setText("연습 9-5");
        lay1.addView(btn, params);
        lay1.addView(btn2, params);
        lay1.addView(new MyGraphicView(this), params);
//        setContentView(new MyGraphicView(this));

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), p9_2.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), prac9_5.class);
                startActivity(intent);
            }
        });
    }

    private static class MyGraphicView extends View {
        public  MyGraphicView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.jeju14);
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, null);

            canvas.translate(-150, 200);
            canvas.drawBitmap(picture, picX, picY, null);

            canvas.rotate(45, cenX, cenY);
            canvas.drawBitmap(picture, picX, picY, null);

            Paint paint = new Paint();
            BlurMaskFilter bMask;

            bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
            paint.setMaskFilter(bMask);
            canvas.drawBitmap(picture, picX, picY, paint);

            Paint paint2 = new Paint();
            EmbossMaskFilter eMask;
            eMask = new EmbossMaskFilter(new float[] {3,10,3}, 0.5f, 5, 10); //blurRadius가 볼록하게 표현
            paint2.setColor(Color.GRAY);
            paint2.setMaskFilter(eMask);
            canvas.rotate(-45, cenX, cenY);
            canvas.drawCircle(cenX, cenY, 150, paint2);

            picture.recycle();

            Bitmap picture2 = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
            int picX2 = (this.getWidth() - picture2.getWidth()) / 2;
            int picY2 = (this.getHeight() - picture2.getHeight()) / 2;
            canvas.drawBitmap(picture2, picX2, picY2, null);

            canvas.translate(0, -picture2.getHeight());

            Paint paint3 = new Paint();
            float[] array = {1, 0, 0, 0, -25,
                            0, 1, 0, 0, -25,
                            0, 0, 1, 0, -25,
                            0, 0, 0, 1, 0};
            ColorMatrix cm = new ColorMatrix(array);
            paint3.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(picture2, picX2, picY2, paint3);
            picture2.recycle();
        }
    }
}