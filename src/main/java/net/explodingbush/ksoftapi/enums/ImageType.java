package net.explodingbush.ksoftapi.enums;

import net.explodingbush.ksoftapi.entities.actions.RedditAction;

public enum ImageType {
    /**
     * Random memes from <b>dankmemes</b> and <b>memes</b>
     *
     * @see <a href="https://reddit.com/r/dankmemes" target="_blank">DankMemes</a>
     * @see <a href="https://reddit.com/r/memes" target="_blank">Memes</a>
     */
    RANDOM_MEME,

    /**
     * Random NSFW images from <b>asstastic</b>, <b>gonewild</b>, <b>palegirls</b>, <b>HappyEmbarrassedGirls</b>, <b>StraightGirlsPlaying</b>, <b>AsiansGoneWild</b>
     *
     * @see <a href="https://reddit.com/r/asstastic" target="_blank">Asstastic</a>
     * @see <a href="https://reddit.com/r/gonewild" target="_blank">GoneWild</a>
     * @see <a href="https://reddit.com/r/palegirls" target="_blank">PaleGirls</a>
     * @see <a href="https://reddit.com/r/happyembarrassedgirls" target="_blank">HappyEmbarrassedGirls</a>
     * @see <a href="https://reddit.com/r/straightgirlsplaying" target="_blank">StraightGirlsPlaying</a>
     * @see <a href="https://reddit.com/r/asiansgonewild" target="_blank">AsiansGoneWild</a>
     */
    RANDOM_NSFW,

    /**
     * Random cute pictures from <b>aww</b> and <b>Eyebleach</b>
     *
     * @see <a href="https://reddit.com/r/aww" target="_blank">aww</a>
     * @see <a href="https://reddit.com/r/eyebleach" target="_blank">Eyebleach</a>
     */
    RANDOW_AWW,
    /**
     * Random images from the {@link RedditAction#setSubreddit(String) RedditAction#setSubreddit}
     *
     */
    RANDOM_REDDIT
}
