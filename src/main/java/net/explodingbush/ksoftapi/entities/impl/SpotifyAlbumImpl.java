package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.SpotifyAlbum;
import org.json.JSONObject;

public class SpotifyAlbumImpl implements SpotifyAlbum {

    private JSONObject json;

    public SpotifyAlbumImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getName() {
        return json.getString("name");
    }

    @Override
    public String getAlbumArtUrl() {
        return json.getString("album_art");
    }

    @Override
    public String getLink() {
        return json.getString("link");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SpotifyAlbum{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
