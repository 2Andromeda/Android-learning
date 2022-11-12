package com.proto.prof11_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class lec11_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lec11_1);
        setTitle("리스트뷰 테스트");

        Button btnBack = (Button) findViewById(R.id.btnBack1);

        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        //final String[] mid = {"히어로즈", "24시", "로스트", "스몰빌"};

        final ArrayList<String> mid = new ArrayList<>(Arrays.asList("히어로즈", "24시", "로스트", "가쉽걸", "아내의 유혹"));

        ListView list = (ListView) findViewById(R.id.listView1); // XML 에 대응

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mid);
        //2번째 파라미터 = 리스트 뷰의 형식, 3번째 파라미터 = 적용할 배열(mid)
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setAdapter(adapter);

        final EditText edtItem = (EditText) findViewById(R.id.edtItem);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mid.add(edtItem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                //onItemClick(추상 메소드) 의 파라미터 -> (어댑터뷰, 뷰, 클릭한 항목의 순번, 항목의 아이디)
                Toast.makeText(getApplicationContext(), mid.get(arg2), Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // 길게 눌러서 지우는 섹션
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mid.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}
