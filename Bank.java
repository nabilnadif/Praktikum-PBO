package LatihanPraktikum_6;

class Bank {
    // atribut static dan private
    private static String namaBank;
    private static int jumlahRekening = 0;

    // constructor
    public Bank(String namaBank, int jumlah) {
        this.namaBank = namaBank;
        Bank.jumlahRekening += jumlah;
    }

    // overloading tampilkan info bank
    public void tampilkanRekening() {
        System.out.println("Nama Bank : " + namaBank);
        System.out.println("Jumlah Rekening : " + jumlahRekening);
    }

    // overloading tampilkan info rekening 
    public void tampilkanRekening(Rekening r) {
        System.out.println("Nama Nasabah : " + r.getNama());
        System.out.println("Saldo : Rp" + r.getSaldo());
        System.out.println();
    }
}
