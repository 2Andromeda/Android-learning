package com.proto.proj11_2;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class proj11_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj11_2);
        setTitle("갤러리 연습");

        Gallery gallery = (Gallery) findViewById(R.id.gallery1);//폐기된 기능
        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);

    }

    public class MyGalleryAdapter extends BaseAdapter{
        Context context;

        public MyGalleryAdapter(Context c){
            context = c;
        }

        Integer[] posterID = new Integer[10];

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

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
//            ViewGroup.LayoutParams lp = imageview.getLayoutParams();
//            lp.width = 200;
//            lp.height = 300;
//            imageview.setLayoutParams(lp);
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            for(int m = 0; m < 10; m++){
                int k = m + 20;
                String j;
                j = Integer.toString(k);

                posterID[m] = getResources().getIdentifier("mov" + j, "drawable", getPackageName());
            }

            imageview.setImageResource(posterID[i]);

            final int pos = i;
            imageview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event){
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);
                    return false;
                }
            });

            return imageview;
        }
    }
}
