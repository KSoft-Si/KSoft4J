package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.SpotifyTrack;
import net.explodingbush.ksoftapi.entities.SpotifyAlbum;
import net.explodingbush.ksoftapi.entities.SpotifyArtist;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpotifyTrackImpl implements SpotifyTrack {

    private JSONObject json;
    public SpotifyTrackImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getSongId() {
        return json.getString("id");
    }

    @Override
    public String getSongTitle() {
        return json.getString("name");
    }

    @Override
    public String getLink() {
        return json.getString("link");
    }

    @Override
    public SpotifyAlbum getAlbum() {
        return new SpotifyAlbumImpl(json.getJSONObject("album"));
    }

    @Override
    public List<SpotifyArtist> getArtists() {
        ArrayList<SpotifyArtist> artistList = new ArrayList<>();
        JSONArray artists = json.getJSONArray("artists");
        for(Object o : artists) {
            JSONObject obj = new JSONObject(o.toString());
            artistList.add(new SpotifyArtistImpl(obj));
        }
        return Collections.unmodifiableList(artistList);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SpotifyTrack{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
