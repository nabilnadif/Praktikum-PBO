import java.util.Scanner;
public class BattleSystem {
    private Hero hero;
    private Enemy enemy;
    private Scanner input = new Scanner(System.in);

    public BattleSystem(Hero hero) {
        this.hero = hero;
        this.enemy = Enemy.generateRandomEnemy();
    }

    public void mulaiPertarungan() {
        System.out.println("Musuh muncul: " + enemy.getNama() + "!");
        System.out.println("======== Pertarungan Dimulai! ========");

        int round = 1;
        while (hero.isHidup() && enemy.isHidup()) {
            System.out.println("============ Ronde " + round++ + " ============");
            System.out.println(hero.getNama() + " HP: " + hero.getHp());
            System.out.println(enemy.getNama() + " HP: " + enemy.getHp());
            System.out.println("========= Giliran " + hero.getNama() + " =========");
            System.out.println("Pilih aksi: ");
            System.out.println("1. Serang");
            System.out.println("2. Bertahan");
            System.out.println("3. Gunakan Ultimate");
            System.out.print("Masukkan pilihan: ");
            int aksi = input.nextInt();

            switch (aksi) {
                case 1:
                    hero.attack(enemy);
                    break;
                case 2:
                    hero.defend();
                    break;
                case 3:
                    hero.useSkill(enemy);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }

            if (!enemy.isHidup()) {
                System.out.println("\nYou win! The Winner takes it all!");
                break;
            }

            System.out.println("\n========= Giliran Musuh =========");
            if (Enemy.shouldUseSkill()) {
                enemy.useSkill(hero);
            } else {
                enemy.attack(hero);
            }

            if (!hero.isHidup()) {
                System.out.println("\nYou lose! The Loser Standing Small. Game Over.");
                break;
            }

            System.out.println("=====================================\n");
        }
    }
}