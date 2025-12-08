public class Warrior extends Hero {
    public Warrior(String nama) {
        super(nama, 135, 30, 10);
    }

    @Override
    public void attack(Character target) {
        System.out.println(getNama() + " menebas dengan pedang ksatria pemberani!");
        target.takeDamage(getAttackPower());
    }

    @Override
    public void useSkill(Character target) {
        if (!skillUsed) {
            System.out.println(getNama() + " menggunakan Power Strike! Damage meningkat dua kali lipat!");
            System.out.println(getNama() + " HP berkurang 5!");
            target.takeDamage(getAttackPower() * 2);
            setHp(getHp() - 5);
            skillUsed = true;
        } else {
            System.out.println("Ultimate sudah digunakan!");
        }
    }
}
