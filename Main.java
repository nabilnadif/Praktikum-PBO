package LatihanPraktikum_6;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("Bank Bersama", 2);

        Rekening r1 = new Rekening("Raka", 2000000);
        Rekening r2 = new Rekening("Nabil", 1500000);

        bank.tampilkanRekening();
        System.out.println();

        bank.tampilkanRekening(r1);
        r1.setNama("Maira");
        bank.tampilkanRekening(r1);

        bank.tampilkanRekening(r2);
        r2.setSaldo(3000000);
        bank.tampilkanRekening(r2);

    }
}
