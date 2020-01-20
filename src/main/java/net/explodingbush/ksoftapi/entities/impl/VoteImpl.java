package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Vote;
import org.json.JSONObject;

public class VoteImpl implements Vote {

    private JSONObject json;

    public VoteImpl(JSONObject json) {
        this.json = json;
    }
    @Override
    public String getType() {
        return json.getString("type");
    }

    @Override
    public long getBotIdLong() {
        return json.getLong("bot");
    }

    @Override
    public long getUserIdLong() {
        return json.getLong("user");
    }

    @Override
    public String getUserId() {
        return String.valueOf(json.getLong("user"));
    }

    @Override
    public String getBotId() {
        return String.valueOf(json.getLong("bot"));
    }

    @Override
    public boolean isWeekend() {
        return json.getBoolean("isWeekend");
    }

    @Override
    public String getQuery() {
        return json.getString("query");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vote{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
