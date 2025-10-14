public class Main {
    public static void main(String[] args) {
        Player player = new Player(100, 10);
        Goblin goblin = new Goblin(50, 5);

        System.out.println("======== Pertarungan Dimulai! ========");
        System.out.println("Player HP: " + player.getHp() + " | Goblin HP: " + goblin.getHp());
        System.out.println("======================================");

        int round = 1;
        while (player.getHp() > 0 && goblin.getHp() > 0) {
            System.out.println("--- Ronde " + round + " ---");
            
            player.attack(goblin);
            if (goblin.getHp() <= 0) {
                System.out.println("Goblin telah dikalahkan!");
                break; 
            }
            System.out.println("Sisa HP Goblin: " + goblin.getHp());

            goblin.attack(player);
            if (player.getHp() <= 0) {
                System.out.println("Player telah dikalahkan!");
                break; 
            }
            System.out.println("Sisa HP Player: " + player.getHp());
            System.out.println("=====================================");
            round++;
        }

        System.out.println("\n======== Pertarungan Selesai! ========");
        if (player.getHp() > 0) {
            System.out.println("The winner takes it all! Player menang!");
        } else {
            System.out.println("Game Over.");
        }
    }
}