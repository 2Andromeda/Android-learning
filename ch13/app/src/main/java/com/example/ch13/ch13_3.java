package com.example.ch13;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ch13_3 extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    MapFragment mapFrag;
    GroundOverlayOptions videoMark;
//    ArrayList<Double>[] cctvList = new ArrayList[2];
    ArrayList<GroundOverlay> cctvList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch13_3);
        setTitle("구글 지도");

//        for(int i=0; i<2; i++){
//            cctvList[i] = new ArrayList<Double>();
//        }

        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map); //xml의 fragment를 대응시킴
        mapFrag.getMapAsync(this); //

    }

    @Override
    public void onMapReady(GoogleMap map){
        gMap = map;
        gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);//지도 보이는 타입(위성 지도)
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240), 15));

        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point){
                videoMark = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.presence_video_busy))
                        .position(point, 100f, 100f);
                cctvList.add(gMap.addGroundOverlay(videoMark));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도");
        menu.add(0, 2, 0, "일반 지도");
        menu.add(0, 3, 0, "월드컵 경기장 보러가기");
        menu.add(0, 4, 0, "바로 전 CCTV 지우기");
        menu.add(0, 5, 0, "모든 cctv 지우기");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case 2:
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240), 15));
                return true;
            case 4:
                cctvList.get(cctvList.size() -1).remove();
                cctvList.remove(cctvList.size()-1);
                return true;
            case 5:
                for(int i=0; i<cctvList.size(); i++){
                    cctvList.get(i).remove();
                }
                cctvList = new ArrayList();
                return true;
        }
        return false;

    }
}
