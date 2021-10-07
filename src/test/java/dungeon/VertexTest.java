package dungeon;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

	@Test
	public void testParsing() {
		Vertex vertex = new Vertex(new String[]{"w:a6"});
		assertEquals(new LinkedHashMap<>(Map.of('w', "a6")), vertex.getWays());
	}
}