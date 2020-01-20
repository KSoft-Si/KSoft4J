package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.Wikihow;
import net.explodingbush.ksoftapi.entities.impl.WikihowImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import org.json.JSONObject;

public class WikihowAction extends KSoftActionAdapter<Wikihow> {

    private String token;
    private JSONObject json;
    private boolean allowNsfw;

    public WikihowAction(String token) {
    	Checks.notNull(token, "token");
        this.token = token;
    }

    /**
     * Declare if an image is allowed to be NSFW
     * <p>Default: <b>true (yes)</b></p>
     * @param nsfw
     * True - Allow NSFW images
     * @return The TaggedImageAction instance. Useful for chaining.
     */
    public WikihowAction allowNsfw(boolean nsfw) {
        this.allowNsfw = nsfw;
        return this;
    }

    /**
     * Executes the request with the specified parameters
     *
     * @return The Wikihow source
     * @throws net.explodingbush.ksoftapi.exceptions.LoginException
     * If the token is not provided or incorrect.
     */
    public Wikihow execute() {
        json = new JSONBuilder().requestKsoft(String.format(Routes.WIKIHOW.toString(), String.valueOf(allowNsfw)), token);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        return new WikihowImpl(json);
    }
}