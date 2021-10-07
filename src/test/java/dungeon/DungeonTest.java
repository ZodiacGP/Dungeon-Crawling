package dungeon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DungeonTest {

	@Test
	public void testAddVertexMethod() {
		Dungeon dungeon = new Dungeon();
		assertEquals(0, dungeon.numberOfDoors());
		dungeon.addVertex("a0", new String[]{"e:b1"});
		assertEquals(1, dungeon.numberOfDoors());
	}

	@Test
	public void testGetDoorMethod() {
		Dungeon dungeon = new Dungeon();
		String location = "a0";
		dungeon.addVertex(location, new String[]{"e:b1"});
		assertEquals("b1", dungeon.getDoor(location, 'e'));
	}

	@Test
	public void testGetPossibleMovesMethod() {
		Dungeon dungeon = new Dungeon();
		String location = "a0";
		dungeon.addVertex(location, new String[]{"e:b1"});
		assertEquals("e", dungeon.getPossibileMoves(location));
	}

	@Test
	public void testClearDungeonMethod() {
		Dungeon dungeon = new Dungeon();
		dungeon.addVertex("a0", new String[]{"e:b1"});
		assertEquals(1, dungeon.numberOfDoors());
		dungeon.clearDungeon();
		assertEquals(0, dungeon.numberOfDoors());
	}
}