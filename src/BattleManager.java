/**
 * Manages the battle mechanics for First Ascension.
 *
 * @author Aditi Sambaraju
 * @version 2026-11-11
 */
public class BattleManager
{
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private Player winner;

    /**
     * Creates a BattleManager for two players.
     *
     * @param player1 the first player
     * @param player2 the second player
     */
    public BattleManager(Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = null;
        gameOver = false;
        winner = null;
    }

    /**
     * Starts the battle if both players have full, locked teams.
     */
    public void startBattle()
    {
        if (player1 == null || player2 == null
            || !player1.isTeamFull() || !player2.isTeamFull()
            || !player1.isTeamLocked() || !player2.isTeamLocked())
        {
            throw new IllegalStateException(
                "Both players must have full, locked teams.");
        }

        gameOver = false;
        winner = null;
        currentPlayer = determineFirstPlayer();
    }

    /**
     * Gets the character at the front of a player's team.
     *
     * @param player the player whose active character is requested
     * @return the active character, or null if the team is empty
     */
    public Character getActiveCharacter(Player player)
    {
        if (player == null || player.getTeam().isEmpty())
        {
            return null;
        }

        return player.getTeam().peek();
    }

    /**
     * Applies an attack from one character to another.
     *
     * @param attacker the attacking character
     * @param defender the defending character
     */
    public void attack(Character attacker, Character defender)
    {
        if (attacker == null || defender == null
            || !attacker.isAlive() || !defender.isAlive())
        {
            return;
        }

        int damage = calculateAttackDamage(attacker, defender);
        defender.takeDamage(damage);

        if (defender.isDefending())
        {
            defender.endDefense();
        }
    }

    /**
     * Activates a character's defensive stance.
     *
     * @param character the character choosing to defend
     */
    public void defend(Character character)
    {
        if (character != null && character.isAlive())
        {
            character.defend();
        }
    }

    /**
     * Calculates attack damage while accounting for defense.
     *
     * @param attacker the attacking character
     * @param defender the defending character
     * @return the amount of damage dealt
     */
    private int calculateAttackDamage(
        Character attacker, Character defender)
    {
        int damage = attacker.getAttack();

        if (defender.isDefending())
        {
            damage -= defender.getDefense();
        }

        return Math.max(1, damage);
    }

    /**
     * Removes defeated characters from their respective teams
     * and checks whether the battle has ended.
     */
    public void checkDefeatedCharacters()
    {
        removeDefeatedCharacters(player1);
        removeDefeatedCharacters(player2);

        if (player1.getTeam().isEmpty())
        {
            gameOver = true;
            winner = player2;
        }
        else if (player2.getTeam().isEmpty())
        {
            gameOver = true;
            winner = player1;
        }
    }

    /**
     * Removes defeated characters from the front of a player's queue.
     *
     * @param player the player whose team is checked
     */
    private void removeDefeatedCharacters(Player player)
    {
        while (!player.getTeam().isEmpty()
            && !player.getTeam().peek().isAlive())
        {
            player.getTeam().dequeue();
        }
    }

    /**
     * Determines who goes first based on active character speed.
     * Player 1 goes first if speeds are equal.
     *
     * @return the player who goes first, or null if a team is empty
     */
    public Player determineFirstPlayer()
    {
        Character character1 = getActiveCharacter(player1);
        Character character2 = getActiveCharacter(player2);

        if (character1 == null || character2 == null)
        {
            return null;
        }

        if (character1.getSpeed() >= character2.getSpeed())
        {
            return player1;
        }

        return player2;
    }

    /**
     * Switches to the other player's turn.
     */
    public void advanceTurn()
    {
        if (!gameOver && currentPlayer != null)
        {
            if (currentPlayer == player1)
            {
                currentPlayer = player2;
            }
            else
            {
                currentPlayer = player1;
            }
        }
    }

    /**
     * Gets the player whose turn it currently is.
     *
     * @return the current player
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Checks whether the battle has ended.
     *
     * @return true if either player's team is empty
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * Gets the winner of the battle.
     *
     * @return the winning player, or null if the battle has not ended
     */
    public Player getWinner()
    {
        return winner;
    }
}