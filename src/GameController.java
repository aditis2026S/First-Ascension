/**
 * Controls the flow of First Ascension.
 *
 * @author Aditi Sambaraju
 * @version 2026.11.12
 */
public class GameController {

    private GameView view;
    private CharacterCatalog characterCatalog;
    private BattleManager battleManager;
    private Player player1;
    private Player player2;

    /**
     * Creates the controller and initializes the view
     * and character catalog.
     */
    public GameController() {
        view = new GameView();
        characterCatalog = new CharacterCatalog();
    }

    /**
     * Starts the overall game.
     */
    public void startGame() {
        view.displayMainMenu();

        setupPlayers();

        selectCharacters(player1);
        confirmTeam(player1);

        selectCharacters(player2);
        confirmTeam(player2);

        battleManager = new BattleManager(player1, player2);

        runBattle();
    }

    /**
     * Creates and initializes both players.
     */
    public void setupPlayers() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    /**
     * Allows a player to select three characters.
     *
     * @param player the player selecting characters
     */
    public void selectCharacters(Player player) {
        while (!player.isTeamFull()) {
            view.displayCharacterCatalog(
                characterCatalog.getAllCharacters());

            String name = view.getCharacterChoice();

            if (!validateCharacterChoice(name)) {
                System.out.println("Invalid character. Try again.");
                continue;
            }

            Character selected =
                characterCatalog.findCharacter(name);

            if (!player.addCharacter(selected)) {
                System.out.println(
                    "Character already selected or team is full.");
            }
            else {
                view.displayTeam(player);
            }
        }
    }

    /**
     * Checks whether a character exists in the catalog.
     *
     * @param name the selected character name
     * @return true if the character exists
     */
    public boolean validateCharacterChoice(String name) {
        return characterCatalog.containsCharacter(name);
    }

    /**
     * Locks a player's team after three selections.
     *
     * @param player the player confirming their team
     */
    public void confirmTeam(Player player) {
        if (player.isTeamFull()) {
            player.lockTeam();
            view.displayTeam(player);
        }
        else {
            System.out.println(
                "You must select three characters first.");
        }
    }

    /**
     * Runs the battle until one player wins.
     */
    public void runBattle() {
        battleManager.startBattle();

        while (!battleManager.isGameOver()) {
            Player currentPlayer =
                battleManager.getCurrentPlayer();

            Character attacker =
                battleManager.getActiveCharacter(currentPlayer);

            Player opponent = (currentPlayer == player1)
                ? player2 : player1;

            Character defender =
                battleManager.getActiveCharacter(opponent);

            view.displayBattleStatus(player1, player2);
            view.displayBattleOptions();

            String move = view.getMoveChoice();

            processMove(currentPlayer, move);

            if (battleManager.isGameOver()) {
                break;
            }

            battleManager.advanceTurn();
        }

        Player winner = battleManager.getWinner();

        if (winner != null) {
            view.displayWinner(winner.getName());
        }
    }

    /**
     * Processes the player's selected battle move.
     *
     * @param player the player making the move
     * @param move the selected move
     */
    public void processMove(Player player, String move) {
        if (player != battleManager.getCurrentPlayer()) {
            return;
        }

        Character character =
            battleManager.getActiveCharacter(player);

        if (character == null || move == null) {
            System.out.println("Invalid move.");
            return;
        }

        if (move.equalsIgnoreCase("attack")) {
            Player opponent = (player == player1)
                ? player2 : player1;

            Character defender =
                battleManager.getActiveCharacter(opponent);

            battleManager.attack(character, defender);

            battleManager.checkDefeatedCharacters();
        }
        else if (move.equalsIgnoreCase("defend")) {
            battleManager.defend(character);
        }
        else {
            System.out.println(
                "Invalid move. Choose attack or defend.");
        }
    }

    /**
     * Creates the controller and starts the game.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.startGame();
    }
}
