import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest
{
    // ~ Fields ................................................................
    private Character hero;

    // ~ Setup .................................................................
    @BeforeEach
    public void setUp()
    {
        hero = new Character("Hero", 10, 15, 5, 100);

    }


    @Test
    public void testStartsAtFullHealth()
    {
        assertEquals(100, hero.getHealth());
        assertEquals(hero.getMaxHealth(), hero.getHealth());
    }


    @Test
    public void testStartsNotDefending()
    {
        assertFalse(hero.isDefending());
    }


    @Test
    public void testStartsAlive()
    {
        assertTrue(hero.isAlive());
    }


    @Test
    public void testTakeDamage()
    {
        hero.takeDamage(30);
        assertEquals(70, hero.getHealth());
        assertEquals(100, hero.getMaxHealth());
        assertTrue(hero.isAlive());
    }


    @Test
    public void testTakeDamageMultipleTimes()
    {
        hero.takeDamage(20);
        hero.takeDamage(35);
        assertEquals(45, hero.getHealth());
    }


    @Test
    public void testTakeZeroDamage()
    {
        hero.takeDamage(0);
        assertEquals(100, hero.getHealth());
        assertTrue(hero.isAlive());
    }


    @Test
    public void testTakeDamageExactlyLethal()
    {
        hero.takeDamage(100);
        assertEquals(0, hero.getHealth());
        assertFalse(hero.isAlive());
    }


    @Test
    public void testConstructorAndGetters()
    {
        assertEquals("Hero", hero.getName());
        assertEquals(10, hero.getSpeed());
        assertEquals(15, hero.getAttack());
        assertEquals(5, hero.getDefense());
        assertEquals(100, hero.getMaxHealth());
    }

}
