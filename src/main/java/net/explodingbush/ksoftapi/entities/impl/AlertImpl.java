package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Alert;
import org.json.JSONObject;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlertImpl implements Alert {

    private JSONObject json;

    public AlertImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getTitle() {
        return json.getString("title");
    }

    @Override
    public List<String> getRegions() {
        List<String> regions = new ArrayList<>();
        for(Object o : json.getJSONArray("regions")) {
            regions.add(o.toString());
        }
        return Collections.unmodifiableList(regions);
    }

    @Override
    public String getSeverity() {
        return json.getString("severity");
    }

    @Override
    public OffsetDateTime getTime() {
        long time = json.getLong("time");
        return Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Override
    public OffsetDateTime getExpiryTime() {
        long expires = json.getLong("expires");
        return Instant.ofEpochSecond(expires).atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Override
    public String getDescription() {
        return json.getString("description");
    }

    @Override
    public String getURL() {
        return json.getString("uri");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Alert{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
