import java.util.Scanner;

public class Kalkulator_Bangun {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;
        
        do 
        { //perulangan do while agar program berjalan terus hingga kondisi tertentu
            // Menampilkan menu
            System.out.println("\n=== KALKULATOR LUAS BANGUN DATAR KELOMPOK 3 ===");
            System.out.println("1. Persegi");
            System.out.println("2. Persegi Panjang");
            System.out.println("3. Lingkaran");
            System.out.println("4. Segitiga");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            pilihan = input.nextInt();

            switch (pilihan) //Percabangan switch case untuk menjalankan menu
            {
                case 1: //kondisi 1
                    // Persegi
                    System.out.print("Masukkan sisi persegi: ");
                    double sisi = input.nextDouble();
                    double luasPersegi = sisi * sisi;
                    System.out.println("Luas Persegi = " + luasPersegi);
                    break;
                case 2: //kondisi 2
                    // Persegi Panjang
                    System.out.print("Masukkan panjang: ");
                    double panjang = input.nextDouble();
                    System.out.print("Masukkan lebar: ");
                    double lebar = input.nextDouble();
                    double luasPP = panjang * lebar;
                    System.out.println("Luas Persegi Panjang = " + luasPP);
                    break;
                case 3: //kondisi 3
                    // Lingkaran
                    System.out.print("Masukkan jari-jari lingkaran: ");
                    double r = input.nextDouble();
                    double luasLingkaran = 3.14 * r * r;
                    System.out.println("Luas Lingkaran = " + luasLingkaran);
                    break;
                case 4: //kondisi 4
                    // Segitiga
                    System.out.print("Masukkan alas segitiga: ");
                    double alas = input.nextDouble();
                    System.out.print("Masukkan tinggi segitiga: ");
                    double tinggi = input.nextDouble();
                    double luasSegitiga = 0.5 * alas * tinggi;
                    System.out.println("Luas Segitiga = " + luasSegitiga);
                    break;
                case 5: //kondisi 5
                    // Selesai atau user telah usai
                    System.out.println("Terima kasih telah menggunakan kalkulator kelompok 3!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);
    }
}