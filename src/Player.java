/**
 * Model class representing one player in the battle game.
 * A player owns a name and a team of exactly three characters stored in a
 * LinkedQueue (FIFO order). Once the team is locked it can no longer change.
 */
public class Player {

    /** Number of characters required on a full team. */
    private static final int TEAM_SIZE = 3;

    private final String name;
    private final LinkedQueue<Character> team;
    private boolean teamLocked;

    /**
     * Creates a player with an empty, unlocked team.
     *
     * @param name
     *            the player's name
     * @throws IllegalArgumentException
     *             if the name is null or empty
     */
    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Player name cannot be null or empty.");
        }
        this.name = name;
        this.team = new LinkedQueue<Character>();
        this.teamLocked = false;
    }

    /**
     * Returns the player's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's character queue.
     *
     * @return the team queue (empty if no characters have been added)
     */
    public LinkedQueue<Character> getTeam() {
        return team;
    }

    /**
     * Adds a character to the team if the selection is valid.
     *
     * @param character
     *            the character to add
     * @return true if added; false if the character is null, a duplicate,
     *         the team is full, or the team is locked
     */
    public boolean addCharacter(Character character) {
        if (character == null || teamLocked || isTeamFull()
            || containsCharacter(character)) {
            return false;
        }
        team.enqueue(character);
        return true;
    }

    /**
     * Removes a character from the team.
     *
     * @param character
     *            the character to remove
     * @return true if removed; false if the character is null, not on the
     *         team, or the team is locked
     */
    public boolean removeCharacter(Character character) {
        if (character == null || teamLocked) {
            return false;
        }
        return team.remove(character);
    }

    /**
     * Checks whether the player has selected three characters.
     *
     * @return true if the team has exactly three characters
     */
    public boolean isTeamFull() {
        return team.size() == TEAM_SIZE;
    }

    /**
     * Locks the team after confirmation.
     *
     * @throws IllegalStateException
     *             if the team does not contain exactly three characters
     */
    public void lockTeam() {
        if (team.size() != TEAM_SIZE) {
            throw new IllegalStateException(
                "A team must contain exactly " + TEAM_SIZE
                + " characters before it can be locked.");
        }
        teamLocked = true;
    }

    /**
     * Checks whether the player's team is locked.
     *
     * @return true if locked
     */
    public boolean isTeamLocked() {
        return teamLocked;
    }

    /**
     * Checks whether a character (matched by name) is already on the team.
     * Cycles through the queue once so the original FIFO order is preserved.
     */
    private boolean containsCharacter(Character character) {
        boolean found = false;
        int count = team.size();
        for (int i = 0; i < count; i++) {
            Character current = team.dequeue();
            if (current.getName().equals(character.getName())) {
                found = true;
            }
            team.enqueue(current);
        }
        return found;
    }
}
