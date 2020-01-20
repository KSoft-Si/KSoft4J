package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Location;
import org.json.JSONObject;

public class LocationImpl implements Location {

    private JSONObject json;

    public LocationImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public double getLatitude() {
        return json.getDouble("lat");
    }

    @Override
    public double getLongitude() {
        return json.getDouble("lon");
    }

    @Override
    public String getAddress() {
        return json.getString("address");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Location{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
