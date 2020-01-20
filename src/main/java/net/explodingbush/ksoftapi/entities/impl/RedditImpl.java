package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Reddit;
import net.explodingbush.ksoftapi.utils.Checks;
import org.json.JSONObject;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class RedditImpl implements Reddit {

    private final JSONObject json;
    public RedditImpl(JSONObject json) {
    	Checks.notNull(json, "json");
        this.json = json;
    }

    @Override
    public String getTitle() {
        return json.getString("title");
    }

    @Override
    public String getImageUrl() {
        return json.getString("image_url");
    }

    @Override
    public String getSourceUrl() {
        return json.getString("source");
    }

    @Override
    public String getSubreddit() {
        return json.getString("subreddit");
    }

    @Override
    public String getUpvotes() {
        return String.valueOf(json.getInt("upvotes"));
    }

    @Override
    public int getUpvotesInt() {
        return json.getInt("upvotes");
    }

    @Override
    public boolean subredditExists() {
        return !json.has("error");
    }

    @Override
    public String getDownvotes() {
        return String.valueOf(json.getInt("downvotes"));
    }

    @Override
    public int getDownvotesInt() {
        return json.getInt("downvotes");
    }

    @Override
    public OffsetDateTime getCreationTime() {
        long time = json.getLong("created_at");
        return Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Override
    public boolean isNsfw() {
        return json.getBoolean("nsfw");
    }

    @Override
    public String getAuthor() {
        return json.getString("author");
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Reddit{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
