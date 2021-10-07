package dungeon;

import java.util.LinkedHashMap;
import java.util.Map;

public class Vertex {
	private final Map<Character, String> ways;

	public Vertex(String[] ways) {
		this.ways = new LinkedHashMap<>();
		for (String w : ways) {
			this.ways.put(w.charAt(0), w.substring(2));
		}
	}

	public Map<Character, String> getWays() {
		return new LinkedHashMap<>(ways);
	}
}
