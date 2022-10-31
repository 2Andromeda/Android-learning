package com.proto.proj10_3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        int res = intent.getIntExtra("Res", 0);
                        Toast.makeText(getApplicationContext(), "결과 :" + res, Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");


        Button btnNewActivity = (Button) findViewById(R.id.btnPlus);
        RadioGroup calMethod = (RadioGroup) findViewById(R.id.rGroup);

        Button btnDial = (Button) findViewById(R.id.btnDial);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnGoogle = (Button) findViewById(R.id.btnGoogle);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnSms = (Button) findViewById(R.id.btnSms);
        Button btnPhoto = (Button) findViewById(R.id.btnPhoto);

        btnNewActivity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText edt1 = (EditText) findViewById(R.id.edt1);
                EditText edt2 = (EditText) findViewById(R.id.edt2);
                int cal = 0;
                switch (calMethod.getCheckedRadioButtonId()){
                    case R.id.plus:
                        cal = 1;
                        break;
                    case R.id.minus:
                        cal = 2;
                        break;
                    case R.id.multiply:
                        cal = 3;
                        break;
                    case R.id.divide:
                        cal =4;
                        break;
                }

                if(edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "숫자를 입력해 주세요", Toast.LENGTH_SHORT).show();
                }else if(cal == 0) {
                    Toast.makeText(getApplicationContext(), "계산 방법을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), ex10_15.class);
                    intent.putExtra("Num1", Integer.parseInt(edt1.getText().toString()));
                    intent.putExtra("Num2", Integer.parseInt(edt2.getText().toString()));
                    intent.putExtra("cal", cal);
                    resultLauncher.launch(intent);
                }
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:01094133476");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });


    }

}