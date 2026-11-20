/**
 * Controls the flow of First Ascension.
 *
 * @author Aditi Sambaraju
 * @version 2026.11.12
 */
import java.util.ArrayList;
import java.util.Collections;
// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author aditisambaraju
 *  @version Nov 20, 2026
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
    public void startGame()
    {
        boolean running = true;

        while (running)
        {
            view.displayMainMenu();
            String choice = view.getMainMenuChoice();

            if (choice.equals("1"))
            {
                setupPlayers();

                chooseCharacters(player1);
                confirmTeam(player1);

                chooseCharacters(player2);
                confirmTeam(player2);

                battleManager = new BattleManager(player1, player2);
                runBattle();

                running = false;
            }
            else if (choice.equals("2"))
            {
                System.out.println("Thanks for playing First Ascension!");
                running = false;
            }
            else
            {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }
    
    public void chooseCharacters(Player player)
    {
        boolean choosing = true;

        while (choosing)
        {
            view.displaySelectionMenu();
            String choice = view.getSelectionMode();

            if (choice.equals("1"))
            {
                selectCharacters(player);
                choosing = false;
            }
            else if (choice.equals("2"))
            {
                randomlySelectCharacters(player);
                choosing = false;
            }
            else
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }
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
            System.out.println("\n" + player.getName()
            + " - Choose your characters");

            System.out.println("Characters selected: "
            + player.getTeam().size() + "/3");
        
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
     * Randomly selects three characters for a player.
     *
     * @param player the player receiving random characters
     */
    public void randomlySelectCharacters(Player player) {
        if (player == null || player.isTeamLocked()
            || player.getTeam().size() != 0) {
            System.out.println("Cannot randomly select characters.");
            return;
        }

        ArrayList<Character> available =
            new ArrayList<>(characterCatalog.getAllCharacters());

        if (available.size() < 3) {
            System.out.println(
                "Not enough characters available.");
            return;
        }

        Collections.shuffle(available);

        for (int i = 0; i < 3; i++) {
            player.addCharacter(available.get(i));
        }

        view.displayTeam(player);
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
            
            System.out.println(
                currentPlayer.getName() + "'s turn!");

            view.displayBattleStatus(player1, player2);
            view.displayBattleOptions();

            String move = view.getMoveChoice();

            boolean moveProcessed = processMove(currentPlayer, move);

            if (battleManager.isGameOver())
            {
                break;
            }

            if (moveProcessed)
            {
                battleManager.advanceTurn();
            }
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
    public boolean processMove(Player player, String move)
    {
        if (player != battleManager.getCurrentPlayer())
        {
            return false;
        }

        Character character =
            battleManager.getActiveCharacter(player);

        if (character == null || move == null)
        {
            System.out.println("Invalid move.");
            return false;
        }

        if (move.equalsIgnoreCase("attack") || move.equals("1"))
        {
            Player opponent = (player == player1)
                ? player2 : player1;

            Character defender =
                battleManager.getActiveCharacter(opponent);

            battleManager.attack(character, defender);
            battleManager.checkDefeatedCharacters();

            return true;
        }
        else if (move.equalsIgnoreCase("defend") || move.equals("2"))
        {
            battleManager.defend(character);
            return true;
        }
        else
        {
            System.out.println(
                "Invalid move. Choose 1 (Attack) or 2 (Defend).");
            return false;
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
