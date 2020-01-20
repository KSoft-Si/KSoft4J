package net.explodingbush.ksoftapi.entities.lyrics;

import java.util.List;

public interface Album {

	List<Track> getTracks();
	Artist getArtist();
	String getName();
	int getReleaseYear();
	int getId();
}
