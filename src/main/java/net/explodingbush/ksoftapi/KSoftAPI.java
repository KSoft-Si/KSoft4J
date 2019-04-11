package net.explodingbush.ksoftapi;

import net.explodingbush.ksoftapi.entities.actions.*;
import net.explodingbush.ksoftapi.entities.lyrics.LyricCache;
import net.explodingbush.ksoftapi.enums.ImageType;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.image.ImageTag;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.webhooks.WebhookManager;

public class KSoftAPI {

	private static LyricCache cache;
    private final String token;
    private String request;

    public KSoftAPI(String token) {
    	Checks.notNull(token, "token");
    	ImageTag.setToken(token);
        this.token = token;
        if(cache == null) {
        	cache = new LyricCache(token);
        }
    }

    public WebhookManager getWebhookManager() {
        return new WebhookManager(this);
    }

    /**
     * This returns a {@link RedditAction RedditAction} using the declared {@link net.explodingbush.ksoftapi.enums.ImageType ImageTypes}
     * @param type
     * The {@link ImageType ImageType} you'd like to use
     * @return {@link RedditAction RedditAction}s
     */
    public RedditAction getRedditImage(ImageType type) {
    	Checks.notNull(type, "type");
        switch (type) {
            case RANDOM_MEME:
                this.request = Routes.REDDIT + "random-meme";
                break;
            case RANDOW_AWW:
                this.request = Routes.REDDIT + "random-aww";
                break;
            case RANDOM_NSFW:
                this.request = Routes.REDDIT + "random-nsfw";
                break;
            case RANDOM_REDDIT:
                this.request = Routes.REDDIT + "rand-reddit";
        }
        return new RedditAction(token, type, request);
    }

    /**
     * This returns a {@link TaggedImageAction TaggedImageAction} using the declared {@link net.explodingbush.ksoftapi.image.ImageTag ImageTags}
     * @param tag
     * The {@link ImageTag ImageTag} you'd like to use
     * @return {@link TaggedImageAction TaggedImageAction}
     */
    public TaggedImageAction getTaggedImage(ImageTag tag) {
    	Checks.notNull(tag, "tag");
        return new TaggedImageAction(token, tag);
    }
    /**
     * This returns a {@link TaggedImageAction TaggedImageAction} using the declared {@link net.explodingbush.ksoftapi.enums.ImageTag ImageTags}
     * @param tag
     * The {@link net.explodingbush.ksoftapi.enums.ImageTag ImageTag} you'd like to use
     * @return {@link TaggedImageAction TaggedImageAction}
     */
    @SuppressWarnings("deprecation")
	public TaggedImageAction getTaggedImage(net.explodingbush.ksoftapi.enums.ImageTag tag) {
    	Checks.notNull(tag, "tag");
        return new TaggedImageAction(token, tag);
    }
    /**
     * This returns a {@link WikihowAction WikihowAction}
     *
     * @return {@link WikihowAction WikihowAction}
     */
    public WikihowAction getRandomWikihow() {
        return new WikihowAction(token);
    }

    /**
     * This returns a {@link BanAction BanAction}
     *
     * @return {@link BanAction BanAction}
     */
    public BanAction getBan() {
        return new BanAction(token);
    }

    /**
     * This returns a {@link LyricAction LyricAction}
     *
     * @return {@link BanAction LyricAction}
     */
    public LyricAction getLyrics(){
    	return new LyricAction(token, cache);
    }

    /**
     * This returns a {@link KumoAction KumoAction}
     *
     * @return {@link KumoAction KumoAction}
     */
    public KumoAction getKumo() {
        return new KumoAction(token);
    }

    /**
     * This returns a {@link MusicAction MusicAction}
     *
     * @return {@link MusicAction KumoAction}
     */
    public MusicAction getMusic() { 
    	return new MusicAction(token); 
    }
}
