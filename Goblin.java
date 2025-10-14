public class Goblin extends Enemy {
    private int attackCounter;

    // Constructor Goblin memanggil constructor dari superclass(Enemy)
    public Goblin(int hp, int baseDamage) {
        super(hp, baseDamage);
        this.attackCounter = 0; 
    }

    // Override method attack() dari class Enemy
    @Override
    public void attack(Player target) {
        this.attackCounter++;

        if (this.attackCounter % 3 == 0) {
            int doubleDamage = getBaseDamage() * 2;
            System.out.println("SPECIAL ATTACK! Goblin menyerang Player dengan damage ganda " + doubleDamage + "!");
            target.setHp(target.getHp() - doubleDamage);
        } else { 
            int normalDamage = getBaseDamage();
            System.out.println("Goblin menyerang Player dengan damage " + normalDamage + "!");
            target.setHp(target.getHp() - normalDamage);
        }
    }
}