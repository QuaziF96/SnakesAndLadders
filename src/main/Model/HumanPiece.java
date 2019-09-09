package main.Model;

import java.util.ArrayList;

public class HumanPiece extends Entity {
	
	String name;
	int location;
	int ladderCount;
	boolean paralysed;
	ArrayList<Ladder> laddersClimbed;
	int paralysedTurns = 0;
	boolean isActivated = false;
	
	public HumanPiece(String name,int location) {
		
		this.location = location;
		laddersClimbed = new ArrayList<Ladder>();
	}
	
	//Activate stage three when location 100 is reached after climbing three ladders
	public boolean activate(Entity subject) {
		return true;
	}
	
	//Moves piece from location to target
	public boolean move(int target) {
		return true;
	}
	
	public void activate() {
		isActivated = true;
	}
	
	public boolean getisActivated() {
		return this.isActivated;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public void setParalyse(boolean status) {
		this.paralysed = status;
	}
	
	public boolean getParalyse() {
		return this.paralysed;
	}
	
	public void setParalysedTurns(int paralysedTurns) {
		this.paralysedTurns = paralysedTurns;
	}
	
	public int getParalysedTurns() {
		return this.paralysedTurns;
	}
	
	public ArrayList<Ladder> getLaddersClimbed() {
		return this.laddersClimbed;
	}
	public void setLaddersClimbed(Ladder ladder) {
		this.laddersClimbed.add(ladder);
	}

}
