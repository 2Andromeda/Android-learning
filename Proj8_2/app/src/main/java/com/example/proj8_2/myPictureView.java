package com.example.proj8_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

public class myPictureView extends View {
    String imagePath = null; // 파일 이름 및 경로 변수
    public myPictureView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    myPictureView myPic1;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        myPic1 = (myPictureView) findViewById(R.id.myPictureView1);
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            canvas.drawBitmap(bitmap, (myPic1.getWidth()-bitmap.getWidth())/2, 0, null);
            bitmap.recycle();
        }
    }
}
