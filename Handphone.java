public class Handphone {
    private String merk;
    private String tipe;
    private double harga;
    private int tahunRilis;
    private int kapasitasBaterai;
    static int jumlahProduk = 0;
    
    public Handphone(String merk, String tipe, double harga, int tahunRilis, int kapasitasBaterai) {
        this.merk = merk;
        this.tipe = tipe;
        this.harga = harga;
        this.tahunRilis = tahunRilis;
        this.kapasitasBaterai = kapasitasBaterai;
        jumlahProduk++;
    }

    public void tampilkanInfo() {
        System.out.println("-----INFO HANDPHONE-----");
        System.out.println("Merk              : " + merk);
        System.out.println("Tipe              : " + tipe);
        System.out.println("Harga             : " + harga);
        System.out.println("Tahun Rilis       : " + tahunRilis);
        System.out.println("Kapasitas Baterai : " + kapasitasBaterai + " mAh");
    }

    public static void infoJumlahProduk(){
        System.out.println("Total produk yang tersedia : " +jumlahProduk);
    }

    public double getHarga(){ 
        return harga;
    }

    public void setHarga(double hargaBaru){
        this.harga = hargaBaru;
    }

    public String getMerk() {
        return merk;
    }

    public String getTipe() {
        return tipe;
    }
}
