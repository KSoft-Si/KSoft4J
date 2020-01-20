package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.TaggedImage;
import org.json.JSONObject;

public class TaggedImageImpl implements TaggedImage {

    private final JSONObject json;
    public TaggedImageImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getUrl() {
        return json.getString("url");
    }

    @Override
    public String getSnowflake() {
        return json.getString("snowflake");
    }

    @Override
    public boolean isNsfw() {
        return json.getBoolean("nsfw");
    }

    @Override
    public String getTag() {
        return json.getString("tag");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TaggedImage{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
