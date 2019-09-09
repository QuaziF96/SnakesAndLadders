package main.Model;

public class Ladder extends Entity {
	private int bottom;
	private int top;

	public Ladder(int b, int t) {
		bottom = b;
		top = t;
	}

	// Moves piece from bottom to top of ladder
	public boolean activate(Entity HumanPiece) {
		return true;
	}

	// Implements super.move()
	public boolean move(int target) {
		return true;
	}

	public int getBottom() {
		return bottom;
	}

	public int getTop() {
		return top;
	}
}