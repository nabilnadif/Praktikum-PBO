package HasilPraktikum_6;

class Produk {

    public String nama;
    private double harga;
    protected int stok;
    private String namaSuplier = "Alif";
    static int jumlahProduk = 0;

    // Constructor
    public Produk(String nama, double harga, int stok){
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        jumlahProduk++;
    }
    
    // Method
    public void tampilkanNamaSuplier(){
        System.out.println("Nama Suplier nya adalah " + namaSuplier);
    } 
    public void tampilkanInfo(){
        System.out.println("-----INFO PRODUK-----");
        System.out.println("Nama Barang    : " +nama);
        System.out.println("Harga Barang   : " +harga);
        System.out.println("Stok Barang    : " +stok);
    }
    
    public static void infoJumlahProduk(){
        System.out.println("Total produk yang telah dibuat: " +jumlahProduk);
    }

    // Getter dan Setter
    public double getHarga(){
        return harga;
    }
    public void setHarga(double hargaBaru){
        if(hargaBaru>0){
            this.harga = hargaBaru;
        }
        else if(hargaBaru == 0){
            this.harga = hargaBaru;
            System.out.println("Produk ini gratis");
        }
        else{
            System.out.println("Harga produk tidak boleh negatif !");
        }
    }
}
    

