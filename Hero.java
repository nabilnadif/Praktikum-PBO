public abstract class Hero extends Character {
    boolean skillUsed = false;

    public Hero(String nama, int hp, int attackPower, int defense) {
        super(nama, hp, attackPower, defense);
    }

    public void defend() {
        System.out.println(getNama() + " bertahan, mengurangi damage serangan!");
        setHp(getHp() + 5); 
    }
}
