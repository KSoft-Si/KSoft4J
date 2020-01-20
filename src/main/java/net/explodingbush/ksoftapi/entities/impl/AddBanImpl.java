package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.AddBan;
import net.explodingbush.ksoftapi.entities.actions.BanAction;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import net.explodingbush.ksoftapi.utils.Checks;

import org.json.JSONObject;

public class AddBanImpl implements AddBan {

    private JSONObject json = new JSONObject();

    @Override
    public AddBan setUserId(String user) {
    	Checks.notNull(user, "user");
        json.put("user", user);
        return this;
    }

    @Override
    public AddBan setModeratorId(String moderatorId) {
    	Checks.notNull(moderatorId, "moderatorId");
        json.put("mod", moderatorId);
        return this;
    }

    @Override
    public AddBan setUsername(String userName) {
    	Checks.notNull(userName, "userName");
        json.put("user_name", userName);
        return this;
    }

    @Override
    public AddBan setDiscriminator(String discriminator) {
    	Checks.notNull(discriminator, "discriminator");
        json.put("user_discriminator", discriminator);
        return this;
    }

    @Override
    public AddBan setReason(String reason) {
    	Checks.notNull(reason, "reason");
        json.put("reason", reason);
        return this;
    }

    @Override
    public AddBan setProof(String proof) {
    	Checks.notNull(proof, "proof");
        json.put("proof", proof);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddBan{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }

    @Override
    public AddBan setAppealable(boolean appealable) {
        json.put("appeal_possible", String.valueOf(appealable));
        return this;
    }

    @Override
    public BanAction set() {
        if(json.isNull("user")) {
            throw new MissingArgumentException("setUserId cannot be null");
        }
        if(json.isNull("reason")) {
            throw new MissingArgumentException("setReason cannot be null");
        }
        if(json.isNull("proof")) {
            throw new MissingArgumentException("setProof cannot be null");
        }
        if(json.isNull("user_name")) {
            throw new MissingArgumentException("setUsername cannot be null");
        }
        if(json.isNull("user_discriminator")) {
            throw new MissingArgumentException("setDiscriminator cannot be null");
        }
        return new BanAction(true, false, json);
    }
}


