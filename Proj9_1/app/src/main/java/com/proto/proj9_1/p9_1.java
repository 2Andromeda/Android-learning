package com.proto.proj9_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class p9_1 extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, SQUARE = 3;
    final static int REDc = 1, BLUEc = 2, GREENc =3;
    static int curShape = LINE;
    static int coLor = REDc;
    static List<MyShape> myshape = new ArrayList<MyShape>();

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("간단 그림판");
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY =-1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context){
            super(context);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    MyShape saveshape = new MyShape();
                    saveshape.startX = startX; saveshape.startY = startY; saveshape.stopX = stopX; saveshape.stopY = stopY;
                    saveshape.color_my = coLor; saveshape.shapeTye = curShape;

                    myshape.add(saveshape);

                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            if(myshape.size() != 0) {
                for (MyShape savedShape : myshape) {

                    draw(savedShape.startX, savedShape.startY, savedShape.stopX, savedShape.stopY, paint, canvas, savedShape.color_my, savedShape.shapeTye);
                }
            }

            draw(startX, startY, stopX, stopY, paint, canvas, coLor, curShape);
        }

        protected void draw(int startX, int startY, int stopX, int stopY, Paint paint, Canvas canvas, int color, int curshape){
            switch (color){
                case REDc:
                    paint.setColor(Color.RED);
                    break;
                case BLUEc:
                    paint.setColor(Color.BLUE);
                    break;
                case GREENc:
                    paint.setColor(Color.GREEN);
                    break;
            }

            switch (curshape){
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX -startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case SQUARE:
                    canvas.drawRect(startX, startY, stopX, stopY, paint);
                    break;
            }
        }

    }

    private static class MyShape{
        int shapeTye;
        int startX, startY, stopX, stopY;
        int color_my;

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0 ,3, 0, "사각형 그리기");
        SubMenu subMenu = menu.addSubMenu("색상 변경");
        subMenu.add(1, 4, 0, "빨간색");
        subMenu.add(1, 5, 0, "파란색");
        subMenu.add(1, 6, 0, "초록색");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                curShape = LINE;
                return true;
            case 2:
                curShape = CIRCLE;
                return true;
            case 3:
                curShape = SQUARE;
                return true;

            case 4:
                coLor = REDc;
                return true;
            case 5:
                coLor = BLUEc;
                return true;
            case 6:
                coLor = GREENc;
                return true;
        }
        return false;
    }
}
