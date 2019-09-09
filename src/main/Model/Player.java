package main.Model;

public class Player {
	
	private enum Type {
		ADMIN,
		HUMANCONTROLLER,
		SNAKECONTROLLER
	}

	private String name;
	private Type type;
	
	public Player(String name,String type) {
		this.name = name;
		if (type.equals("Admin")) {
			this.type = Type.ADMIN;
		}
		else if (type.equals("Human")) {
			this.type = Type.HUMANCONTROLLER;
		}
		else if (type.equals("Snake")) {
			this.type = Type.SNAKECONTROLLER;
		} 
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type.toString();
	}

}
