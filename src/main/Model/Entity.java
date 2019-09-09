package main.Model;

public abstract class Entity {

	
	//Moves entity to target location, checks validity
	public abstract boolean move(int target);
	
	//Activates entity effect on the entity which tripped it
	public abstract boolean activate(Entity subject);
	
	
}
