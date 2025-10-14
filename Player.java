package GameRPG;

public class Player {
    // Atribut privat untuk enkapsulasi
    private int hp;
    private int baseDamage;

    // Constructor untuk inisialisasi objek Player
    public Player(int hp, int baseDamage) {
        this.hp = hp;
        this.baseDamage = baseDamage;
    }

    // Method untuk menyerang musuh
    public void attack(Enemy target) {
        System.out.println("Player menyerang " + target.getClass().getSimpleName() + " dengan damage " + this.baseDamage + "!");
        target.setHp(target.getHp() - this.baseDamage);
    }

    // Getter untuk hp
    public int getHp() {
        return hp;
    }

    // Setter untuk hp
    public void setHp(int hp) {
        this.hp = hp;
    }

    // Getter untuk baseDamage
    public int getBaseDamage() {
        return baseDamage;
    }

    // Setter untuk baseDamage
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}
