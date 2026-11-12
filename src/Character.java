public class Character
{
    // ~ Fields ................................................................
    private String name;
    private int speed;
    private int attack;
    private int defense;
    private int maxHealth;
    private int currentHealth;
    private boolean defending;

    // ~ Constructors ..........................................................
    public Character(
        String name,
        int speed,
        int attack,
        int defense,
        int maxHealth)
    {
        this.name = name;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;

        currentHealth = maxHealth;
        defending = false;
    }
    // ~Public Methods ........................................................


    public String getName()
    {
        return name;
    }


    public int getSpeed()
    {
        return speed;
    }
    
    public int getAttack()
    {
        return attack;
    }
    
    public int getDefense()
    {
        return defense;
    }
    
    public int getHealth()
    {
        return currentHealth;
    }
    
    public void takeDamage(int damage)
    {
        currentHealth = currentHealth - damage;
    }
    
    public boolean isAlive()
    {
        return currentHealth != 0;
    }
    
    public void defend()
    {
        defending = true;
    }
    
    public boolean isDefending()
    {
        return defending;
    }
    
    public void endDefense()
    {
        defending = false;
    }
    
}
