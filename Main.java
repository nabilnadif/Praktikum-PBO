import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("====== Game RPG Berbasis Terminal ======");
        System.out.println("Pilih Role Hero Anda:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");
        System.out.print("Masukkan pilihan Anda: ");
        int pilih = input.nextInt();
        Hero hero;

        switch (pilih) {
            case 1:
                hero = new Warrior("Warrior");
                break;
            case 2:
                hero = new Mage("Mage");
                break;
            case 3:
                hero = new Archer("Archer");
                break;
            default:
                System.out.println("Pilihan tidak valid. Memilih Warrior sebagai default.");
                hero = new Warrior("Warrior");
                break;
        }

        System.out.println("Anda telah memilih: " + hero.getNama());
        BattleSystem battle = new BattleSystem(hero);
        battle.mulaiPertarungan();
    }
}