package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.Reddit;
import net.explodingbush.ksoftapi.entities.impl.RedditImpl;
import net.explodingbush.ksoftapi.enums.ImageType;
import net.explodingbush.ksoftapi.enums.Span;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import net.explodingbush.ksoftapi.webhooks.WebhookService;
import okhttp3.Response;

import org.json.JSONObject;
import org.slf4j.Logger;

public class RedditAction extends KSoftActionAdapter<Reddit> {

    private String token;
    private ImageType type;
    private Span span;
    private String subreddit;
    private String request;
    private boolean allowNSFW;
    private Logger logger = new WebhookService(null).getLogger();

    public RedditAction(String token, ImageType type, String request) {
    	Checks.notNull(token, "token");
    	Checks.notNull(type, "type");
    	Checks.notNull(request, "request");
        this.token = token;
        this.type = type;
        this.request = request;
        this.span = Span.DAY;
    }

    /**
     * Sets the subreddit
     * @param subreddit
     *  The subreddit to use with an {@link ImageType#RANDOM_REDDIT ImageType#RANDOM_REDDIT}
     * @return RedditAction instance. Useful for chaining.
     */
    public RedditAction setSubreddit(String subreddit) {
    	Checks.notNull(subreddit, "subreddit");
    	while(subreddit.endsWith("/")) {
    		subreddit = subreddit.substring(0, subreddit.length()-1);
    	}
    	Checks.notBlank(subreddit, "subreddit");
    	if(subreddit.contains("/")) {
    		subreddit = subreddit.substring(subreddit.lastIndexOf("/")+1);
    	}
        this.subreddit = subreddit;
        return this;
    }
    
    /**
     * Sets whether to allow NSFW posts. Default is false.
     * @param nsfw
     *  Whether to allow an NSFW post to be returned.
     * @return RedditAction instance. Useful for chaining.
     */
    public RedditAction allowNSFW(boolean nsfw) {
    	this.allowNSFW = nsfw;
    	return this;
    }
    
    /**
     * Sets the time span. Default is {@link Span#DAY}.
     * @param spam
     *  Time span to search for images in.
     * @return RedditAction instance. Useful for chaining.
     */
    public RedditAction setSpan(Span span) {
    	Checks.notNull(span, "span");
    	this.span = span;
    	return this;
    }
    
    /**
     * Executes the request with the specified parameters
     *
     * @return A new {@link Reddit Reddit} instance with the provided {@link ImageType ImageTypes}
     * @throws net.explodingbush.ksoftapi.exceptions.LoginException           If the token is not provided or incorrect.
     * @throws net.explodingbush.ksoftapi.exceptions.MissingArgumentException If using {@link net.explodingbush.ksoftapi.enums.ImageType#RANDOM_REDDIT ImageType.RANDOM_REDDIT} without declaring a subreddit
     */
    @Override
    public Reddit execute() throws LoginException, MissingArgumentException {
        Response response;
        JSONObject json;
        if (request == null || type.equals(ImageType.RANDOM_REDDIT) && this.subreddit == null) {
            throw new MissingArgumentException("Subreddit not defined");
        }
        if (subreddit != null && !type.equals(ImageType.RANDOM_REDDIT)) {
            logger.warn("You're setting a subreddit, but ImageType is not RANDOM_REDDIT");
        }
        if (type.equals(ImageType.RANDOM_REDDIT)) {
            request += "/" + subreddit;
        }
        request += "?remove_nsfw=" + !allowNSFW;
        request += "&span=" + span.toString().toLowerCase();
        response = new JSONBuilder().requestKsoftResponse(request, token);
        json = new JSONBuilder().getJSONResponse(response);
        if(json.has("error") && json.getBoolean("error")) {
        	throw new NotFoundException(json.getString("message"));
        }
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        return new RedditImpl(json);
    }
}
