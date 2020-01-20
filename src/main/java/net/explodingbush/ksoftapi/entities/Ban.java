package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.entities.actions.BanAction;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface Ban {

    /**
     * The ID of a banned user
     *
     * @return The corresponding Discord user ID of the banned user
     */
    long getId();

    /**
     * The username of a banned user
     *
     * @return The corresponding Discord username of the banned user
     */
    String getName();

    /**
     * The discriminator of a banned user
     *
     * @return The corresponding Discord discriminator of the banned user
     */
    int getDiscriminator();

    /**
     * The effective name of the banned user
     *
     * @return The corresponding Discord effective name of the banned user
     * <p>An example of this could look like <b>Matt#0001</b></p>
     */
    String getEffectiveName();

    /**
     * The moderator ID of the ban
     *
     * <p>If the moderator ID is not provided, it would be the Discord user tied to the KSoft token holder, or if one is provided, it's overriden to be the Ban Manager who either accepts or rejects the request.
     *
     * @return The corresponding Discord moderator ID of the bannned user
     *
     */
    long getModId();

    /**
     * The reason on why a user was banned
     *
     * @return The corresponding reason why the user was banned
     */
    String getReason();

    /**
     * The proof of why a user was banned
     *
     * @return The corresponding proof of why the user was banned
     */
    String getProof();

    /**
     * A value of a user's ban status
     *
     * @return The corresponding boolean if the user is banned
     */
    boolean isBanActive();

    /**
     * A value if a user's ban is appeable
     *
     * @return The corresponding boolean if the user's ban is appealable
     */
    boolean isAppealable();

    /**
     * The OffsetDateTime of a user's initial ban acceptance
     *
     * @return The corresponding OffsetDateTime of the user's initial ban acceptance
     */
    OffsetDateTime getTimestamp();

    /**
     * A value of the ban's existance
     *
     * @return The corresponding true/false if the ban exists
     */
    boolean exists();

    /**
     * A ban's active value
     *
     * @return The corresponding boolean if the ban is active
     */
    boolean isBanned();

    /**
     * Gets a list of banned users
     *
     * @return An immutable list of banned users specified by {@link BanAction#getBanList(int) BanAction#getBanList}
     */
    List<Ban> getBannedList();

    /**
     * Gets a stream of banned users
     *
     * @return An immutable stream of banned users specified by {@link BanAction#getBanList(int) BanAction#getBanList}
     */
    Stream<Ban> getBannedStream();

    /**
     * Gets a list of banned users from a bulk ban check
     *
     * @return An immutable list of banned users from a bulk ban check specified by {@link BanAction#checkBulkBan()}  BanAction#checkBulkBan}
     */
    List<Ban> getBulkBanList();

    /**
     * Gets a stream of banned users from a bulk ban check
     *
     * @return An immutable stream of banned users from a bulk ban check specified by {@link BanAction#checkBulkBan()}  BanAction#checkBulkBan}
     */
    Stream<Ban> getBulkBanStream();

    /**
     * The appeal reason for a user ban
     * <br>If the user is still banned or does not have an appeal reason, this will return null.
     *
     * @return Possibly-null corresponding appeal reason for the user ban
     */
    String getAppealReason();

    /**
     * The appeal date for a user ban
     * <br>If the user is still banned, this will return null.
     *
     * @return Possibly-null corresponding appeal date for the user ban
     */
    OffsetDateTime getAppealDate();

    @Override
    String toString();
}
