package com.test.myapplication;

import org.mapsforge.core.model.Tile;
import org.mapsforge.map.layer.download.tilesource.OnlineTileSource;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomOnlineTileSource extends OnlineTileSource {

    public CustomOnlineTileSource(String[] hostNames, int port) {
        super(hostNames, port);
    }

    @Override
    public URL getTileUrl(Tile tile) throws MalformedURLException {

        int x =tile.tileX;
        int y = tile.tileY;
        int zoom = tile.zoomLevel;

        String url = "http://"+ getHostName() + "/" + zoom + "/" + x + "/" + y + ".png";

        //Log.i("ali",url);
        return new URL( url);
    }


}
