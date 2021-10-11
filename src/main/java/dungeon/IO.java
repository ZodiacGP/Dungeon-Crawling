package dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class IO {
	private static int left = 1, right = 1, up = 1, down = 1;

	public static Map<String, Room> readFile(String pathname) {

		try (Scanner file = new Scanner(new File(pathname))) {
			Map<String, Room> rooms = new HashMap<>();

			while (file.hasNext()) {
				String currentRoomName = file.next();

				Room currentRoom = rooms.getOrDefault(currentRoomName, new Room(currentRoomName));

				String[] ways = file.nextLine().trim().split(" ");

				for (String edge : ways) {
					char direction = edge.charAt(0);
					String neighbourRoomName = edge.substring(2);

					Room neighbour = rooms.get(neighbourRoomName);
					if (neighbour == null) {
						neighbour = new Room(neighbourRoomName);
						rooms.put(neighbourRoomName, neighbour);
					}

					switch (direction) {
						case 'n' -> currentRoom.setN(neighbour);
						case 's' -> currentRoom.setS(neighbour);
						case 'e' -> currentRoom.setE(neighbour);
						case 'w' -> currentRoom.setW(neighbour);
					}

					rooms.put(currentRoom.getName(), currentRoom);
				}
			}
			return rooms;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		return Collections.emptyMap();
	}

	public static String[][] getDungeonMap(Map<String, Room> dungeon) {
		int[] dimensions = extractDimensions(dungeon);
		printDungeonSize(dimensions);
		String[][] dungeonMap = new String[dimensions[0]][dimensions[1]];

		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				dungeonMap[i][j] = "  ";
			}
		}

		return dungeonMap;
	}

	private static void printDungeonSize(int[] dimensions) {
		System.out.println("Dungeon is of " + dimensions[0] + "x" + dimensions[1]);
	}

	public static int[] extractDimensions(Map<String, Room> dungeon) {
		Room position = dungeon.get(dungeon.keySet().iterator().next());

		traverse(dungeon, position, 1, 1, new HashSet<>());

		int[] dimensions = new int[2];
		dimensions[0] = Math.abs(up - down) + 1;
		dimensions[1] = Math.abs(right - left) + 1;

		if (dimensions[0] > 20 || dimensions[1] > 20) {
			throw new IllegalStateException("Map size is too big");
		}

		return dimensions;
	}

	public static void traverse(Map<String, Room> dungeon, Room position, int row, int column, Set<Room> visited) {
		if (position == null || visited.contains(position)) return;
		if (row < up) up = row;
		if (row > down) down = row;
		if (column < left) left = column;
		if (column > right) right = column;

		visited.add(position);

		position.setRow(row);
		position.setCol(column);
		dungeon.put(position.getName(), position);

		traverse(dungeon, position.getN(), row - 2, column, visited);
		traverse(dungeon, position.getS(), row + 2, column, visited);
		traverse(dungeon, position.getE(), row, column + 2, visited);
		traverse(dungeon, position.getW(), row, column - 2, visited);

	}

	public static void printSeparator(String[][] dungeonMap) {
		int width = dungeonMap[0].length;
		IntStream.rangeClosed(0, width).forEach(i -> System.out.print("--"));
		System.out.println();
	}

	public static void printMap(Map<String, Room> dungeon, Room position, String[][] dungeonMap) {
		dungeon.forEach((k, v) -> {
			int row = v.getRow(), col = v.getCol();
			row = Math.abs(up - row);
			col = Math.abs(left - col);

			if (v != position) dungeonMap[row][col] = v.getName();
			else dungeonMap[row][col] = "**";

			if (v.getE() != null) {
				dungeonMap[row][col + 1] = "--";
			}

			if (v.getW() != null) {
				dungeonMap[row][col - 1] = "--";
			}

			if (v.getN() != null) {
				dungeonMap[row - 1][col] = " |";
			}

			if (v.getS() != null) {
				dungeonMap[row + 1][col] = " |";
			}
		});


		for (String[] strings : dungeonMap) {
			for (int j = 0; j < dungeonMap[0].length; j++) {
				System.out.print(strings[j]);
			}
			System.out.println();
		}

		System.out.println();
	}

	public static void printCurrentPosition(Room position) {
		System.out.println("You are in room " + position);
	}

	public static void printPossibleMoves(Room position) {
		System.out.print("Possible moves: " + getPossibleMoves(position) + '\n');
	}

	private static String getPossibleMoves(Room position) {
		StringBuilder possibleMoves = new StringBuilder();
		if (position.getE() != null) {
			possibleMoves.append("e");
		}

		if (position.getW() != null) {
			possibleMoves.append("w");
		}

		if (position.getN() != null) {
			possibleMoves.append("n");
		}

		if (position.getS() != null) {
			possibleMoves.append("s");
		}

		return possibleMoves.toString();
	}

	public static Room move(Room position) {
		System.out.print("Your choice: ");
		char[] input = inputDirection();
		Room startPosition = position;
		position = checkInput(input, position);

		while (position.equals(startPosition)) {
			System.out.print("Incorrect input. Try again: ");
			input = inputDirection();
			position = checkInput(input, position);
		}

		return position;
	}

	private static char[] inputDirection() {
		return new Scanner(System.in).nextLine().trim().toLowerCase().toCharArray();
	}

	private static Room checkInput(char[] input, Room position) {
		Room startPosition = position;
		String possibileMoves = getPossibleMoves(position);

		for (char d : input) {
			if (d != 'n' && d != 's' && d != 'e' && d != 'w') {
				return startPosition;
			}
			if (!possibileMoves.contains(String.valueOf(d))) {
				return startPosition;
			}
			position = switch (d) {
				case 'n' -> position.getN();
				case 's' -> position.getS();
				case 'e' -> position.getE();
				case 'w' -> position.getW();
				default -> null;
			};

			if (position == null) {
				return startPosition;
			}
			possibileMoves = getPossibleMoves(position);
		}
		return position;
	}
}
