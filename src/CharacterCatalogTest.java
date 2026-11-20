import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the CharacterCatalog class.
 */
public class CharacterCatalogTest {

    private CharacterCatalog catalog;

    /**
     * Sets up the character catalog before each test.
     */
    @BeforeEach
    public void setUp() {
        catalog = new CharacterCatalog();
    }

    /**
     * Tests getting all characters from the catalog.
     */
    @Test
    public void testGetAllCharacters() {
        ArrayList<Character> characters = catalog.getAllCharacters();

        assertNotNull(characters);
        assertFalse(characters.isEmpty());
    }

    /**
     * Tests finding a character that exists.
     */
    @Test
    public void testFindCharacter() {
        Character expected = catalog.getAllCharacters().get(0);

        Character actual = catalog.findCharacter(expected.getName());

        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
    }

    /**
     * Tests finding a character that does not exist.
     */
    @Test
    public void testFindNonexistentCharacter() {
        Character actual = catalog.findCharacter("Not a Character");

        assertNull(actual);
    }

    /**
     * Tests checking whether a character exists.
     */
    @Test
    public void testContainsCharacter() {
        Character character = catalog.getAllCharacters().get(0);

        assertTrue(catalog.containsCharacter(character.getName()));
    }

    /**
     * Tests checking whether a nonexistent character exists.
     */
    @Test
    public void testContainsNonexistentCharacter() {
        assertFalse(catalog.containsCharacter("Not a Character"));
    }

    /**
     * Tests checking whether a null character name exists.
     */
    @Test
    public void testContainsNull() {
        assertFalse(catalog.containsCharacter(null));
    }
}