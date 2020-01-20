package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Ban;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BanImpl implements Ban {

    private final JSONObject json;
    public BanImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public long getId() {
        return json.optLong("id");
    }

    @Override
    public String getName() {
        return json.optString("name");
    }

    @Override
    public int getDiscriminator() {
        return Integer.parseInt(json.optString("discriminator"));
    }

    @Override
    public String getEffectiveName() {
        return getName() + "#" + getDiscriminator();
    }

    @Override
    public long getModId() {
    	if(!json.has("moderator_id")) {
            return 0L;
        }
        return json.optLong("moderator_id");
    }

    @Override
    public String getReason() {
        return json.optString("reason");
    }

    @Override
    public String getProof() {
        return json.optString("proof");
    }

    @Override
    public boolean isBanActive() {
        return json.optBoolean("is_ban_active");
    }

    @Override
    public boolean isAppealable() {
        return json.optBoolean("can_be_appealed");
    }


    @Override
    public OffsetDateTime getTimestamp() {
        return LocalDateTime.parse(json.optString("timestamp"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public boolean exists() {
        return json.optBoolean("exists");
    }

    @Override
    public boolean isBanned() {
        return exists();
    }

    @Override
    public List<Ban> getBannedList() {
        JSONArray array = json.optJSONArray("data");
        List<Ban> banList = new ArrayList<>();
        for(Object o : array) {
            banList.add(new BanImpl(new JSONObject(o.toString())));
        }
        return Collections.unmodifiableList(banList);
    }

    @Override
    public Stream<Ban> getBannedStream() {
        return getBannedList().stream();
    }

    @Override
    public List<Ban> getBulkBanList() {
        return getBannedList();
    }

    @Override
    public Stream<Ban> getBulkBanStream() {
        return getBannedStream();
    }

    @Override
    public String getAppealReason() {
        if(json.optString("appeal_reason").isEmpty()) {
            return null;
        }
        return json.optString("appeal_reason");
    }

    @Override
    public OffsetDateTime getAppealDate() {
        if(json.isNull("appeal_date")) {
            return null;
        }
        return LocalDateTime.parse(json.optString("appeal_date"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Ban{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
