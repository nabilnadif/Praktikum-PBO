package HasilPraktikum_6;

public class MainProduk {
     public static void main(String[] args) {
        Produk produk1 = new Produk("Laptop", 12000000, 5);
        Produk produk2 = new Produk("Handphone", 5000000, 2);
        
        System.out.println("Nama Produk 1: " +produk1.nama);
        System.out.println("Harga : " +produk1.getHarga());
        produk1.setHarga(7000000);
        // System.out.println(produk1.nama);
        // System.out.println(produk1.harga);
        // System.out.println(produk1.stok);
        produk1.tampilkanNamaSuplier();
        produk1.tampilkanInfo();
        produk2.tampilkanInfo();
        Produk.infoJumlahProduk();   
    }
}

