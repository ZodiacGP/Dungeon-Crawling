package dungeon;

import java.util.*;

public class Dungeon {
	private final Map<String, Vertex> doors = new HashMap<>();

	public void addVertex(String door, String[] ways) {
		doors.put(door, new Vertex(ways));
	}

	public String getPossibileMoves(String location) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Character, String> vertex : doors.get(location).getWays().entrySet()) {
			sb.append(vertex.getKey());
		}
		return sb.toString();
	}

	public String getDoor(String location, Character direction) {
		return doors.get(location).getWays().get(direction);
	}

	public int numberOfDoors() {
		return doors.size();
	}

	public void clearDungeon() {
		doors.clear();
	}
}