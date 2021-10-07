package dungeon;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static dungeon.Game.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

	@Test
	public void testCreateGraphFromFileMethod() {
		createDungeonFromFile(getPathname());
		int counter = 0;
		try (Scanner scanner = new Scanner(new File(getPathname()))) {
			while (scanner.hasNextLine()) {
				counter++;
				scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		assertEquals(counter, getDungeon().numberOfDoors());
	}
}