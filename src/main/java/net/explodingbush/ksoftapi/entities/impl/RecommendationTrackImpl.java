package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.RecommendationTrack;
import net.explodingbush.ksoftapi.entities.SpotifyTrack;
import net.explodingbush.ksoftapi.entities.YouTubeTrack;
import org.json.JSONObject;

public class RecommendationTrackImpl implements RecommendationTrack {

    private JSONObject json;
    public RecommendationTrackImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public YouTubeTrack getYouTube() {
        return new YouTubeTrackImpl(json.getJSONObject("youtube"));
    }

    @Override
    public SpotifyTrack getSpotify() {
        return new SpotifyTrackImpl(json.getJSONObject("spotify"));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RecommendationTrack{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
