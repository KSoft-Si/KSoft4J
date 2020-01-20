package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.entities.actions.BanAction;

public interface AddBan {

    /**
     * Sets the user ID to report
     *
     * @param user
     * User ID to report
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setUserId(String user);

    /**
     * Sets the user ID to report
     *
     * @param moderatorId
     * User ID to set as the report moderator
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setModeratorId(String moderatorId);

    /**
     * Sets the user ID to report
     *
     * @param userName
     * Username to set as the reportee username
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setUsername(String userName);

    /**
     * Sets the user ID to report
     *
     * @param discriminator
     * Discriminator to set as the reportee discriminator
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setDiscriminator(String discriminator);

    /**
     * Sets the user ID to report
     *
     * @param reason
     * The reason for the ban
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setReason(String reason);

    /**
     * Sets the user ID to report
     *
     * @param proof
     * Proof of the reportee's offense
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setProof(String proof);

    /**
     * Sets the user ID to report
     *
     * <p>Default: <b>true (yes)</b></p>
     *
     * @param appealable
     * True - Allow the ban to be appealable
     *
     *
     * @return The AddBan instance. Useful for chaining.
     */
    AddBan setAppealable(boolean appealable);

    /**
     * Sets a new ban to report with the appended values
     *
     * @return A new {@link BanAction BanAction} instance ready to report a new ban with.
     * <p>No need to set an values in {@link BanAction BanAction}, just use the {@link BanAction#execute() execute} function.</p>
     */
    BanAction set();

    @Override
    String toString();

}
