package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.YouTubeTrack;
import org.json.JSONObject;

public class YouTubeTrackImpl implements YouTubeTrack {

    private JSONObject json;

    public YouTubeTrackImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getId() {
        return json.getString("id");
    }

    @Override
    public String getLink() {
        return json.getString("link");
    }

    @Override
    public String getTitle() {
        return json.getString("title");
    }

    @Override
    public String getThumbnailUrl() {
        return json.getString("thumbnail");
    }

    @Override
    public String getDescription() {
        return json.getString("description");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("YouTubeTrack{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
