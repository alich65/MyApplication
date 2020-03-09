package com.test.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMap();

        addBaseMap();
    }

    private void addBaseMap(){
        OnlineLayer onlineLayer = new OnlineLayer(MainActivity.this);
        onlineLayer.addCustomTileToMap(mapView);
    }

    private void createMap(){
        AndroidGraphicFactory.createInstance(this.getApplication());

        mapView = new MapView(this);

        final RelativeLayout rl = findViewById(R.id.map_view);
        rl.addView(mapView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        mapView.setClickable(true);
        mapView.getMapScaleBar().setVisible(false);
        mapView.setBuiltInZoomControls(false);

        mapView.getModel().mapViewPosition.setCenter(new LatLong(35.7734, 51.0408));
        mapView.getModel().mapViewPosition.setZoomLevel((byte) 11);

        mapView.setZoomLevelMin((byte)1);
        mapView.setZoomLevelMax((byte)25);
    }

    @Override
    protected void onStart() {
        super.onStart();
        OnlineLayer.getCustomTileDownloadLayer().onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OnlineLayer.getCustomTileDownloadLayer().onDestroy();
    }
}
