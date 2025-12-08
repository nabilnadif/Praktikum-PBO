public class Main {
    public static void main(String[] args) {
        Provinsi provinsi1 = new Provinsi();
        Provinsi provinsi2 = new Provinsi("Sumatera Barat", 1945, "Padang");
        Provinsi provinsi3 = new Provinsi("Jawa Barat", 37044.86, 1945, 80, "Bandung", "Seblak");
        provinsi1.tampilkanInfo();
        provinsi1.tampilkanInfo("=== Sekilas Info ===");
        System.out.println("==============================");
        provinsi2.tampilkanInfo();
        provinsi2.tampilkanInfo("Fun Fact", 19);
        System.out.println("==============================");
        provinsi3.tampilkanInfo();
        provinsi3.tampilkanInfo("Fakta Menarik", 72.5);
    }
}