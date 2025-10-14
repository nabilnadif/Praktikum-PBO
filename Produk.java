public class Produk implements HargaAkhir{
    protected String nama;
    protected double harga;

    // Constructor
    public Produk(String nama, double harga){
        this.nama = nama;
        this.harga = harga;
    }

    // Method
    public void tampilkanInfo(){
        System.out.println("Nama Produk : " + nama);
        System.out.println("Harga       : " + harga);
    }

    // Overriding
    public double hitungPajak(){
        return harga * 0.05; 
    }

    @Override
    public double hitungHarga(){
        return harga * 1.05;
    }
}