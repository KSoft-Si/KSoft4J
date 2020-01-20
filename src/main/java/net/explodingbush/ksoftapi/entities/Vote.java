package net.explodingbush.ksoftapi.entities;

public interface Vote {

    /**
     * The type of a DBL vote
     *
     * @return The corresponding type of DBL vote
     */
    String getType();

    /**
     * The bot ID a user voted for as a long
     *
     * @return The corresponding bot ID a user voted for as a long
     */
    long getBotIdLong();

    /**
     * The user who voted as a long
     *
     * @return The corresponding user who voted as a long
     */
    long getUserIdLong();

    /**
     * The user who voted
     *
     * @return The corresponding user who vote
     */
    String getUserId();

    /**
     * The bot ID a user voted for
     *
     * @return The corresponding bot ID a user voted for
     */
    String getBotId();

    /**
     * The boolean if a user voted on a weekend
     *
     * @return The corresponding boolean if the user voted on a weekend
     */
    boolean isWeekend();

    /**
     * The query used to capture a JSON response
     *
     * @return The corresponding query used to capture the JSON response
     */
    String getQuery();

    @Override
    String toString();

}
