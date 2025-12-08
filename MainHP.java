public class MainHP {
    public static void main(String[] args) {
        Handphone hp1 = new Handphone("Samsung", "Galaxy S25", 25000000, 2024, 5000);
        Handphone hp2 = new Handphone("Apple", "iPhone 15", 18000000, 2024, 4000);
        
        hp1.tampilkanInfo();
        hp2.tampilkanInfo();
        System.out.println("Merk HP 1 : " + hp1.getMerk());
        System.out.println("Tipe HP 1 : " + hp1.getTipe());
        System.out.println("Harga HP 1: " + hp1.getHarga());
        hp1.setHarga(22000000);
        System.out.println("Harga HP 1 setelah diskon: " + hp1.getHarga());
        Handphone.infoJumlahProduk();
    }
}
