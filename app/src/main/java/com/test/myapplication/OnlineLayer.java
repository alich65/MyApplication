package com.test.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.download.TileDownloadLayer;

public class OnlineLayer {

    private static TileDownloadLayer customTileDownloadLayer;

    private AppCompatActivity activity;

    public OnlineLayer(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void addCustomTileToMap(MapView mapView){
        TileCache tileCache = AndroidUtil.createTileCache(
                activity,
                "customMap",
                mapView.getModel().displayModel.getTileSize(),
                1f,
                mapView.getModel().frameBufferModel.getOverdrawFactor()
        );

        int port = 80;
        CustomOnlineTileSource customOnlineTileSourceTile = new CustomOnlineTileSource(
                new String[]{
                        "a.tile.openstreetmap.fr/hot",
                        "b.tile.openstreetmap.fr/hot"
                },
                port
        );

        customOnlineTileSourceTile
                .setParallelRequestsLimit(8)
                .setTileSize(256)
                .setZoomLevelMax((byte) 20)
                .setZoomLevelMin((byte) 0)
                .setAlpha(true);

        customTileDownloadLayer = new TileDownloadLayer(
                tileCache,
                mapView.getModel().mapViewPosition,
                customOnlineTileSourceTile,
                AndroidGraphicFactory.INSTANCE);

        mapView.getLayerManager().getLayers().add(customTileDownloadLayer);

    }

    public static TileDownloadLayer getCustomTileDownloadLayer() {
        return customTileDownloadLayer;
    }
}
