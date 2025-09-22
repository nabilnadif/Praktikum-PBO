public class Provinsi {
    String nama;
    double luas;
    int tahunBerdiri;
    int usia;
    String ibuKota;
    String makananKhas;

   public Provinsi()
   {
        this.nama = "Riau";
        this.luas = 87023.66;
        this.tahunBerdiri = 1957;
        this.ibuKota = "Pekanbaru";
        this.makananKhas = "Bolu Kemojo";
        this.usia = 2025 - this.tahunBerdiri;
    }
    public Provinsi(String nama, int tahunBerdiri, String ibuKota)
    {
        this.nama = nama;
        this.luas = 42297.30;
        this.tahunBerdiri = tahunBerdiri;
        this.ibuKota = ibuKota;
        this.makananKhas = "Rendang";
    }
    public Provinsi(String nama, double luas, int tahunBerdiri, int usia, String ibuKota, String makananKhas) 
    {
        this.nama = nama;
        this.luas = luas;
        this.tahunBerdiri = tahunBerdiri;
        this.usia = usia;
        this.ibuKota = ibuKota;
        this.makananKhas = makananKhas;
    }
    public Provinsi(String nama, double luas, int tahunBerdiri, String ibuKota, String makananKhas) 
    {
        this.nama = nama;
        this.luas = luas;
        this.tahunBerdiri = tahunBerdiri;
        this.ibuKota = ibuKota;
        this.makananKhas = makananKhas;
        this.usia = hitungUsia(); 
    }
    public int hitungUsia() 
    {
        usia = 2025 - tahunBerdiri;
        return usia;
    }
    public void tampilkanInfo()
    {
        System.out.println("Nama Provinsi : " + nama);
        System.out.println("Luas Wilayah  : " + luas + " km kuadrat");
        System.out.println("Tahun Berdiri : " + tahunBerdiri);
        System.out.println("Usia          : " + hitungUsia() + " tahun");
        System.out.println("Ibu Kota      : " + ibuKota);
        System.out.println("Makanan Khas  : " + makananKhas);
    }
    public void tampilkanInfo(String judul)
    {
        System.out.println("\n" + judul);
        System.out.println("Provinsi " + this.nama + " memiliki ibu kota di " + this.ibuKota);
    }
    public void tampilkanInfo(String sapaan, int jumlahKabupaten)
    {
        System.out.println("\n" + sapaan + "! Provinsi " + this.nama + " ternyata memiliki " + jumlahKabupaten + " kabupaten/kota loh");
    }
    public void tampilkanInfo(String fakta, double indeks)
    {
        System.out.println("\n" + fakta + "! Provinsi " + this.nama + " ternyata memiliki indeks investasi sebesar " + indeks + " triliun rupiah");
    }

}
