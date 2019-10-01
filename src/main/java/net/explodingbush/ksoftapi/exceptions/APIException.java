package net.explodingbush.ksoftapi.exceptions;

public class APIException extends RuntimeException {

	private Exception e;
	
	public APIException(Exception e) {
		super(e.getMessage());
		this.e = e;
	}
	public String toString() {
		return this.getClass().getName() + ": Ran into an exception when fetching data from the API!\n"+e;
	}
}
