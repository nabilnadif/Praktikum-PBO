public abstract class Character implements Attack {
    private String nama;
    private int hp;
    private int attackPower;
    private int defense;

    public Character(String nama, int hp, int attackPower, int defense) {
        this.nama = nama;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public String getNama() {
        return nama; 
    }
    public int getHp() {
        return hp; 
    }
    public int getAttackPower() {
        return attackPower;
    }
    public int getDefense() {
        return defense; 
    }
    public void setHp(int hp) { 
        this.hp = hp; 
    }
    public boolean isHidup() {
        return hp > 0;
    }

    @Override
    public void takeDamage(int damage) {
    int dmg;
    if (damage > defense) {
        dmg = damage - defense;
    } else {
        dmg = 0;
    }
    hp -= dmg;
    if (hp < 0) {
        hp = 0;
    }
    System.out.println(nama + " terkena " + dmg + " damage. (HP: " + hp + ")");    
    }
    public abstract void useSkill(Character target);
}