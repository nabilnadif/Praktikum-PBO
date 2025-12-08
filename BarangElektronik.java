public class BarangElektronik extends Produk implements HargaAkhir {
    private int garansi;

    // Constructor
    public BarangElektronik(String nama, double harga, int garansi) {
        super(nama, harga);
        this.garansi = garansi;
    }

    // Method
    public void tampilkanGaransi() {
        System.out.println("Garansi     : " + garansi + " bulan");
    }

    // Overriding
    @Override
    public double hitungPajak() {
        return harga * 0.1; 
    }

    // Implementasi dari interface HargaAkhir
    @Override
    public double hitungHarga(){
        return harga * 1.01;
    }
}
