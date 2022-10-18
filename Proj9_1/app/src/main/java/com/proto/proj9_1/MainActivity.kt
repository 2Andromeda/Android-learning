package com.proto.proj9_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.view.ViewGroup
import android.content.Intent
import android.graphics.*
import com.proto.proj9_1.p9_1
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(new MyGraphicView(this), paramsG);
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val lay1 = LinearLayout(this)
        lay1.orientation = LinearLayout.VERTICAL
        setContentView(lay1, params)
        val btnparams1 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val btn1 = Button(this)
        btn1.text = "그림판으로 넘어가기"
        lay1.addView(btn1, btnparams1)
        lay1.addView(MyGraphicView(this), btnparams1)
        btn1.setOnClickListener {
            val intent = Intent(applicationContext, p9_1::class.java)
            startActivity(intent)
        }
    }

    private class MyGraphicView(context: Context?) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val paint = Paint()
            paint.isAntiAlias = true
            paint.color = Color.GREEN
            canvas.drawLine(10f, 10f, 300f, 10f, paint)
            paint.color = Color.BLUE
            paint.strokeWidth = 5f
            canvas.drawLine(10f, 30f, 300f, 30f, paint)
            paint.color = Color.RED
            paint.strokeWidth = 0f
            paint.style = Paint.Style.FILL
            val rect1 = Rect(10, 40, 10 + 100, 50 + 100)
            canvas.drawRect(rect1, paint)
            paint.style = Paint.Style.STROKE
            val rect2 = Rect(130, 50, 130 + 100, 50 + 100)
            canvas.drawRect(rect2, paint)
            val rect3 = RectF(250F, 50F, 250F + 100F, 50F + 100F)
            canvas.drawRoundRect(rect3, 20f, 20f, paint)
            canvas.drawCircle(60f, 220f, 50f, paint)
            paint.strokeWidth = 5f
            val path1 = Path()
            path1.moveTo(10f, 290f)
            path1.lineTo((10 + 50).toFloat(), (290 + 50).toFloat())
            path1.lineTo((10 + 100).toFloat(), (290 + 50).toFloat())
            path1.lineTo((10 + 200).toFloat(), 290f)
            canvas.drawPath(path1, paint)
            paint.strokeWidth = 0f
            paint.textSize = 30f
            canvas.drawText("안드로이드", 10f, 390f, paint)
            paint.strokeWidth = 5f
            paint.textSize = 30f
            canvas.drawText("안드로이드", 10f, 490f, paint)
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL
            val rect4 = Rect(10, 550, 10 + 100, 550 + 20)
            canvas.drawRect(rect4, paint)
            val rect5 = RectF(10F, 600F, 10F + 100F, 600F + 20F)
            canvas.drawRoundRect(rect5, 20f, 20f, paint)
            val rect6 = RectF(10F, 650F, 10F + 100F, 650F + 20F)
            canvas.drawOval(rect6, paint)
            val startAngle = 70f
            val sweepAngle = 90f
            val arc = RectF(130F, 650F, 130F + 50F, 650F + 50F)
            canvas.drawArc(arc, startAngle, sweepAngle, true, paint)
            paint.color = Color.argb(255, 255, 0, 0)
            val rect7 = Rect(250, 700, 250 + 200, 700 + 200)
            canvas.drawRect(rect7, paint)
            paint.color = Color.argb(150, 0, 0, 255)
            val rect8 = Rect(200, 650, 200 + 200, 650 + 200)
            canvas.drawRect(rect8, paint)
        }
    }
}