public class Enemy {
    // Atribut privat
    private int hp;
    private int baseDamage;

    // Constructor
    public Enemy(int hp, int baseDamage) {
        this.hp = hp;
        this.baseDamage = baseDamage;
    }

    // Method attack dasar untuk musuh
    public void attack(Player target) {
        System.out.println("Enemy menyerang Player dengan damage " + this.baseDamage + "!");
        target.setHp(target.getHp() - this.baseDamage);
    }
    
    // Getter dan Setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}