package net.explodingbush.ksoftapi.image;

import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImageTag {

	private static final Logger LOG = LoggerFactory.getLogger(ImageTag.class);
	private static Map<String, ImageTag> cache;
	private static String token;
	
	private String tag;
	private boolean nsfw;
	
	private ImageTag(String tag, boolean nsfw) {
		this.tag = tag;
		this.nsfw = nsfw;
	}
	@SuppressWarnings("deprecation")
	private ImageTag(net.explodingbush.ksoftapi.enums.ImageTag tag) {
		this.tag = tag.toString().toLowerCase();
		this.nsfw = tag.isNSFW();
	}
	public boolean isNSFW() {
		return nsfw;
	}
	
	public String toString() {
		return tag;
	}
	/**
	 * Returns the ImageTag associated with the given name from the cache
	 * 
	 * @param name
	 * The name of the ImageTag to be searched for
	 * @return the corresponding ImageTag
	 * 
	 * @throws IllegalArgumentException
	 * if the provided tag name was invalid or if it was null
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static ImageTag valueOf(String name) {
		Checks.notNull(name, "tag name");
		try {
			name = name.toLowerCase();
			synchronized(cache) {
				if(cache == null) {
					refreshCache().execute();
				}
				if(!cache.containsKey(name)) {
					throw new IllegalArgumentException("No tag called "+name);
				}
				return cache.get(name);
			}
		}
		catch(Exception e) {
			LOG.error(e.toString());
			return new ImageTag(net.explodingbush.ksoftapi.enums.ImageTag.valueOf(name.toUpperCase()));
		}
	}
	/**
	 * Refreshes the internal {@link ImageTag ImageTag} cache.
	 * 
	 * @return a {@link KSoftAction KSoftAction}
	 */
	public static synchronized KSoftAction<Void> refreshCache() {
		return new KSoftActionAdapter<Void>() {
			@Override
			public Void execute() {
				JSONObject j = new JSONBuilder().requestKsoft(Routes.IMAGE_TAGS.toString(), token);
				JSONArray models = j.getJSONArray("models");
				cache = new HashMap<>();
				models.forEach(obj -> {
					JSONObject json = (JSONObject)obj;
					cache.put(json.getString("name"), new ImageTag(json.getString("name"), json.getBoolean("nsfw")));
				});
				JSONArray nsfwTags = j.getJSONArray("nsfw_tags");
				nsfwTags.forEach(obj -> {
					cache.put(obj.toString(), new ImageTag(obj.toString(), true));
				});
				return null;
			}
			
		};
	}
	/**
	 * Gets the currently cached list of ImageTags.
	 * 
	 * @return an immutable {@link java.util.List list} of cached ImageTags
	 */
	public static List<ImageTag> getTags(){
		if(cache == null) {
			refreshCache().execute();
		}
		return cache.values().stream().collect(Collectors.toList());
	}
	
	public static void setToken(String token) {
		Checks.notNull(token, "token");
		ImageTag.token = token;
	}
}
