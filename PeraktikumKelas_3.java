import java.util.Scanner;
public class PeraktikumKelas_3 {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
    //Operasi Aritmatika
    /*int a = 10, b = 3;

    System.out.println("Hasil a + b = " + (a + b)); // Penjumlahan: 13
    System.out.println("Hasil a - b = " + (a - b)); // Pengurangan: 7
    System.out.println("Hasil a * b = " + (a * b)); // Perkalian: 30
    System.out.println("Hasil a / b = " + (a / b)); // Pembagian: 3
    System.out.println("Hasil a % b = " + (a % b)); // Modulus: 1

    System.out.println("Hasil ++a = " + (++a)); // Pre-increment: 11
    System.out.println("Hasil b-- = " + (b--)); // Post-decrement: 3 (b menjadi 2 setelah ini)*/

    //Operator Perbandingan
    /*int x = 5, y = 8;

    System.out.println("Hasil x = y = " + (x == y));   // false
    System.out.println("Hasil x != y = " + (x != y)); // true
    System.out.println("Hasil x > y = " + (x > y));   // false
    System.out.println("Hasil x < y = " + (x < y));   // true
    System.out.println("Hasil x >= y = " + (x >= y)); // false
    System.out.println("Hasil x <= y = " + (x <= y)); // true */

    //Operator Logika
    /*boolean p = true, q = false;

    System.out.println("Hasil p && q = " + (p && q)); // AND: false
    System.out.println("Hasil p || q = " + (p || q)); // OR: true
    System.out.println("Hasil !p = " + (!p));         // NOT: false

    //short-circuit evaluation
    int a = 5, b = 0;
    boolean result = (b != 0) && ((a / b) > 2); // Tidak error karena short-circuit
    System.out.println("Result: " + result); // false */

    //Operator Assignment
    /*int num = 10;

    num += 5;  // num = num + 5 = 15
    num -= 3;  // num = num - 3 = 12
    num *= 2;  // num = num * 2 = 24
    num /= 4;  // num = num / 4 = 6
    num %= 4;  // num = num % 4 = 2 

    System.out.println("Nilai akhir: " + num); */

    //Operator Ternary
    /*int score = 85;
    String grade = (score >= 80) ? "A" : (score >= 70) ? "B" : "C";
    System.out.println("Nilai: " + grade);

    int max = (10 > 5) ? 10 : 5;
    System.out.println("Nilai maksimum: " + max); */

    //Operator Bitwise
    /*int a = 12; // 1100 dalam biner
    int b = 10; // 1010 dalam biner

    System.out.println("Hasil a & b = " + (a & b)); // AND: 8 (1000 dalam biner)
    System.out.println("Hasil a | b = " + (a | b)); // OR: 14 (1110 dalam biner)
    System.out.println("Hasil a ^ b = " + (a ^ b)); // XOR: 6 (0110 dalam biner)
    System.out.println("Hasil ~a = " + (~a));       // NOT: -13 
    System.out.println("Hasil a << 2 = " + (a << 2)); // Shift kiri: 48 
    System.out.println("Hasil a >> 2 = " + (a >> 2)); // Shift kanan: 3 */

    //Output menggunakan System.out
    /*System.out.println ("Hello World!"); 
    System.out.println ("Baris Kedua"); 

    System.out.print ("Hello ");
    System.out.print ("World!");
    System.out.println();

    String nama = "Nabil";
    int umur = 19;
    double tinggi = 169;

    System.out.printf("Nama: %s, Umur: %d tahun, Tinggi %.1f cm.%n", nama, umur, tinggi);*/

    //Input menggunakan Scanner

    /*Scanner scanner = new Scanner(System.in);
    //Input String
    System.out.print("Masukkan nama Anda: ");
    String nama = scanner.nextLine();
    System.out.print("Masukkan umur Anda: ");
    int umur = scanner.nextInt();
    System.out.print("Masukkan tinggi badan Anda: ");
    double tinggi = scanner.nextDouble(); */

    //AutoBoxing dan Unboxing
    //autoboxing - primitif ke wrapper
    /*int primitifInt = 100;
    Integer wrapperInt = primitifInt; // AutoBoxing

    //unboxing - wrapper ke primitif
    Integer anotherWrapper = 200;
    int anotherPrimitif = anotherWrapper; // Unboxing

    //Konversi string ke wrapper dan sebaliknya
    String strNumber = "123";
    String strDouble = "45.67";
    String strBoolean = "true";

    //Parse Method
    int num = Integer.parseInt(strNumber);
    double decimal = Double.parseDouble(strDouble);
    boolean flag = Boolean.parseBoolean(strBoolean);

    System.out.println("Hasil parsing:");
    System.out.println("Parsed int: " + num);
    System.out.println("Parsed double: " + decimal);
    System.out.println("Parsed boolean: " + flag);

    Integer wrapperNum = Integer.valueOf(strNumber);
    Double wrapperDecimal = Double.valueOf(strDouble);

    System.out.println("Wrapper Integer: " + wrapperNum);
    System.out.println("Wrapper Double: " + wrapperDecimal);

    //wrapper/primitif ke string
    int value = 789;
    String strValue1 = Integer.toString(value);
    String strValue2 = String.valueOf(value);

    System.out.println("toString(): " + strValue1);
    System.out.println("Valueof(): " + strValue2);

    //Integer Methods
    System.out.println("Max Integer: " + Integer.MAX_VALUE);
    System.out.println("Min Integer: " + Integer.MIN_VALUE);*/

    //Konversi sistem Bilangan
    int decimal = 255;
    System.out.println("Binary: " + Integer.toBinaryString(decimal));
    System.out.println("Octal: " + Integer.toOctalString(decimal));
    System.out.println("Hexadecimal: " + Integer.toHexString(decimal));

    //Enumerasi
    /*enum Hari {
        SENIN, SELASA, RABU, KAMIS, JUMAT, SABTU, MINGGU
     }

    Hari hariIni = Hari.SENIN;
    
    if (hariIni == Hari.SENIN) {
        System.out.println("Hari ini adalah Senin!");
    } 
    System.out.println("Besok adalah " + Hari.SELASA);*/

    }

}
