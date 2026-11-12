import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles console input and output for First Ascension.
 */
public class GameView
{
    private Scanner scanner;

    /**
     * Creates the game view.
     */
    public GameView()
    {
        scanner = new Scanner(System.in);
    }

    public void displayMainMenu()
    {
        System.out.println("=== FIRST ASCENSION ===");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");
    }

    public void displayCharacterCatalog(
        ArrayList<Character> characters)
    {
        System.out.println("\n=== CHARACTER CATALOG ===");

        for (Character character : characters)
        {
            System.out.println(character.getName());
        }
    }

    public void displayTeam(Player player)
    {
        System.out.println("\n" + player.getName() + "'s Team:");

        System.out.println("Characters selected: "
            + player.getTeam().size());

        if (!player.getTeam().isEmpty())
        {
            System.out.println("Front character: "
                + player.getTeam().peek().getName());
        }
    }

    public void displayCharacterStats(Character character)
    {
        System.out.println("\n=== CHARACTER STATS ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Speed: " + character.getSpeed());
        System.out.println("Attack: " + character.getAttack());
        System.out.println("Defense: " + character.getDefense());
        System.out.println("Health: " + character.getHealth());
    }

    public void displayBattleStatus(Player player1,
        Player player2)
    {
        System.out.println("\n=== BATTLE STATUS ===");

        displayTeam(player1);
        displayTeam(player2);
    }

    public void displayBattleOptions()
    {
        System.out.println("\nChoose your move:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
    }

    public void displayWinner(String winner)
    {
        System.out.println("\nWinner: " + winner);
    }

    public String getCharacterChoice()
    {
        System.out.print("Choose a character: ");
        return scanner.nextLine();
    }

    public String getMoveChoice()
    {
        System.out.print("Enter your move: ");
        return scanner.nextLine();
    }
}

