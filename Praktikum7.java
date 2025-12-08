public class Praktikum7 {
    //Inheritance
    // public static void main(String[] args) {
    //     BarangElektronik laptop = new BarangElektronik
    //     ("Laptop", 12000000, 12);
    //     laptop.tampilkanInfo();
    //     laptop.tampilkanGaransi();
    // }

    //Polymorphism
    public static void main(String[] args) {
        Produk p1 = new Produk("Buku", 150000);
        //Produk p2 = new BarangElektronik("Mouse", 150000, 12);
        BarangElektronik p2 = new BarangElektronik("Mouse", 150000, 12);

        p1.tampilkanInfo();
        System.out.println("Pajak Produk Umum: " + p1.hitungPajak());
        System.out.println("Harga akhir produk: " + p1.hitungHarga());
        System.out.println("--------------------------------");
        p2.tampilkanInfo();
        //p2.tampilkanGaransi(); // Error karena method tidak ditemukan pada referensi tipe
        p2.tampilkanGaransi(); // Bisa karena referensi tipe sesuai dengan objek
        System.out.println("Pajak Barang Elektronik: " + p2.hitungPajak());
        System.out.println("Harga akhir produk: " + p2.hitungHarga());
    }
}
