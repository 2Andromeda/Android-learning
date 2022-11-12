package com.proto.prof11_1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class proj11_1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj11_1);
        setTitle("그리드뷰 테스트/실습");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdaper = new MyGridAdapter(this);
        gv.setAdapter(gAdaper);


    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        Integer[] posterID = new Integer[31];
        String[] posterName = {"토이스토리", "호빗", "제이슨 본", "반지의 제왕",
            "정직한 후보", "나쁜 녀석들", "겨울왕국2", "알라딘", "극한직업",
            "스파이더맨 파프롬홈", "레옹", "랄프2", "타짜", "걸캅스", "도굴", "어벤져스",
            "엑시트", "캡틴마블", "봉오동 전투", "분노의 질주", "아바타", "미스터리",
            "포드페라리", "쥬만지", "대부", "국가대표", "토이스토리", "마당암탉",
            "죽은 시인의 사회", "서유쌍기", "킹콩"};


        @Override
        public View getView(int position, View convertView, ViewGroup parent) { // 포스터의 개수 만큼 반복되어 각 그리드 뷰마다 보여주는 기능
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new ViewGroup.LayoutParams(200,300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            for(int i = 0; i < 31; i++){
                int k = i + 1;
                String j;
                if(k < 10){
                    j = "0" + Integer.toString(k);
                }else{
                    j = Integer.toString(k);
                }

                posterID[i] = getResources().getIdentifier("mov" + j, "drawable", getPackageName());
            }

            imageview.setImageResource(posterID[position]);

            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    View dialogView = (View) View.inflate(proj11_1.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(proj11_1.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.movie_icon);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return imageview;
        }
    }
}
