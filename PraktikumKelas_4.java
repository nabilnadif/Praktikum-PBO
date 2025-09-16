import java.util.Scanner;
public class PraktikumKelas_4 {
    public static void main(String[] args) {
        //Percabangan if-else
        // Scanner input = new Scanner(System.in);
        // System.out.print("Masukkan nilai: ");
        // int nilai = input.nextInt();

        // if (nilai>75){
        //     System.out.println("Anda lulus ujian");
        // }
        // else{
        //     System.out.println("Anda harus mengulang ujian");
        // }

        //Percabangan if-else if-else
        // System.out.print("Masukkan nilai: ");
        // int nilai = input.nextInt();

        // if (nilai>75){
        //     System.out.println("Anda lulus ujian");
        // }
        // else if (nilai>=65){
        //     System.out.println("Nilai anda berada di ambang batas kelulusan");
        // }
        // else{
        //     System.out.println("Anda harus mengulang ujian");
        // }

        //Percabangan Switch Case
        // System.out.print("Masukkan warna (RGB): ");
        // String warna = input.nextLine();

        // switch (warna){
        //     case "R":
        //         System.out.println("Anda memilih warna Merah");
        //         break;
        //     case "G":
        //         System.out.println("Anda memilih warna Hijau");
        //         break;
        //     case "B":
        //         System.out.println("Anda memilih warna Biru");
        //         break;
        //     default:
        //         System.out.println("Warna tidak tersedia");
        // }

        //Percabangan Nested If
        // System.out.print("Masukkan angka: ");
        // int angka = input.nextInt();
        // if (angka > 0) {
        //     System.out.println("Bilangan bernilai positif");
        //     if (angka % 2 == 0) {
        //         System.out.println("dan genap");
        //     } else {
        //         System.out.println("dan ganjil");
        //     }
        // } else if (angka == 0) {
        //     System.out.println("Bilangan bernilai netral");
        // } else {
        //     System.out.println("Bilangan bernilai negatif");
        //     if (angka % 2 == 0) {
        //         System.out.println("dan genap");
        //     } else {
        //         System.out.println("dan ganjil");

        //Perulangan For
        // int i;
        // for (i=1; i<=5; i++){
        //     System.out.println("Perulangan ke-"+i);
        // }

        // String[] mahasiswa = {"Nabil", "Maira", "Raka", "Farrel"};
        // for (i=0; i<mahasiswa.length; i++){
        //     System.out.println("Mahasiswa ke-"+(i+1)+": "+mahasiswa[i]);
        // }

        //Perulangan While
        // int a = 1;
        // while (a <= 5) {
        //     System.out.println("While loop ke-" + a);
        //     a++;
        // }

        // Scanner input = new Scanner(System.in);
        // String password = "";
        // while (!password.equals("kelompok3")) {
        //     System.out.print("Masukkan password: ");
        //     password = input.nextLine();
        // }
        // System.out.println("Login berhasil!");

        //Perulangan Do While
        int x = 6;
        do {
            System.out.println("Do-While loop ke-" + x);
            x++;
        } while (x <= 5);

        //Perulangan Nested Loop
        // System.out.println("---Menecetak nomor kursi bioskop---");
        // char kursi = 'A';
        // for (int i = 0; i < 3; i++) { 
        //     for (int j = 1; j <= 3; j++) { 
        //         System.out.print(kursi + "" + j + " ");
        //     }
        //     System.out.println();
        //     kursi++;
        // }

        //Control Statement: Break dan Continue
        //break
        for (int d = 1; d <= 10; d++) {
            if (d == 5) {
                System.out.println("Break di angka " + d);
                break; // Menghentikan loop saat d = 6
            }
            System.out.println("Angka: " + d);
        }

        //continue
        for (int e = 1; e <= 10; e++) {
            if (e % 2 == 0) {
                continue; // Melewati iterasi saat e adalah angka genap
            }
            System.out.println("Angka ganjil: " + e);
        }
    }
}


