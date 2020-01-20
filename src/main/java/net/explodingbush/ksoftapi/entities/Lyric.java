package net.explodingbush.ksoftapi.entities;

public interface Lyric {

	/**
	 * The artist name of a song
	 *
	 * @return The corresponding artist name of the song
	 */
	String getArtistName();

	/**
	 * The search query for used to find a song's lyrics
	 *
	 * @return The corresponding search query for used to find the song's lyrics
	 */
	String getSearchString();

	/**
	 * The title of the song
	 *
	 * @return The corresponding title of the song
	 */
	String getTitle();

	/**
	 * The title and artist of a song
	 *
	 * @return The corresponding title and artist of the song
	 */
	String getFullTitle();

	/**
	 * The lyrics of a song
	 *
	 * @return The corresponding lyrics of the song
	 */
	String getLyrics();

	/**
	 * The album art of a song
	 *
	 * @return The corresponding URL for the song's album art
	 */
	String getAlbumArtUrl();

	/**
	 * The album(s) a song resides in
	 *
	 * @return The corresponding album(s) a song resides in
	 */
	String[] getAlbums();

	/**
	 * The KSoft ID of an artist
	 *
	 * @return The corresponding KSoft ID of the artist
	 */
	int getArtistId();

	/**
	 * The KSoft ID of a song
	 *
	 * @return The corresponding KSoft ID of the song
	 */
	int getSongId();

	/**
	 * The KSoft ID's of the album(s) a song resides in
	 *
	 * @return The corresponding KSoft ID's of the album(s) the song resides in
	 */
	int[] getAlbumIds();

	/**
	 * The release years of the album(s)
	 *
	 * @return The corresponding release years of the album(s)
	 */
	int[] getAlbumReleaseYears();

	@Override
	String toString();

	
}
