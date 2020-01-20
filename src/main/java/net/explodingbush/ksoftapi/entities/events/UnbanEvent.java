package net.explodingbush.ksoftapi.entities.events;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.Ban;

public class UnbanEvent extends Event {

    private final Ban ban;

    public UnbanEvent(KSoftAPI api, Ban ban) {
        super(api);
        this.ban = ban;
    }

    public Ban getBan() {
        return ban;
    }
}
