package net.explodingbush.ksoftapi.entities;

import java.time.OffsetDateTime;

public interface Reddit {

    /**
     * The Reddit post title
     *
     * @return The corresponding Reddit post
     */
    String getTitle();
    /**
     * The Reddit image URL
     *
     * @return The corresponding Reddit image URL
     */
    String getImageUrl();

    /**
     * The Reddit source URL
     *
     * @return The corresponding Reddit source URL
     */
    String getSourceUrl();

    /**
     * The subreddit used
     *
     * @return The corresponding subreddit
     */
    String getSubreddit();

    /**
     * The amount of upvotes a post
     *
     * @return The corresponding amount of upvotes on the post
     */
    String getUpvotes();

    /**
     * The amount of upvotes a post as an integer
     *
     * @return The corresponding amount of upvotes on the post as an integer
     */
    int getUpvotesInt();

    /**
     * The amount of downvotes on a post
     *
     * @return The corresponding amount of downvotes on the post
     */
    String getDownvotes();

    /**
     * The amount of downvotes a post as an integer
     *
     * @return The corresponding amount of downvotes on the post as an integer
     */
    int getDownvotesInt();

    /**
     * The time the post was posted
     *
     * @return The OffsetDateTime of the post
     */
    OffsetDateTime getCreationTime();
    /**
     * If a post is NSFW
     *
     * @return If the post is an NSFW post
     */
    boolean isNsfw();

    /**
     * The author of a Reddit post
     *
     * @return The corresponding author of the Reddit post
     */
    String getAuthor();

    /**
     * A subreddit active value
     *
     * @return The corresponding true/false if the ban is active
     */
    boolean subredditExists();

    @Override
    String toString();


}
