package net.explodingbush.ksoftapi.enums;

public enum Routes {
	
	BAN_ADD("https://api.ksoft.si/bans/add"),
	BAN_INFO("https://api.ksoft.si/bans/info?user="),
	BAN_LIST("https://api.ksoft.si/bans/list?per_page="),
	BAN_BULK("https://api.ksoft.si/bans/bulkcheck?more_info=yes"),
	IMAGE_TAGS("https://api.ksoft.si/images/tags"),
	IMAGE("https://api.ksoft.si/images/random-image?tag=%s&nsfw=%s"),
	WIKIHOW("https://api.ksoft.si/images/random-wikihow?nsfw=%s"),
	REDDIT("https://api.ksoft.si/images/"),
	LYRICS_SEARCH("https://api.ksoft.si/lyrics/search?q=%s&text_only=%s&limit=%d"),
	LYRICS_ARTIST("https://api.ksoft.si/lyrics/artist/%s/"),
	LYRICS_TRACK("https://api.ksoft.si/lyrics/track/%s/"),
	LYRICS_ALBUM("https://api.ksoft.si/lyrics/album/%s/"),
	KUMO_WEATHER("https://api.ksoft.si/kumo/weather/%s?q=%s&units=%s&lang=%s"),
	KUMO_CURRENCY("https://api.ksoft.si/kumo/currency?from=%s&to=%s&value=%.2f"),
	KUMO_IP("https://api.ksoft.si/kumo/geoip?ip=%s"),
	MUSIC_RECOMMEND("https://api.ksoft.si/music/recommendations");

	  
	private final String route;
	  
	Routes(String route){
	  this.route = route;
	}
	public String toString(){
	  return this.route;
	}
}
