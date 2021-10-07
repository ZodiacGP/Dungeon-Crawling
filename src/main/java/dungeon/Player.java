package dungeon;

public class Player {
	private String location = "a0";

	public String getLocation() {
		return location;
	}

	public void enterTheDoor(String door) {
		location = door;
	}
}
