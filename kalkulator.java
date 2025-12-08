import java.util.Scanner;

public class kalkulator {
    // Untuk Volume limas segiempat (alas persegi)
    public static double volumeLimasSegiempat(double sisi, double tinggi) {
        double luasAlas = sisi * sisi; // rumus luas persegi
        return (1.0 / 3.0) * luasAlas * tinggi; // rumus volume limas segiempat
    }

    // Untuk Volume limas segitiga (alas segitiga)
    public static double volumeLimasSegitiga(double alas, double tinggiAlas, double tinggiLimas) {
        double luasAlas = 0.5 * alas * tinggiAlas; // rumus luas segitiga
        return (1.0 / 3.0) * luasAlas * tinggiLimas; // rumus volume limas segitiga
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== KALKULATOR VOLUME LIMAS KELOMPOK 3 ===");
            System.out.println("Pilih jenis limas yang ingin dihitung volumenya:");
            System.out.println("[Perhitungan dilakukan dengan satuan cm]");
            System.out.println("1. Limas Segiempat (alas persegi)");
            System.out.println("2. Limas Segitiga (alas segitiga)");
            System.out.println("0. Keluar");
            System.out.print("Pilih jenis limas: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan panjang sisi alas: ");
                    double sisi = input.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiSegiempat = input.nextDouble();
                    System.out.println("Volume Limas Segiempat = " + volumeLimasSegiempat(sisi, tinggiSegiempat));
                    break;

                case 2:
                    System.out.print("Masukkan panjang alas segitiga: ");
                    double alas = input.nextDouble();
                    System.out.print("Masukkan tinggi segitiga alas: ");
                    double tinggiAlas = input.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiSegitiga = input.nextDouble();
                    System.out.println("Volume Limas Segitiga = " + volumeLimasSegitiga(alas, tinggiAlas, tinggiSegitiga));
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan kalkulator limas Kelompok 3!");
                    break;

                default:
                    System.out.println("Pilihan kamu tidak valid. Silahkan coba lagi !");
            }

        } while (pilihan != 0);

    }
}