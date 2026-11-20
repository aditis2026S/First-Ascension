import student.TestCase;
import java.util.ArrayList;

/**
 * Tests for the CharacterCatalog class.
 */
public class CharacterCatalogTest extends TestCase {

    private CharacterCatalog catalog;

    /**
     * Sets up the character catalog before each test.
     */
    public void setUp() {
        catalog = new CharacterCatalog();
    }

    /**
     * Tests getting all characters from the catalog.
     */
    public void testGetAllCharacters() {
        ArrayList<Character> characters = catalog.getAllCharacters();

        assertNotNull(characters);
        assertFalse(characters.isEmpty());
    }

    /**
     * Tests finding a character that exists.
     */
    public void testFindCharacter() {
        Character expected = catalog.getAllCharacters().get(0);

        Character actual = catalog.findCharacter(expected.getName());

        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
    }

    /**
     * Tests finding a character that does not exist.
     */
    public void testFindNonexistentCharacter() {
        Character actual = catalog.findCharacter("Not a Character");

        assertNull(actual);
    }

    /**
     * Tests checking whether a character exists.
     */
    public void testContainsCharacter() {
        Character character = catalog.getAllCharacters().get(0);

        assertTrue(catalog.containsCharacter(character.getName()));
    }

    /**
     * Tests checking whether a nonexistent character exists.
     */
    public void testContainsNonexistentCharacter() {
        assertFalse(catalog.containsCharacter("Not a Character"));
    }

    /**
     * Tests checking whether a null character name exists.
     */
    public void testContainsNull() {
        assertFalse(catalog.containsCharacter(null));
    }
}