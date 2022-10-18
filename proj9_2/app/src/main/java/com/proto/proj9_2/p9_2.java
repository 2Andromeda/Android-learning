package com.proto.proj9_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class p9_2 extends AppCompatActivity {

    ImageButton ibZoomin, ibZoomout, ibRotate, ibBright, ibDark, ibGray;
    MyGraphicView graphicView;
    static float scaleX=1, scaleY=1;
    static float angle = 0;
    static float color = 0;
    static float satur = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p9_2);
        setTitle("미니 포토샵");

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);

        clickIcons();
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);
            Paint paint = new Paint();
            float[] array = {1, 0, 0, 0, color,
                            0, 1, 0, 0, color,
                            0, 0, 1, 0, color,
                            0, 0, 0, 1, 0};
            ColorMatrix cm = new ColorMatrix(array);
            if(satur ==0) cm.setSaturation(satur);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();
        }
    }

    private void clickIcons(){
        ibZoomin = (ImageButton) findViewById(R.id.magnify);
        ibZoomin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();//onDraw를 자동 호출해주는 함수
            }
        });

        ibZoomout = (ImageButton) findViewById(R.id.reduce);
        ibZoomout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
            }
        });

        ibRotate = (ImageButton) findViewById(R.id.rotate);
        ibRotate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                angle = angle + 20;
                graphicView.invalidate();
            }
        });

        ibBright = (ImageButton) findViewById(R.id.bright);
        ibBright.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                color = color + 10.0f;
                graphicView.invalidate();
            }
        });

        ibDark = (ImageButton) findViewById(R.id.dark);
        ibDark.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                color = color - 10.0f;
                graphicView.invalidate();
            }
        });

        ibGray = (ImageButton) findViewById(R.id.gray);
        ibGray.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(satur == 0) satur=1;
                else satur = 0;
                graphicView.invalidate();
            }
        });
    }
}
