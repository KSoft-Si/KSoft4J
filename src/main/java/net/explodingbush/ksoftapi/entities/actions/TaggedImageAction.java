package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.TaggedImage;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.entities.impl.TaggedImageImpl;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.image.ImageTag;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import org.json.JSONObject;

public class TaggedImageAction extends KSoftActionAdapter<TaggedImage> {

    private String token;
    private JSONObject json;
    private ImageTag tag;
    private boolean allowNsfw = false;

    public TaggedImageAction(String token, ImageTag tag) {
    	Checks.notNull(token, "token");
    	Checks.notNull(tag, "tag");
        this.token = token;
        this.tag = tag;
    }
    @SuppressWarnings("deprecation")
	public TaggedImageAction(String token, net.explodingbush.ksoftapi.enums.ImageTag tag) {
		this(token, ImageTag.valueOf(tag.toString().toLowerCase()));
	}

	/**
     * Declare if an image is allowed to be NSFW
     * <p>Default: <b>true (yes)</b></p>
     * @param nsfw
     * True - Allow NSFW images
     * @return The TaggedImageAction instance. Useful for chaining.
     */
    public TaggedImageAction allowNsfw(boolean nsfw) {
        this.allowNsfw = nsfw;
        return this;
    }

    public TaggedImage execute() {
        json = new JSONBuilder().requestKsoft(String.format(Routes.IMAGE.toString(), tag.toString(), String.valueOf(allowNsfw)), token);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        return new TaggedImageImpl(json);
    }
}
