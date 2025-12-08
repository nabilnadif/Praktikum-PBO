public class Archer extends Hero {
    public Archer(String nama) {
        super(nama, 100, 18, 5);
    }

    @Override
    public void attack(Character target) {
        System.out.println(getNama() + " melepaskan anak panah berapi!");
        target.takeDamage(getAttackPower());
    }

    @Override
    public void useSkill(Character target) {
        if (!skillUsed) {
            System.out.println(getNama() + " menggunakan Double Shot!");
            target.takeDamage((int)(getAttackPower() * 1.2));
            target.takeDamage((int)(getAttackPower() * 1.2));
            skillUsed = true;
        } else {
            System.out.println("Ultimate sudah digunakan!");
        }
    }
}
