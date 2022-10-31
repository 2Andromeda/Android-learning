package com.proto.proj10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ex10_15 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex10_15);
        setTitle("ex10_15");

        Intent inIntent = getIntent();
        final int hapValue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);
        final int minValue = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0);
        final int mulValue = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0);
        final int divValue = inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 1);

        final int cal = inIntent.getIntExtra("cal", 1);

        Button btnReturn = (Button) findViewById(R.id.btnBack);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                switch (cal){
                    case 1:
                        outIntent.putExtra("Res", hapValue);
                        break;
                    case 2:
                        outIntent.putExtra("Res", minValue);
                        break;
                    case 3:
                        outIntent.putExtra("Res", mulValue);
                        break;
                    case 4:
                        outIntent.putExtra("Res", divValue);
                        break;
                }

                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

    }
}
