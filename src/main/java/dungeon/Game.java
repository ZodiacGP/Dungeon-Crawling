package dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
	private static final Player player = new Player();
	private static final Dungeon dungeon = new Dungeon();
	private static final String pathname = "src/main/resources/input.txt";

	public static String getPathname() {
		return pathname;
	}

	public static Dungeon getDungeon() {
		return dungeon;
	}

	public static void main(String[] args) {
		createDungeonFromFile(pathname);

		while (true) {
			printSeparator();
			printPlayerLocation();
			String possibilities = dungeon.getPossibileMoves(player.getLocation());
			printPossibleMoves();
			System.out.print("Your choice: ");
			String[] input = inputDirection();
			String currentLocation = player.getLocation();

			while (!checkInput(input, possibilities)) {
				System.out.print("Incorrect input. Try again: ");
				player.enterTheDoor(currentLocation);
				input = inputDirection();
			}
		}
	}

	public static void createDungeonFromFile(String pathname) {
		try (Scanner scanner = new Scanner(new File(pathname))) {
			if (dungeon.numberOfDoors() != 0) {
				dungeon.clearDungeon();
			}
			while (scanner.hasNext()) {
				String door = scanner.next();
				String[] ways = scanner.nextLine().trim().split(" ");
				dungeon.addVertex(door, ways);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	private static String[] inputDirection() {
		return new Scanner(System.in).next().trim().toLowerCase().split("");
	}

	private static boolean checkInput(String[] input, String possibilities) {
		for (String d : input) {
			if (!d.equals("n") && !d.equals("s") && !d.equals("e") && !d.equals("w")) {
				return false;
			}
			if (!possibilities.contains(d)) {
				return false;
			}
			player.enterTheDoor(dungeon.getDoor(player.getLocation(), d.charAt(0)));
			possibilities = dungeon.getPossibileMoves(player.getLocation());
		}
		return true;
	}

	private static void printPlayerLocation() {
		System.out.println("You are in room " + player.getLocation());
	}

	private static void printPossibleMoves() {
		System.out.println("Possible moves: " + dungeon.getPossibileMoves(player.getLocation()));
	}

	private static void printSeparator() {
		System.out.println("---------------------------");
	}
}
