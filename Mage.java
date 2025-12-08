import java.util.Random;
public class Mage extends Hero {
    Random rand = new Random();

    public Mage(String nama) {
        super(nama, 105, 25, 3);
    }

    @Override
    public void attack(Character target) {
        System.out.println(getNama() + " memberikan serangan jarak jauh dengan ilmu hitam!");
        target.takeDamage(getAttackPower());
    }

    @Override
    public void useSkill(Character target) {
        if (!skillUsed) {
            if (rand.nextInt(100) < 70) {
                System.out.println(getNama() + " melempar Fireball! Fireball mengenai target!");
                target.takeDamage(getAttackPower() + 20);
            } else {
                System.out.println(getNama() + " Serangan Fireball meleset!");
            }
            skillUsed = true;
        } else {
            System.out.println("Ultimate sudah digunakan!");
        }
    }
}
