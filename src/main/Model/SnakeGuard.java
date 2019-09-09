package main.Model;

public class SnakeGuard extends Entity {
	private int location;

	public SnakeGuard(int loc) {
		location = loc;
	}

	// Applies paralyse effect
	public boolean activate(Entity HumanPiece) {
		return true;
	}
	
	public boolean move(int target) {
		return true;
	}

	public int getLocation() {
		return location;
	}
	
	
}
