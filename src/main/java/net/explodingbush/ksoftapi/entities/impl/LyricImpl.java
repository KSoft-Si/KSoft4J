package net.explodingbush.ksoftapi.entities.impl;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.entities.Lyric;
import net.explodingbush.ksoftapi.utils.ArrayUtils;

@Deprecated
public class LyricImpl implements Lyric {

	private JSONObject json;

	@Override
	public String getAlbumArtUrl() {
		return json.getString("album_art");
	}

	public LyricImpl(JSONObject json){
		this.json = json;
	}
	
	@Override
	public String getArtistName() {
		return json.getString("artist");
	}

	@Override
	public String getSearchString() {
		return json.getString("search_str");
	}

	@Override
	public String getTitle() {
		return json.getString("name");
	}

	@Override
	public String getFullTitle() {
		return String.format("%s - %s", getArtistName(), getTitle());
	}

	@Override
	public String getLyrics() {
		return json.getString("lyrics");
	}

	@Override
	public String[] getAlbums() {
		return json.getString("album").split(",");
	}

	@Override
	public int getArtistId() {
		return json.getInt("artist_id");
	}

	@Override
	public int getSongId() {
		return Integer.parseInt(json.getString("id"));
	}

	@Override
	public int[] getAlbumIds() {
		String ids = json.getString("album_ids");
		if(!ids.contains(",")){
			int[] arr = new int[1];
			arr[0] = Integer.parseInt(ids);
			return arr;
		}
		return ArrayUtils.toIntArray(ids.split(","));
	}

	@Override
	public int[] getAlbumReleaseYears() {
		String ids = json.getString("album_year");
		if(!ids.contains(",")){
			int[] arr = new int[1];
			arr[0] = Integer.parseInt(ids);
			return arr;
		}
		return ArrayUtils.toIntArray(ids.split(","));
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lyric{\n");
		json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
		builder.append("}");
		return builder.toString();
	}
}
