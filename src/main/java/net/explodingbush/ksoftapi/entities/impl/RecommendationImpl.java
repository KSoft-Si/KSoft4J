package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Recommendation;
import net.explodingbush.ksoftapi.enums.RecommendationProvider;
import net.explodingbush.ksoftapi.entities.RecommendationTrack;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationImpl implements Recommendation {

    private JSONObject json;

    public RecommendationImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public RecommendationProvider getProvider() {
        String provider = json.getString("provider");
        switch (provider) {
            case "youtube_ids":
                return RecommendationProvider.YOUTUBE_IDS;
            case "youtube":
                return RecommendationProvider.YOUTUBE_URLS;
            case "spotify":
                return RecommendationProvider.SPOTIFY_IDS;
            default:
                return null;
        }
    }

    @Override
    public int getTotal() {
        return json.getInt("total");
    }

    @Override
    public List<RecommendationTrack> getRecommendationTracks() {
        ArrayList<RecommendationTrack> tracks = new ArrayList<>();
        JSONArray array = json.getJSONArray("tracks");
        for(Object o : array) {
            JSONObject obj = new JSONObject(o.toString());
            tracks.add(new RecommendationTrackImpl(obj));
        }
        return Collections.unmodifiableList(tracks);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Recommendation{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
