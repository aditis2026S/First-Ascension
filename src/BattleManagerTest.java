import student.TestCase;

/**
 * Tests for the BattleManager class.
 */
public class BattleManagerTest extends TestCase {

    private BattleManager battleManager;
    private Player player1;
    private Player player2;
    private Character character1;
    private Character character2;
    private Character character3;
    private Character character4;
    private Character character5;
    private Character character6;

    /**
     * Sets up players and characters before each test.
     */
    public void setUp() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        character1 = new Character("Jester", 20, 15, 5, 100);
        character2 = new Character("Guardian", 10, 12, 8, 100);
        character3 = new Character("Blob", 15, 10, 4, 100);

        character4 = new Character("Wind", 12, 14, 6, 100);
        character5 = new Character("Cat", 18, 11, 5, 100);
        character6 = new Character("Knight", 8, 13, 9, 100);

        player1.addCharacter(character1);
        player1.addCharacter(character2);
        player1.addCharacter(character3);

        player2.addCharacter(character4);
        player2.addCharacter(character5);
        player2.addCharacter(character6);

        player1.lockTeam();
        player2.lockTeam();

        battleManager = new BattleManager(player1, player2);
    }

    /**
     * Tests starting a battle with two full, locked teams.
     */
    public void testStartBattle() {
        battleManager.startBattle();

        assertNotNull(battleManager.getCurrentPlayer());
        assertFalse(battleManager.isGameOver());
        assertNull(battleManager.getWinner());
    }

    /**
     * Tests starting a battle with incomplete teams.
     */
    public void testStartBattleIncompleteTeams() {
        Player incompletePlayer = new Player("Incomplete");
        incompletePlayer.addCharacter(
            new Character("Solo", 10, 10, 5, 100));

        BattleManager manager =
            new BattleManager(incompletePlayer, player2);

        try {
            manager.startBattle();
            fail("Expected IllegalStateException.");
        }
        catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests retrieving the active character.
     */
    public void testGetActiveCharacter() {
        assertEquals(character1,
            battleManager.getActiveCharacter(player1));
    }

    /**
     * Tests retrieving the active character from an empty team.
     */
    public void testGetActiveCharacterEmptyTeam() {
        Player emptyPlayer = new Player("Empty");

        assertNull(battleManager.getActiveCharacter(emptyPlayer));
    }

    /**
     * Tests attacking an opponent.
     */
    public void testAttack() {
        int originalHealth = character4.getHealth();

        battleManager.attack(character1, character4);

        assertEquals(originalHealth - character1.getAttack(),
            character4.getHealth());
    }

    /**
     * Tests attacking with a null attacker.
     */
    public void testAttackNullAttacker() {
        int originalHealth = character4.getHealth();

        battleManager.attack(null, character4);

        assertEquals(originalHealth, character4.getHealth());
    }

    /**
     * Tests attacking with a defeated attacker.
     */
    public void testAttackDefeatedAttacker() {
        character1.takeDamage(100);

        int originalHealth = character4.getHealth();

        battleManager.attack(character1, character4);

        assertEquals(originalHealth, character4.getHealth());
    }

    /**
     * Tests that defense reduces incoming damage.
     */
    public void testAttackAgainstDefendingCharacter() {
        battleManager.defend(character4);

        int originalHealth = character4.getHealth();
        int expectedDamage = character1.getAttack()
            - character4.getDefense();

        if (expectedDamage < 1) {
            expectedDamage = 1;
        }

        battleManager.attack(character1, character4);

        assertEquals(originalHealth - expectedDamage,
            character4.getHealth());
        assertFalse(character4.isDefending());
    }

    /**
     * Tests activating a defensive move.
     */
    public void testDefend() {
        battleManager.defend(character1);

        assertTrue(character1.isDefending());
    }

    /**
     * Tests defending with null.
     */
    public void testDefendNull() {
        battleManager.defend(null);

        assertFalse(character1.isDefending());
    }

    /**
     * Tests removing a defeated front character.
     */
    public void testCheckDefeatedCharacters() {
        character1.takeDamage(100);

        battleManager.checkDefeatedCharacters();

        assertEquals(character2,
            player1.getTeam().peek());
    }

    /**
     * Tests checking teams when no characters are defeated.
     */
    public void testCheckDefeatedCharactersNoneDefeated() {
        int originalSize = player1.getTeam().size();

        battleManager.checkDefeatedCharacters();

        assertEquals(originalSize, player1.getTeam().size());
        assertEquals(character1, player1.getTeam().peek());
    }

    /**
     * Tests that the faster character's player goes first.
     */
    public void testDetermineFirstPlayer() {
        assertEquals(player1, battleManager.determineFirstPlayer());
    }

    /**
     * Tests that Player 1 goes first when speeds are equal.
     */
    public void testDetermineFirstPlayerTie() {
        Character fastTieCharacter =
            new Character("Tie", 20, 10, 5, 100);

        Player tiedPlayer = new Player("Tied Player");
        tiedPlayer.addCharacter(fastTieCharacter);
        tiedPlayer.addCharacter(
            new Character("Second", 10, 10, 5, 100));
        tiedPlayer.addCharacter(
            new Character("Third", 10, 10, 5, 100));
        tiedPlayer.lockTeam();

        BattleManager tiedManager =
            new BattleManager(player1, tiedPlayer);

        assertEquals(player1, tiedManager.determineFirstPlayer());
    }

    /**
     * Tests advancing to the other player's turn.
     */
    public void testAdvanceTurn() {
        battleManager.startBattle();

        Player firstPlayer = battleManager.getCurrentPlayer();

        battleManager.advanceTurn();

        assertNotSame(firstPlayer, battleManager.getCurrentPlayer());
    }

    /**
     * Tests retrieving the current player before battle starts.
     */
    public void testGetCurrentPlayerBeforeBattle() {
        assertNull(battleManager.getCurrentPlayer());
    }

    /**
     * Tests that the game is not over during an active battle.
     */
    public void testIsGameOver() {
        battleManager.startBattle();

        assertFalse(battleManager.isGameOver());
    }

    /**
     * Tests that the game ends when one team is empty.
     */
    public void testIsGameOverEmptyTeam() {
        character1.takeDamage(100);
        character2.takeDamage(100);
        character3.takeDamage(100);

        battleManager.checkDefeatedCharacters();

        assertTrue(battleManager.isGameOver());
    }

    /**
     * Tests retrieving the winner after the battle ends.
     */
    public void testGetWinner() {
        character1.takeDamage(100);
        character2.takeDamage(100);
        character3.takeDamage(100);

        battleManager.checkDefeatedCharacters();

        assertEquals(player2, battleManager.getWinner());
    }

    /**
     * Tests retrieving the winner before the battle ends.
     */
    public void testGetWinnerBeforeGameOver() {
        assertNull(battleManager.getWinner());
    }
}