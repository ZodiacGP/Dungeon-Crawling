package dungeon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

	@Test
	public void testCreation() {
		assertEquals("a0", new Player().getLocation());
	}

	@Test
	public void testEnterTheDoorMethod() {
		Player player = new Player();
		player.enterTheDoor("b2");
		assertEquals("b2", player.getLocation());
	}
}