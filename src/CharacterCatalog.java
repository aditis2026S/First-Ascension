/**
 * Stores all characters available for selection in First Ascension.
 *
 * @author Aditi Sambaraju
 * @version 2026.11.10
 */
public class CharacterCatalog {

    private ArrayList<Character> characters;

    /**
     * Creates the character catalog and initializes all available characters.
     */
    public CharacterCatalog() {
        characters = new ArrayList<Character>();

        // Original characters
        characters.add(new Character("The Conqueror", 7, 9, 6, 100));
        characters.add(new Character("The Warden", 5, 6, 10, 115));
        characters.add(new Character("The Phantom", 10, 8, 5, 95));
        characters.add(new Character("The Harbinger", 8, 9, 5, 95));
        characters.add(new Character("The Ascendant", 7, 7, 8, 105));
        characters.add(new Character("The Worldbreaker", 5, 10, 6, 100));
        characters.add(new Character("The Stormbringer", 9, 7, 6, 100));
        characters.add(new Character("The Unbroken", 5, 6, 9, 115));
        characters.add(new Character("The Blob", 5, 6, 10, 115));
        characters.add(new Character("The Cat", 10, 7, 5, 95));
        characters.add(new Character("The Wind", 10, 8, 5, 95));
        characters.add(new Character("The Reaper", 7, 10, 5, 95));
        characters.add(new Character("The Titan", 5, 8, 9, 115));
        characters.add(new Character("The Trickster", 9, 7, 6, 100));
        characters.add(new Character("The Phoenix", 8, 8, 6, 105));
        characters.add(new Character("The Serpent", 8, 9, 5, 95));
        characters.add(new Character("The Oracle", 7, 6, 9, 110));
        characters.add(new Character("The Berserker", 6, 10, 5, 95));
        characters.add(new Character("The Guardian", 5, 7, 10, 115));
        characters.add(new Character("The Eclipse", 9, 8, 6, 100));
        characters.add(new Character("The Frostborn", 6, 7, 9, 110));
        characters.add(new Character("The Monarch", 7, 8, 7, 105));
        characters.add(new Character("The Specter", 10, 7, 5, 95));
        characters.add(new Character("The Colossus", 5, 9, 9, 110));
        characters.add(new Character("The Inferno", 8, 10, 5, 95));
        characters.add(new Character("The Wanderer", 8, 7, 7, 105));
        characters.add(new Character("The Leviathan", 6, 9, 8, 110));
        characters.add(new Character("The Jester", 9, 6, 7, 100));
    }

    /**
     * Returns all available characters in the catalog.
     *
     * @return a copy of the character list
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<Character>(characters);
    }

    /**
     * Finds a character by name.
     *
     * @param name the character's name
     * @return the matching character, or null if not found
     */
    public Character findCharacter(String name) {
        if (name == null) {
            return null;
        }

        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }

        return null;
    }

    /**
     * Checks whether a character exists in the catalog.
     *
     * @param name the character's name
     * @return true if the character exists, false otherwise
     */
    public boolean containsCharacter(String name) {
        return findCharacter(name) != null;
    }
}
