package net.explodingbush.ksoftapi.utils;

public class ArrayUtils {

	public static int[] toIntArray(String[] array){
		int[] out = new int[array.length];
		for(int i = 0; i < out.length; i++){
			out[i] = Integer.parseInt(array[i]);
		}
		return out;
	}
}
