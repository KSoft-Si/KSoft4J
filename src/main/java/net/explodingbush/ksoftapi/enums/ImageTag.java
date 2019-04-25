package net.explodingbush.ksoftapi.enums;

import java.util.Arrays;

@Deprecated
public enum ImageTag {
	
    PEPE, 
    DOGE, 
    KAPPA,
    DAB, 
    BIRB, 
    DOG, 
    FBI, 
    LICK, 
    KISS, 
    SMACK, 
    SPANK, 
    PAT, 
    HUG, 
    HENTAI_GIF, 
    HEADRUB, 
    NEKO;
	
	public boolean isNSFW(ImageTag tag) {
		return Arrays.asList(NEKO, HENTAI_GIF, LICK).contains(tag);
	}
	public boolean isNSFW() {
		return isNSFW(this);
	}
}
