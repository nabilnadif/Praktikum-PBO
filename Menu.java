import java.util.*;

public class Menu {
    public static void main(String[] args) {

        int counter = 1;
        boolean on = true;
        Scanner sc = new Scanner(System.in);
        Set<String> menu = new HashSet<>();
        Map<String, Pesanan> pesanan = new HashMap<>();

        // Menu
        do {
            System.out.println("### SELAMAT DATANG DI SISTEM MANAJEMEN PESANAN WARUNG ###");
            System.out.println("Pilih Fitur (1-3) :");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Tampilkan Pesanan");
            System.out.println("3. Hapus Pesanan");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("## Tambah Pesanan ##\n");
                    System.out.print("Masukkan nama pelanggan : ");
                    String nama = sc.nextLine();
                    System.out.print("Masukkan pilihan menu : ");
                    String namaMenu = sc.nextLine();
                    if (menu.contains(namaMenu)) {
                        System.out.println("Menu sudah tidak bisa dipilih!");
                    } else {
                        menu.add(namaMenu);
                        String key = String.valueOf(counter);
                        pesanan.put(key, new Pesanan(nama, namaMenu));
                        counter++;
                    }
                    break;
                case "2":
                    System.out.println("## Semua Pesanan ##");
                    for (Map.Entry<String, Pesanan> entry : pesanan.entrySet()) {
                        String key = entry.getKey();
                        Pesanan pesan = entry.getValue();

                        System.out.println("Pesanan #" + key + "\n" + pesan + "\n");
                    }
                    break;
                case "3":
                    System.out.println("## Hapus Pesanan ##");
                    System.out.println("Pilih pesanan yang ingin dihapus : ");
                    for (String key : pesanan.keySet()) {
                        System.out.println(key + " : " + "\t" + pesanan.get(key));
                    }
                    String pilihanHapus = sc.nextLine();
                    if (pesanan.containsKey(pilihanHapus)) {
                        pesanan.remove(pilihanHapus);
                        System.out.println("Pesanan telah dihapus!");
                    }
                    break;
                default:
                    System.out.println("Pilihan invalid");
                    on = false;
            }
        } while (on);
    }
}