package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.entities.actions.BanAction;

public interface BulkBan {



    BulkBan addId(String id);
    BulkBan addIds(String... ids);
    BulkBan removeId(String id);

    @Override
    String toString();

    BanAction set();

}
