package net.explodingbush.ksoftapi.entities;

public interface Wikihow {

    /**
     * The URL of a header image
     *
     * @return The corresponding header image of the Wikihow post
     */
    String getImage();

    /**
     * The title of a Wikihow post
     *
     * @return The corresponding title of the Wikihow post
     */
    String getTitle();

    /**
     * The post NSFW boolean
     *
     * @return The corrosponding boolean if the post is an NSFW post
     */
    boolean isNsfw();

    /**
     * The URK of a Wikihow post
     *
     * @return The corresponding URL of the Wikihow post
     */
    String getArticleUrl();

    @Override
    String toString();

}
