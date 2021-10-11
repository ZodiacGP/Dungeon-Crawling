package dungeon;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IOTest {

	@Test
	public void testReadFileMethod() {
		assertEquals(Collections.emptyMap(), IO.readFile("imaginary.txt"));
	}

	@Test
	public void testExtractDimensionsMethod() {
		assertThrows(IllegalStateException.class, () -> IO.extractDimensions(IO.readFile("src/main/resources/maxSize.txt")));
	}
}