import student.TestCase;

/**
 * Tests for the Player class.
 */
public class PlayerTest extends student.TestCase {

    private Player player;
    private Character a;
    private Character b;
    private Character c;
    private Character d;

    /**
     * Creates a fresh player and four distinct characters before each test.
     */
    public void setUp() {
        player = new Player("Alice");
        a = new Character("Knight", 5, 10, 4, 50);
        b = new Character("Archer", 8, 7, 2, 35);
        c = new Character("Mage", 6, 12, 1, 30);
        d = new Character("Tank", 2, 5, 9, 70);
    }

    // ---------- constructor / getName ----------

    /** Normal case: name is stored and returned. */
    public void testGetName() {
        assertEquals("Alice", player.getName());
    }

    /** Bad input: null name is rejected. */
    public void testConstructorNullName() {
        Exception thrown = null;
        try {
            new Player(null);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /** Bad input: empty name is rejected. */
    public void testConstructorEmptyName() {
        Exception thrown = null;
        try {
            new Player("");
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /** Bad input: blank (spaces only) name is rejected. */
    public void testConstructorBlankName() {
        Exception thrown = null;
        try {
            new Player("   ");
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    // ---------- getTeam ----------

    /** Bad input case: team is empty and unlocked before adding anything. */
    public void testGetTeamEmptyInitially() {
        assertNotNull(player.getTeam());
        assertTrue(player.getTeam().isEmpty());
        assertEquals(0, player.getTeam().size());
        assertFalse(player.isTeamLocked());
    }

    /** Normal case: team reflects added characters in FIFO order. */
    public void testGetTeamAfterAdding() {
        player.addCharacter(a);
        player.addCharacter(b);
        assertEquals(2, player.getTeam().size());
        assertSame(a, player.getTeam().peek());
    }

    // ---------- addCharacter ----------

    /** Normal case: a unique character is added. */
    public void testAddCharacterNormal() {
        assertTrue(player.addCharacter(a));
        assertEquals(1, player.getTeam().size());
    }

    /** Bad input: null is rejected. */
    public void testAddCharacterNull() {
        assertFalse(player.addCharacter(null));
        assertEquals(0, player.getTeam().size());
    }

    /** Bad input: the same character twice is rejected. */
    public void testAddCharacterDuplicateSameObject() {
        assertTrue(player.addCharacter(a));
        assertFalse(player.addCharacter(a));
        assertEquals(1, player.getTeam().size());
    }

    /** Bad input: a different object with the same name is rejected. */
    public void testAddCharacterDuplicateSameName() {
        Character copy = new Character("Knight", 1, 1, 1, 1);
        assertTrue(player.addCharacter(a));
        assertFalse(player.addCharacter(copy));
        assertEquals(1, player.getTeam().size());
    }

    /** Bad input: a fourth character is rejected. */
    public void testAddCharacterFourth() {
        assertTrue(player.addCharacter(a));
        assertTrue(player.addCharacter(b));
        assertTrue(player.addCharacter(c));
        assertFalse(player.addCharacter(d));
        assertEquals(3, player.getTeam().size());
    }

    /** Bad input: adding after locking is rejected. */
    public void testAddCharacterAfterLock() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        player.lockTeam();
        assertFalse(player.addCharacter(d));
        assertEquals(3, player.getTeam().size());
    }

    /** The duplicate check must not disturb the queue order. */
    public void testAddCharacterKeepsOrderAfterDuplicateCheck() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        player.addCharacter(a); // rejected as duplicate
        assertSame(a, player.getTeam().dequeue());
        assertSame(b, player.getTeam().dequeue());
        assertSame(c, player.getTeam().dequeue());
    }

    // ---------- removeCharacter ----------

    /** Normal case: removing before locking succeeds. */
    public void testRemoveCharacterNormal() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        assertTrue(player.removeCharacter(b));
        assertEquals(2, player.getTeam().size());
        assertSame(a, player.getTeam().dequeue());
        assertSame(c, player.getTeam().dequeue());
    }

    /** Bad input: removing a character that is not on the team. */
    public void testRemoveCharacterNotPresent() {
        player.addCharacter(a);
        assertFalse(player.removeCharacter(b));
        assertEquals(1, player.getTeam().size());
    }

    /** Bad input: removing null. */
    public void testRemoveCharacterNull() {
        player.addCharacter(a);
        assertFalse(player.removeCharacter(null));
        assertEquals(1, player.getTeam().size());
    }

    /** Bad input: removing from an empty team. */
    public void testRemoveCharacterEmptyTeam() {
        assertFalse(player.removeCharacter(a));
    }

    /** Bad input: removing after locking is rejected. */
    public void testRemoveCharacterAfterLock() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        player.lockTeam();
        assertFalse(player.removeCharacter(a));
        assertEquals(3, player.getTeam().size());
    }

    // ---------- isTeamFull ----------

    /** Normal case: three characters means full. */
    public void testIsTeamFullTrue() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        assertTrue(player.isTeamFull());
    }

    /** Bad input: empty team is not full. */
    public void testIsTeamFullEmpty() {
        assertFalse(player.isTeamFull());
    }

    /** Partially filled team is not full. */
    public void testIsTeamFullPartial() {
        player.addCharacter(a);
        player.addCharacter(b);
        assertFalse(player.isTeamFull());
    }

    // ---------- lockTeam / isTeamLocked ----------

    /** Normal case: a team of exactly three locks. */
    public void testLockTeamNormal() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        player.lockTeam();
        assertTrue(player.isTeamLocked());
    }

    /** Bad input: locking an empty team throws. */
    public void testLockTeamEmpty() {
        Exception thrown = null;
        try {
            player.lockTeam();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertFalse(player.isTeamLocked());
    }

    /** Bad input: locking a team with fewer than three throws. */
    public void testLockTeamIncomplete() {
        player.addCharacter(a);
        player.addCharacter(b);
        Exception thrown = null;
        try {
            player.lockTeam();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertFalse(player.isTeamLocked());
    }

    /** Normal case: a locked team reports locked. */
    public void testIsTeamLockedTrue() {
        player.addCharacter(a);
        player.addCharacter(b);
        player.addCharacter(c);
        player.lockTeam();
        assertTrue(player.isTeamLocked());
    }

    /** Bad input: an unlocked team reports not locked. */
    public void testIsTeamLockedFalse() {
        assertFalse(player.isTeamLocked());
    }
}
