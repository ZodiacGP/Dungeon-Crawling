package dungeon;

import java.util.Map;

import static dungeon.IO.*;

public class Game {
	private static Map<String, Room> dungeon;
	private static final String pathname = "src/main/resources/input.txt";

	public static void main(String[] args) {
		createDungeon(pathname);

		String[][] dungeonMap = getDungeonMap(dungeon);

		Room position = dungeon.get("a0");

		while (true) {
			printSeparator(dungeonMap);
			printMap(dungeon, position, dungeonMap);
			printCurrentPosition(position);
			printPossibleMoves(position);
			position = move(position);
		}
	}

	public static void createDungeon(String pathname) {
		dungeon = readFile(pathname);

		if (dungeon.isEmpty()) {
			throw new IllegalStateException("Empty dungeon");
		}
	}

}
