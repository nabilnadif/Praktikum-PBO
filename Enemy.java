import java.util.Random;

public class Enemy extends Character {
    public static final Enemy[] DAFTAR_ENEMY = {
        new Enemy("Goblin", 65, 10, 2),
        new Enemy("Orc", 95, 15, 5),
        new Enemy("Dragon", 125, 25, 8)
    };

    public Enemy(String nama, int hp, int attackPower, int defense) {
        super(nama, hp, attackPower, defense);
    }

    private static final Random acak = new Random();
    public static Enemy generateRandomEnemy() {
        int index = acak.nextInt(DAFTAR_ENEMY.length);
        return DAFTAR_ENEMY[index];
    }

    public static boolean shouldUseSkill() {
        return acak.nextInt(100) >= 80;
    }

    @Override
    public void attack(Character target) {
        System.out.println(getNama() + " menyerang balik dengan kekuatan basic attack!");
        target.takeDamage(getAttackPower());
    }

    @Override
    public void useSkill(Character target) {
        System.out.println(getNama() + " membalas dengan menyerang dengan kekuatan penuh!");
        target.takeDamage(getAttackPower() + 5);
    }
}