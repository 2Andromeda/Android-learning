package com.proto.prac6_7;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    ActionBar.Tab tabDog, tabCat, tabRabbit, tabHorse;

    MyTabFragment myFrags[] = new MyTabFragment[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabDog = bar.newTab();
        tabDog.setIcon(R.drawable.icon_dog);
        tabDog.setTag("강아지");
        tabDog.setTabListener(this);
        bar.addTab(tabDog);

        tabCat = bar.newTab();
        tabCat.setIcon(R.drawable.icon_cat);
        tabCat.setTag("고양이");
        tabCat.setTabListener(this);
        bar.addTab(tabCat);

        tabRabbit = bar.newTab();
        tabRabbit.setIcon(R.drawable.icon_rabbit);
        tabRabbit.setTag("토끼");
        tabRabbit.setTabListener(this);
        bar.addTab(tabRabbit);

        tabHorse = bar.newTab();
        tabHorse.setIcon(R.drawable.icon_horse);
        tabHorse.setTag("말");
        tabHorse.setTabListener(this);
        bar.addTab(tabHorse);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        MyTabFragment myTabFrag = null;

        if(myFrags[tab.getPosition()]==null){
            myTabFrag = new MyTabFragment();
            Bundle data = new Bundle();
            data.putString("tabName", tab.getTag().toString());
            myTabFrag.setArguments(data);
            myFrags[tab.getPosition()] = myTabFrag;
        }
        else
            myTabFrag = myFrags[tab.getPosition()];

        ft.replace(android.R.id.content, myTabFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class MyTabFragment extends androidx.fragment.app.Fragment{
        String tabName;

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

            View myView = inflater.inflate(R.layout.activity_main, null);
            ImageView image = (ImageView) myView.findViewById(R.id.image);

            if(tabName == "고양이") image.setImageResource(R.drawable.cat);
            if(tabName == "강아지") image.setImageResource(R.drawable.dog);
            if(tabName == "말") image.setImageResource(R.drawable.horse);
            if(tabName == "토끼") image.setImageResource(R.drawable.rabbit);

            return myView;

        }

    }
}