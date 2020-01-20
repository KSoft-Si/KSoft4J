package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.SpotifyArtist;
import org.json.JSONObject;

public class SpotifyArtistImpl implements SpotifyArtist {
    private JSONObject json;

    public SpotifyArtistImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getName() {
        return json.getString("name");
    }

    @Override
    public String getLink() {
        return json.getString("link");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SpotifyArtist{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
