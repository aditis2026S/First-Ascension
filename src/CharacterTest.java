import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Character class.
 */
public class CharacterTest {

    private Character character;

    /**
     * Creates a character before each test.
     */
    @BeforeEach
    public void setUp() {
        character = new Character("Jester", 20, 15, 5, 100);
    }

    /**
     * Tests the constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals("Jester", character.getName());
        assertEquals(20, character.getSpeed());
        assertEquals(15, character.getAttack());
        assertEquals(5, character.getDefense());
        assertEquals(100, character.getMaxHealth());
        assertEquals(100, character.getHealth());
        assertTrue(character.isAlive());
        assertFalse(character.isDefending());
    }

    /**
     * Tests taking damage.
     */
    @Test
    public void testTakeDamage() {
        character.takeDamage(30);

        assertEquals(70, character.getHealth());
        assertTrue(character.isAlive());
    }

    /**
     * Tests that health cannot go below zero.
     */
    @Test
    public void testTakeDamageBelowZero() {
        character.takeDamage(150);

        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }

    /**
     * Tests taking zero damage.
     */
    @Test
    public void testTakeZeroDamage() {
        character.takeDamage(0);

        assertEquals(100, character.getHealth());
        assertTrue(character.isAlive());
    }

    /**
     * Tests the defend method.
     */
    @Test
    public void testDefend() {
        character.defend();

        assertTrue(character.isDefending());
    }

    /**
     * Tests ending the defense state.
     */
    @Test
    public void testEndDefense() {
        character.defend();
        character.endDefense();

        assertFalse(character.isDefending());
    }

    /**
     * Tests that a character with zero health is not alive.
     */
    @Test
    public void testIsAlive() {
        assertTrue(character.isAlive());

        character.takeDamage(100);

        assertFalse(character.isAlive());
    }
}