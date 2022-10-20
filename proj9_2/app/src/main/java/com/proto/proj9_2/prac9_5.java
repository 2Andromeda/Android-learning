package com.proto.proj9_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class prac9_5 extends AppCompatActivity {

    ImageButton ibZoomin, ibZoomout, ibRotate, ibBright, ibDark, ibGray;
    MyGraphicView graphicView;
    static float scaleX=1, scaleY=1;
    static float angle = 0;
    static float color = 0;
    static float satur = 1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTitle("미니 포토샵 2");

        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout1.setOrientation(LinearLayout.VERTICAL);

        setContentView(layout1, params);

        graphicView = (MyGraphicView) new MyGraphicView(this);
        layout1.addView(graphicView);

        registerForContextMenu(graphicView);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 1, 0, "확대");
        menu.add(0, 2, 0, "축소");
        menu.add(0, 3, 0, "회전");
        menu.add(0, 4, 0, "밝게");
        menu.add(0, 5, 0, "어둡게");
        menu.add(0, 6, 0, "그레이영상");

    }

    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 1:
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
                return true;
            case 2:
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
                return true;
            case 3:
                angle = angle + 20;
                graphicView.invalidate();
                return true;
            case 4:
                color = color + 10.0f;
                graphicView.invalidate();
                return true;
            case 5:
                color = color - 10.0f;
                graphicView.invalidate();
                return true;
            case 6:
                if(satur == 0) satur=1;
                else satur = 0;
                graphicView.invalidate();
                return true;
        }
        return false;
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
}
