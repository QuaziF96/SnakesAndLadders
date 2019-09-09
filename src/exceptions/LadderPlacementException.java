package exceptions;

public class LadderPlacementException extends Exception {
	
	public LadderPlacementException() {
		
	}
	
	public LadderPlacementException(String mesg) {
		super(mesg);
	}
}